package pairmatching.view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import pairmatching.constant.PrintConstant;
import pairmatching.domain.Course;
import pairmatching.domain.Level;

public class OutputView {
    public OutputView() {}

    public void printCourseMenu() {
        System.out.println("\n" + PrintConstant.MENU_START);
        printCourse();
        printMissions();
        System.out.println(PrintConstant.MENU_START);
    }

    public void printMatchingResult(List<List<String>> result) {
        System.out.println("\n" + PrintConstant.MATCHING_RESULT_INTRO);
        result.forEach(this::printEachPair);
        System.out.println(PrintConstant.EMPTY_STRING);
    }

    public void alertMatchingResultExistence() {
        System.out.println(PrintConstant.MATCHING_EXIST);
    }

    public void alertInitialization() {
        System.out.println(PrintConstant.INITIALIZATION);
    }

    private void printEachPair(List<String> pair) {
        String pairResult = pair.stream()
                .collect(Collectors.joining(PrintConstant.PAIR_DELIMITER));
        System.out.println(pairResult);
    }

    private void printCourse() {
        System.out.printf(PrintConstant.INTRO_WITH_COLUMN, PrintConstant.COURSE);
        printWithShortDivision(Arrays.stream(Course.values())
                .map(Course::getName)
                .collect(Collectors.toList()));
    }

    private void printMissions() {
        System.out.printf(PrintConstant.INTRO_WITH_COLUMN + "\n", PrintConstant.MISSION);
        Arrays.stream(Level.values())
                .forEach(this::printEachLevel);
    }

    private void printEachLevel(Level level) {
        System.out.printf(PrintConstant.LEVEL_INDENT + PrintConstant.INTRO_WITH_COLUMN, level.getName());
        printWithShortDivision(level.getMissions());
    }

    private void printWithShortDivision(List<String> options) {
        String joinedWithDelimiter = options.stream()
                .collect(Collectors.joining(PrintConstant.SHORT_DIVISION));
        System.out.println(joinedWithDelimiter);
    }
}
