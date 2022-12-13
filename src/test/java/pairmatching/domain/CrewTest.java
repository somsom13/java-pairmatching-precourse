package pairmatching.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CrewTest {

    @Test
    @DisplayName("파일 입력을 통한 크루 생성을 테스트한다")
    void readCrewNameFromFile() {
        assertThat(new Crew("backend").getCrews().size()).isEqualTo(20);
        assertThat(new Crew("frontend").getCrews().size()).isEqualTo(15);
    }

}