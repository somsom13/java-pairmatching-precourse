package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import pairmatching.constant.ErrorString;
import pairmatching.constant.PrintConstant;
import pairmatching.domain.Course;
import pairmatching.domain.FunctionMenu;
import pairmatching.domain.Level;
import pairmatching.domain.Rematch;

public class InputView {
    public InputView() {
    }

    public String chooseFunctionMenu() {
        showFunctionMenu();
        return Console.readLine();
    }

    public String chooseCourseAndMission() {
        printCourseAndMissionInfo();
        while (true) {
            try {
                return readMissionChoose(Console.readLine());
            } catch (IllegalArgumentException formatException) {
                System.out.println(formatException.getMessage());
            }
        }
    }

    public String chooseRematch() {
        List<String> rematchMenu = Arrays.stream(Rematch.values())
                .map(Rematch::getMessage)
                .collect(Collectors.toList());
        printWithShortDivision(rematchMenu);
        return Console.readLine();
    }

    private String readMissionChoose(String input) {
        validateMissionInput(input);
        return input;
    }

    private void showFunctionMenu() {
        System.out.println("\n" + PrintConstant.MENU_REQUEST);
        Arrays.stream(FunctionMenu.values()).forEach(menu -> System.out.println(printEachMenu(menu)));
    }

    private String printEachMenu(FunctionMenu menu) {
        StringJoiner joiner = new StringJoiner(PrintConstant.MENU_DELIMITER);
        joiner.add(menu.getMenu()).add(menu.getFunction());
        return joiner.toString();
    }

    private void printCourseAndMissionInfo() {
        System.out.printf(PrintConstant.OPTION_CHOOSE_REQUEST + "\n",
                printMissionSelectRequest(PrintConstant.COURSE, PrintConstant.LEVEL, PrintConstant.MISSION));
        System.out.println(
                PrintConstant.EXAMPLE + printMissionSelectRequest(Course.BACKEND.getName(), Level.LEVEL1.getName(),
                        Level.LEVEL1.getMissions().get(0)));
    }

    private String printMissionSelectRequest(String firstOption, String secondOption, String thirdOption) {
        return String.format(PrintConstant.OPTION_WITH_COMMA, firstOption, secondOption, thirdOption);
    }

    private void validateMissionInput(String input) {
        if (input.contains(",")) {
            return;
        }
        throw new IllegalArgumentException(ErrorString.WRONG_OPTION_DELIMITER.print());
    }

    private void printWithShortDivision(List<String> options) {
        String joinedWithDelimiter = String.join(PrintConstant.SHORT_DIVISION, options);
        System.out.println(joinedWithDelimiter);
    }

}
