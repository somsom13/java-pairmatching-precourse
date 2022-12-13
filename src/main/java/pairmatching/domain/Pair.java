package pairmatching.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Pair {
    private List<String> pairs;

    public Pair(String firstCrew, String secondCrew) {
        this.pairs = new ArrayList<>();
        this.pairs.add(firstCrew);
        this.pairs.add(secondCrew);
    }

    public List<String> getPairInfo() {
        return pairs;
    }

    public void addAdditionalCrew(String thirdCrew) {
        this.pairs.add(thirdCrew);
    }

    public boolean isSamePair(Pair newPair) {
        if (isDuplicatePair(pairs, newPair.getPairInfo())) {
            return true;
        }
        if (isDuplicatePair(newPair.getPairInfo(), pairs)) {
            return true;
        }
        return false;
    }

    private boolean isDuplicatePair(List<String> compareTarget, List<String> originTarget) {
        long notDuplicateCrew = compareTarget.stream()
                .filter(pair -> isNotIncludedInOtherPair(originTarget, pair))
                .count();
        return compareTarget.size() == 3 && notDuplicateCrew <= 1 || compareTarget.size() == 2 && notDuplicateCrew == 0;
    }

    private boolean isNotIncludedInOtherPair(List<String> originTarget, String target) {
        return !originTarget.contains(target);
    }

}
