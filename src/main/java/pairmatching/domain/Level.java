package pairmatching.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import pairmatching.constant.ErrorString;

public enum Level {
    LEVEL1("레벨1", Arrays.asList("자동차경주", "로또", "숫자야구게임")),
    LEVEL2("레벨2", Arrays.asList("장바구니", "결제", "지하철노선도")),
    LEVEL3("레벨3", Collections.emptyList()),
    LEVEL4("레벨4", Arrays.asList("성능개선", "배포")),
    LEVEL5("레벨5", Collections.emptyList());

    private String name;
    private List<String> missions;

    Level(String name, List<String> missions) {
        this.name = name;
        this.missions = missions;
    }

    public static void validateMissionInLevel(String levelName, String mission) {
        Level level = validateLevelInput(levelName);
        if (level.getMissions().contains(mission)) {
            return;
        }
        throw new IllegalArgumentException(ErrorString.WRONG_OPTION.print());
    }

    public String getName() {
        return this.name;
    }

    public List<String> getMissions() {
        return missions;
    }

    private static Level validateLevelInput(String levelName) {
        return Arrays.stream(values())
                .filter(level -> level.getName().equals(levelName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ErrorString.WRONG_OPTION.print()));
    }
    // 메뉴 조회
}
