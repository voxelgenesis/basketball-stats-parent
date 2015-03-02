package basketball.stats.models;

import basketball.stats.util.GameTime;

public class TimeDivision {

    private GameTime startTime;
    private GameTime endTime;
    private String name;

    public TimeDivision() {

    }

    public TimeDivision(final GameTime startTime, final GameTime endTime, final String name) {
        super();
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
    }

    public GameTime getStartTime() {
        return startTime;
    }

    public void setStartTime(final GameTime startTime) {
        this.startTime = startTime;
    }

    public GameTime getEndTime() {
        return endTime;
    }

    public void setEndTime(final GameTime endTime) {
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TimeDivision other = (TimeDivision) obj;
        if (endTime == null) {
            if (other.endTime != null) {
                return false;
            }
        } else if (!endTime.equals(other.endTime)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (startTime == null) {
            if (other.startTime != null) {
                return false;
            }
        } else if (!startTime.equals(other.startTime)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TimeDivision [" + (startTime != null ? "startTime=" + startTime + ", " : "") + (endTime != null ? "endTime=" + endTime + ", " : "")
                + (name != null ? "name=" + name : "") + "]";
    }

}
