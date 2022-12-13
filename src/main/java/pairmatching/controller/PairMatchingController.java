package pairmatching.controller;

import java.util.StringTokenizer;
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
        return FunctionMenu.findRequestedMenu(inputView.chooseFunctionMenu());
    }

    private void doFunction(FunctionMenu menu) {
        if (menu == FunctionMenu.MATCHING) {
            outputView.printCourseMenu();
            String missionInfo = inputView.chooseCourseAndMission(); //여기서 while 문을 사용하자!
            startPairMatching(missionInfo);
            printMatchingResult(missionInfo);
        }
    }

    private void printMatchingResult(String missionInfo) {
        StringTokenizer tokenizer = new StringTokenizer(missionInfo, ", ");
        try {
            outputView.printMatchingResult(matching.findPairMatchingResult(tokenizer.nextToken(), tokenizer.nextToken(),
                    tokenizer.nextToken()));
        } catch (IllegalArgumentException noDataException) {
            System.out.println(noDataException.getMessage());
        }
    }

    private void startPairMatching(String missionInfo) {
        StringTokenizer tokenizer = new StringTokenizer(missionInfo, ", ");
        try {
            matching.provideNewPairMatch(tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken());
        } catch (IllegalArgumentException wrongMissionException) {
            System.out.println(wrongMissionException.getMessage());
            doFunction(FunctionMenu.MATCHING);
        }
    }
}
