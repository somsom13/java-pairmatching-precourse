package pairmatching.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FunctionMenuTest {
    private final String ERROR_HEAD = "[ERROR]";

    @Test
    @DisplayName("제공된 입력값 외의 값이 들어오면 예외가 발생한다")
    void inputWithWrongMenuNumber() {
        assertThatThrownBy(() -> FunctionMenu.findRequestedMenu("4"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith(ERROR_HEAD);
    }

    @Test
    @DisplayName("입력 메뉴에 따른 기능이 반환된다")
    void chooseMenuByInput() {
        assertThat(FunctionMenu.findRequestedMenu("1")).isEqualTo(FunctionMenu.MATCHING);
    }
}