package pairmatching.domain;

public enum Course {
    BACKEND("백엔드", "backend"),
    FRONTEND("프론트엔드", "frontend");

    private String name;
    private String engName;

    Course(String name, String engName) {
        this.name = name;
        this.engName = engName;
    }

    // 코스 이름 조회
    // 입력값 검증
}
