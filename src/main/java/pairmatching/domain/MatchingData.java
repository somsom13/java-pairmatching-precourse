package pairmatching.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchingData {
    private Map<MissionDetail, MissionPair> matchingData; // 미션 디테일 - 페어 정보 저장

    public MatchingData() {
        this.matchingData = new HashMap<>();
    }

    public boolean isMatchAvailable(MissionDetail mission, List<Pair> newPair) {
        if (matchingData.containsKey(mission)) {
            MissionPair missionPair = matchingData.get(mission);
            if (!missionPair.isPairAvailable(newPair)) {
                return false;
            }
        }
        setNewPairData(mission, newPair);
        return true;
    }

    private void setNewPairData(MissionDetail mission, List<Pair> newPair) {
        matchingData.put(mission, new MissionPair(newPair));
    }
}
