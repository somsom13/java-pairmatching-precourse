package pairmatching.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MissionPairTest {
    private MissionPair missionPair;

    @BeforeEach
    void setUp() {
        this.missionPair = new MissionPair(Arrays.asList(new Pair("Alice", "Bob"),
                new Pair("Dave", "Eve"),
                new Pair("Jane", "Kate")));
    }

    @Test
    @DisplayName("새로운 페어 중 중복 페어가 하나라도 포함된다면 생성 불가하다")
    void newPairIncludingDuplicatePair() {
        List<Pair> newPair = Arrays.asList(new Pair("Alice","Jane"),
                new Pair("Bob", "Kate"),
                new Pair("Eve", "Dave"));
        assertThat(missionPair.isPairAvailable(newPair)).isFalse();
    }

    @Test
    @DisplayName("새로운 페어가 모두 다르다면 생성 가능하다")
    void newPairWithNoDuplication() {
        List<Pair> newPair = Arrays.asList(new Pair("Alice","Kate"),
                new Pair("Bob", "Dave"),
                new Pair("Eve", "Jane"));
        assertThat(missionPair.isPairAvailable(newPair)).isTrue();
    }
}