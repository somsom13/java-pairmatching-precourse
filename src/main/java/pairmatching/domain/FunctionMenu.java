package pairmatching.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import pairmatching.constant.ErrorString;

public enum FunctionMenu {
    MATCHING("1", "페어 매칭"),
    VIEW("2", "페어 조회"),
    INITIALIZE("3", "페어 초기화"),
    QUIT("Q", "종료");

    private static final Map<String, FunctionMenu> MENU_FUNCTION_MAP =
            Arrays.stream(values())
                    .collect(Collectors.toMap(FunctionMenu::getMenu, function -> function));
    private String menu;
    private String function;

    FunctionMenu(String menu, String function) {
        this.menu = menu;
        this.function = function;
    }

    public String getMenu() {
        return menu;
    }

    public String getFunction() {
        return function;
    }

    public static FunctionMenu findRequestedMenu(String chosenMenu) {
        validateExistingMenu(chosenMenu);
        return MENU_FUNCTION_MAP.get(chosenMenu);
    }

    private static void validateExistingMenu(String chosenMenu) {
        if (MENU_FUNCTION_MAP.containsKey(chosenMenu)) {
            return;
        }
        throw new IllegalArgumentException(ErrorString.WRONG_MENU.print());
    }
}
