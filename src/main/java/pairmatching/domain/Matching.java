package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import pairmatching.constant.ErrorString;

public class Matching {
    private final MatchingData matchingData;
    private final Crew backendCrew;
    private final Crew frontendCrew;
    private boolean isPairGenerationSuccess;

    //입력받은게 모든 검증에 통과한다면, MatchingDetail 을 만들어서 matchingData 를 찾음
    public Matching() {
        this.matchingData = new MatchingData();
        this.backendCrew = new Crew(Course.BACKEND.getEngName());
        this.frontendCrew = new Crew(Course.FRONTEND.getEngName());
        this.isPairGenerationSuccess = false;
    }

    public void provideNewPairMatch(String mission, String level, String course) throws IllegalArgumentException {
        Course.validateCourseName(course);
        Level.validateMissionInLevel(level, mission);
        generatePair(mission, level, course);
    }

    private void generatePair(String mission, String level, String course) {
        int tryCount = 1;
        while (tryCount <= 3) {
            if (matchingData.isMatchAvailable(new MissionDetail(mission, level, course), generateNewMissionPair(course))) {
                isPairGenerationSuccess = true;
                return;
            }
            tryCount++;
        }
        throw new IllegalArgumentException(ErrorString.MATCH_FAIL.print());
    }

    private List<Pair> generateNewMissionPair(String course) {
        if (course.equals(Course.BACKEND.getEngName())) {
            return generateCrewPair(backendCrew.getCrews());
        }
        return generateCrewPair(frontendCrew.getCrews());
    }

    private List<Pair> generateCrewPair(List<String> crew) {
        List<Pair> crewPair = new ArrayList<>();
        for (int number = 0; number < crew.size() - 1; number += 2) {
            crewPair.add(new Pair(crew.get(number), crew.get(number + 1)));
        }
        if (crew.size() % 2 == 1) {
            crewPair.set(crewPair.size() - 1, generateCrewWithSize3(crewPair, crew.get(crew.size() - 1)));
        }
        return crewPair;
    }

    private Pair generateCrewWithSize3(List<Pair> crewPair, String lastCrew) {
        Pair lastCrewPair = crewPair.get(crewPair.size() - 1);
        lastCrewPair.addAdditionalCrew(lastCrew);
        return lastCrewPair;
    }

}
