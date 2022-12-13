package pairmatching.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PairTest {
    private Pair pair;

    @BeforeEach
    void setUp() {
        this.pair = new Pair("Alice", "Bob");
        pair.addAdditionalCrew("Dave");
    }

    @Test
    @DisplayName("완전히 같은 구성원이면 동일한 페어이다")
    void isExactlySamePair() {
        assertThat(pair.isSamePair(new Pair("Bob", "Alice"))).isTrue();
    }

    @Test
    @DisplayName("3명 중 2명이라도 같으면 동일한 페어이다")
    void isSamePairIncluded() {
        Pair newPair = new Pair("Alice", "Eve");
        newPair.addAdditionalCrew("Bob");
        assertThat(pair.isSamePair(newPair)).isTrue();
    }

    @Test
    @DisplayName("2명이 3명에 포함된다면 동일한 페어이다")
    void isSamePairInOrigin() {
        assertThat(pair.isSamePair(new Pair("Dave", "Alice"))).isTrue();
    }

    @Test
    @DisplayName("중복된 크루가 둘 이상 있지 않다면 다른 페어이다")
    void isExactlyDifferentPair() {
        assertThat(pair.isSamePair(new Pair("Dave", "Eve"))).isFalse();
    }
}