package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import pairmatching.constant.ErrorString;

public class Matching {
    private final MatchingData matchingData;
    private final Crew backendCrew;
    private final Crew frontendCrew;

    public Matching() {
        this.matchingData = new MatchingData();
        this.backendCrew = new Crew(Course.BACKEND.getEngName());
        this.frontendCrew = new Crew(Course.FRONTEND.getEngName());
    }

    public void provideNewPairMatch(String course, String level, String mission) throws IllegalArgumentException {
        MissionDetail missionDetail = new MissionDetail(mission, level, course);
        generatePair(missionDetail, course);
    }

    public List<List<String>> findPairMatchingResult(String course, String level, String mission) throws IllegalArgumentException {
        return matchingData.findMatchingPairData(new MissionDetail(mission, level, course));
    }

    public void validateCourseLevel(String course, String level, String mission) throws IllegalArgumentException {
        Course.validateCourseName(course);
        Level.validateMissionInLevel(level, mission);
    }

    public boolean isSameMissionPairExist(String course, String level, String mission) {
        return matchingData.isMatchingPairDataAlreadyExist(new MissionDetail(mission, level, course));
    }

    public void clearAllMatchingData() {
        matchingData.deleteMatchingData();
    }

    private void generatePair(MissionDetail missionDetail, String course) {
        int tryCount = 1;
        while (tryCount <= 3) {
            if (matchingData.isMatchAvailable(missionDetail, generateNewMissionPair(course))) {
                return;
            }
            tryCount++;
        }
        throw new IllegalArgumentException(ErrorString.MATCH_FAIL.print());
    }

    private List<Pair> generateNewMissionPair(String course) {
        if (course.equals(Course.BACKEND.getName())) {
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
