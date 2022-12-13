package pairmatching.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import pairmatching.constant.ErrorString;
import pairmatching.domain.FunctionMenu;
import pairmatching.domain.Matching;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatchingController {
    private final InputView inputView;
    private final Matching matching;
    private final OutputView outputView;

    public PairMatchingController() {
        this.inputView = new InputView();
        this.matching = new Matching();
        this.outputView = new OutputView();
    }

    public void start() {
        while (true) {
            doFunction(selectMenu());
        }
    }

    private FunctionMenu selectMenu() {
        while (true) {
            try {
                return FunctionMenu.findRequestedMenu(inputView.chooseFunctionMenu());
            } catch (IllegalArgumentException wrongFunctionMenuException) {
                System.out.println(wrongFunctionMenuException.getMessage());
            }
        }
    }

    private void doFunction(FunctionMenu menu) {
        if (menu == FunctionMenu.MATCHING) {
            outputView.printCourseMenu();
            List<String> missionInfo = chooseMission();
            startPairMatching(missionInfo);
        }
        if (menu == FunctionMenu.VIEW) {
            outputView.printCourseMenu();
            printMatchingResult(chooseMission());
        }
    }

    private List<String> chooseMission() {
        while (true) {
            try {
                return readMissionSelection();
            } catch (IllegalArgumentException wrongMissionInput) {
                System.out.println(wrongMissionInput.getMessage());
            }
        }
    }

    private List<String> readMissionSelection() {
        String[] missionInfos = inputView.chooseCourseAndMission().split(", ");
        if (missionInfos.length != 3) {
            throw new IllegalArgumentException(ErrorString.WRONG_OPTION.print());
        }
        matching.validateCourseLevel(missionInfos[0], missionInfos[1], missionInfos[2]);
        return Arrays.stream(missionInfos).collect(Collectors.toList());
    }

    private void printMatchingResult(List<String> missionInfo) {
        try {
            outputView.printMatchingResult(matching.findPairMatchingResult(missionInfo.get(0),
                    missionInfo.get(1), missionInfo.get(2)));
        } catch (IllegalArgumentException noDataException) {
            System.out.println(noDataException.getMessage());
        }
    }

    private void startPairMatching(List<String> missionInfo) {
        try {
            matching.provideNewPairMatch(missionInfo.get(0), missionInfo.get(1), missionInfo.get(2));
        } catch (IllegalArgumentException pairMatchingFailException) {
            System.out.print(pairMatchingFailException.getMessage());
        }
        printMatchingResult(missionInfo);
    }
}
