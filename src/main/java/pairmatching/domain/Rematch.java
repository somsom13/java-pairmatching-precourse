package pairmatching.domain;

import java.util.Arrays;
import pairmatching.constant.ErrorString;

public enum Rematch {
    REMATCH("네", true),
    NONE("아니오", false);

    private String message;
    private boolean isRematch;

    Rematch(String message, boolean isRematch) {
        this.message = message;
        this.isRematch = isRematch;
    }

    public static boolean isRematch(String message) {
        return Arrays.stream(values())
                .filter(rematch -> rematch.getMessage().equals(message))
                .findAny()
                .map(Rematch::isRematch)
                .orElseThrow(() -> new IllegalArgumentException(ErrorString.WRONG_REMATCH.print()));
    }

    public String getMessage() {
        return message;
    }

    public boolean isRematch() {
        return isRematch;
    }
}
