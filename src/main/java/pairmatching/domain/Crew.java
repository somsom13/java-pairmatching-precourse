package pairmatching.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Crew {
    private final List<String> crews;

    public Crew(String course) {
        this.crews = readCrewName(course);
    }

    public List<String> getCrews() {
        return Randoms.shuffle(crews);
    }

    private List<String> readCrewName(String course) {
        Path path = Paths.get("src/main/resources/" + course + "-crew.md"); // 파일 경로는 항상 repository root 를 기준으로 한다
        List<String> crews = new ArrayList<>();
        try (Stream<String> stream = Files.lines(path)) {
            crews = stream.collect(Collectors.toList());
        } catch (IOException exception) {
            System.out.println("[ERROR] 파일 경로가 잘못되었습니다.");
        }
        return crews;
    }
}
