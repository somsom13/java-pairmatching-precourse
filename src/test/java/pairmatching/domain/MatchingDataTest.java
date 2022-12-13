package pairmatching.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MatchingDataTest {

    private static MatchingData matchingData;

    @BeforeAll
    static void setUp() {
        matchingData = new MatchingData();
    }

    @Test
    @DisplayName("같은 레벨에 중복되는 레벨이 없는, 완전히 새로운 매칭이면 추가된다")
    void newMatchCreation() {
        MissionDetail mission = new MissionDetail("테스트1","레벨1","백엔드");
        List<Pair> newPair = Arrays.asList(new Pair("Alice","Jane"),
                new Pair("Bob", "Kate"),
                new Pair("Eve", "Dave"));

        assertThat(matchingData.isMatchAvailable(mission, newPair)).isTrue();
    }

    @Test
    @DisplayName("동일한 레벨에 중복되는 페어가 생성되면 매칭이 불가능하다")
    void newPairWithDuplicateHistory() {
        MissionDetail mission = new MissionDetail("테스트2","레벨1","백엔드");
        List<Pair> newPair = Arrays.asList(new Pair("Alice","Jane"),
                new Pair("Bob", "Kate"),
                new Pair("Eve", "Dave"));

        assertThat(matchingData.isMatchAvailable(mission, newPair)).isFalse();
    }

    @Test
    @DisplayName("동일한 레벨에 중복되지 않는 페어가 생성되면 매칭이 가능하다")
    void newPairWithNoneDuplication() {
        MissionDetail mission = new MissionDetail("테스트3","레벨1","백엔드");
        List<Pair> newPair = Arrays.asList(new Pair("Alice", "Bob"),
                new Pair("Dave", "Jane"),
                new Pair("Eve", "Kate"));

        assertThat(matchingData.isMatchAvailable(mission, newPair)).isTrue();
    }

}