package pairmatching.constant;

public enum ErrorString {
    HEAD("[ERROR]"),
    WRONG_MENU("잘못된 기능 입력입니다."),
    WRONG_OPTION("주어진 과정, 레벨, 미션만 선택할 수 있습니다."),
    WRONG_REMATCH("네, 아니오 만 선택할 수 있습니다."),
    WRONG_OPTION_DELIMITER(",(쉼표)로 구분된 값만 입력할 수 있습니다."),
    MATCH_FAIL("매칭이 불가능합니다."),
    NO_MATCH_DATA("매칭 이력이 없습니다.");
    private String message;

    ErrorString(String message) {
        this.message = message;
    }

    public String print() {
        return HEAD.message + this.message;
    }
}
