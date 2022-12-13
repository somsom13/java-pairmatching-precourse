package pairmatching.domain;

import java.util.Objects;

public class MissionDetail {
    private String missionName;
    private String level;
    private String course;

    public MissionDetail(String mission, String level, String course) {
        this.missionName = mission;
        this.level = level;
        this.course = course;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MissionDetail mission = (MissionDetail) obj;
        return isSameMission(mission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(missionName, level, course);
    }

    private boolean isSameMission(MissionDetail mission) {
        return mission.missionName.equals(this.missionName)
                && mission.level.equals(this.level)
                && mission.course.equals(course);
    }
}
