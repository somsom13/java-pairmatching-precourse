package pairmatching.domain;

import java.util.Arrays;
import java.util.Map;
import pairmatching.constant.ErrorString;

public enum Course {
    BACKEND("백엔드", "backend"),
    FRONTEND("프론트엔드", "frontend");

    private String name;
    private String engName;

    Course(String name, String engName) {
        this.name = name;
        this.engName = engName;
    }

    public String getName() {
        return name;
    }

    public String getEngName() {
        return engName;
    }

    public static void validateCourseName(String courseName) {
        Arrays.stream(values())
                .filter(course -> course.getName().equals(courseName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ErrorString.WRONG_OPTION.print()));
    }

    // 코스 이름 조회
}
