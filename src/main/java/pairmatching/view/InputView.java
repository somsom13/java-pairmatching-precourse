package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.StringJoiner;
import pairmatching.constant.PrintConstant;
import pairmatching.domain.FunctionMenu;

public class InputView {
    public InputView() {
    }

    public String chooseFunctionMenu() {
        showFunctionMenu();
        return Console.readLine();
    }

    private void showFunctionMenu() {
        System.out.println(PrintConstant.MENU_REQUEST);
        Arrays.stream(FunctionMenu.values())
                .forEach(menu -> System.out.println(printEachMenu(menu)));
    }

    private String printEachMenu(FunctionMenu menu) {
        StringJoiner joiner = new StringJoiner(PrintConstant.MENU_DELIMITER);
        joiner.add(menu.getMenu())
                .add(menu.getFunction());
        return joiner.toString();
    }
}
