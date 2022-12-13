package pairmatching.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pairmatching.constant.ErrorString;

public class MatchingData {
    private Map<MissionDetail, MissionPair> matchingData; // 미션 디테일 - 페어 정보 저장

    public MatchingData() {
        this.matchingData = new HashMap<>();
    }

    public boolean isMatchAvailable(MissionDetail mission, List<Pair> newPair) {
        if (isPairAvailableInSameLevel(mission.getLevel(), newPair)) {
            setNewPairData(mission, newPair);
            return true;
        }
        return false;
    }

    public List<List<String>> findMatchingPairData(MissionDetail missionDetail) throws IllegalArgumentException {
        if (matchingData.containsKey(missionDetail)) {
            return matchingData.get(missionDetail)
                    .findMissionPairCrews();
        }
        throw new IllegalArgumentException(ErrorString.NO_MATCH_DATA.print());
    }

    public boolean isMatchingPairDataAlreadyExist(MissionDetail missionDetail) {
        return matchingData.containsKey(missionDetail);
    }

    private void setNewPairData(MissionDetail mission, List<Pair> newPair) {
        matchingData.put(mission, new MissionPair(newPair));
    }

    private boolean isPairAvailableInSameLevel(String level, List<Pair> newPair) {
        long duplicateCount = matchingData.entrySet()
                .stream()
                .filter(set -> set.getKey().isSameLevel(level))
                .filter(set -> !set.getValue().isPairAvailable(newPair))
                .count();
        return duplicateCount == 0;
    }
}
