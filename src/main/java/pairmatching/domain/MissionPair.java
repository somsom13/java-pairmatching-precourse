package pairmatching.domain;

import java.util.List;
import java.util.stream.Collectors;

public class MissionPair {
    private List<Pair> missionPairs;

    public MissionPair(List<Pair> missionPairs) {
        this.missionPairs = missionPairs;
    }

    public boolean isPairAvailable(List<Pair> newPairs) {
        if (missionPairs.isEmpty()) {
            return true;
        }
        long availablePairCount = newPairs.stream()
                .filter(this::isNotDuplicatePair)
                .count();
        return availablePairCount == newPairs.size();
    }

    public List<List<String>> findMissionPairCrews() {
        return missionPairs.stream()
                .map(Pair::getPairInfo)
                .collect(Collectors.toList());
    }

    private boolean isNotDuplicatePair(Pair newPair) {
        for (Pair pair : missionPairs) {
            if (pair.isSamePair(newPair)) {
                return false;
            }
        }
        return true;
    }

}
