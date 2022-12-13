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
        if (isPairAvailableInSameLevel(mission.getLevel(), newPair)) {
            setNewPairData(mission, newPair);
            return true;
        }
        return false;
//        if (matchingData.containsKey(mission)) { //같은 미션이 아니라 같은 level 에 대해서 해야한다
//            MissionPair missionPair = matchingData.get(mission);
//            if (!missionPair.isPairAvailable(newPair)) {
//                return false;
//            }
//        }
//        setNewPairData(mission, newPair); //contain 안되어도 일단 같은 레벨이랑 비교 -> 추가 가능해야 등록한다!
//        return true;
    }

    private void setNewPairData(MissionDetail mission, List<Pair> newPair) {
        matchingData.put(mission, new MissionPair(newPair));
    }

    //일단 중복 검사
    //추가 가능할 때, 새롭게 넣어준다
    private boolean isPairAvailableInSameLevel(String level, List<Pair> newPair) {
        long duplicateCount = matchingData.entrySet()
                .stream()
                .filter(set -> set.getKey().isSameLevel(level))
                .filter(set -> !set.getValue().isPairAvailable(newPair))
                .count();
        return duplicateCount == 0;
    }
}
