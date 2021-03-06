package basketball.stats.util;

public class GameTime implements Comparable<GameTime> {

    private int minutes;
    private int seconds;
    private int splitSeconds;

    public GameTime() {

    }

    public GameTime(final int minutes, final int seconds, final int splitSeconds) {
        this.minutes = minutes;
        this.seconds = seconds;
        this.splitSeconds = splitSeconds;
    }

    public GameTime(final String time) {
        final String[] splitTime = time.split(":");
        this.minutes = Integer.parseInt(splitTime[0]);
        this.seconds = Integer.parseInt(splitTime[1]);
        this.splitSeconds = Integer.parseInt(splitTime[2]);
    }

    @Override
    public int compareTo(final GameTime gameTime) {
        final Integer totalSeconds = minutes * 60 + seconds;
        final Integer secondTotalSeconds = gameTime.minutes * 60 + gameTime.seconds;

        if (totalSeconds == secondTotalSeconds) {
            return new Integer(splitSeconds).compareTo(new Integer(gameTime.splitSeconds));
        }

        return totalSeconds.compareTo(secondTotalSeconds);
    }

    public static String formatRealTime(String totalMinutes, String totalSeconds, String totalSplitSeconds) {
        return "" + String.format("%02d", totalMinutes) + ":" + String.format("%02d", totalSeconds) + "." + totalSplitSeconds;
    }

    public static GameTime getTimeDifferential(final GameTime startTime, final GameTime endTime) {
        final GameTime diff = new GameTime();
        if (startTime.getMinutes() == endTime.getMinutes()) {
            diff.setSeconds(endTime.getSeconds() - startTime.getSeconds());
        } else {
            diff.setSeconds(endTime.getSeconds() + (startTime.getSeconds() == 0 ? 0 : 60 - startTime.getSeconds()));
            diff.setMinutes(endTime.getMinutes() - (startTime.getSeconds() == 0 ? startTime.getMinutes() : startTime.getMinutes() + 1));
        }
        return diff;
    }
    
    public int getTotalSeconds() {
        return minutes * 60 + seconds;
    }

    public String getFormattedTime() {
        return this.minutes + ":" + this.seconds + ":" + this.splitSeconds;
    }

    public boolean isFirstQuarter() {
        return minutes >= 0 && minutes < 12;
    }

    public boolean isSecondQuarter() {
        return minutes >= 12 && minutes < 24;
    }

    public boolean isThirdQuarter() {
        return minutes >= 24 && minutes < 36;
    }

    public boolean isFourthQuarter() {
        return minutes >= 36 && minutes < 48;
    }

    public boolean isFirstOvertime() {
        return minutes >= 48 && minutes < 53;
    }

    public boolean isSecondOverTime() {
        return minutes >= 53 && minutes < 58;
    }

    public void addMinutes(final int minutes) {
        this.minutes += minutes;
    }

    public void addSeconds(final int seconds) {
        this.seconds += seconds;
    }

    public void addSplitSeconds(final int splitSeconds) {
        this.splitSeconds += splitSeconds;
    }

    /**
     * @return the minutes
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * @param minutes
     *            the minutes to set
     */
    public void setMinutes(final int minutes) {
        this.minutes = minutes;
    }

    /**
     * @return the seconds
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     * @param seconds
     *            the seconds to set
     */
    public void setSeconds(final int seconds) {
        this.seconds = seconds;
    }

    /**
     * @return the splitSeconds
     */
    public int getSplitSeconds() {
        return splitSeconds;
    }

    /**
     * @param splitSeconds
     *            the splitSeconds to set
     */
    public void setSplitSeconds(final int splitSeconds) {
        this.splitSeconds = splitSeconds;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + minutes;
        result = prime * result + seconds;
        result = prime * result + splitSeconds;
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
        final GameTime other = (GameTime) obj;
        if (minutes != other.minutes) {
            return false;
        }
        if (seconds != other.seconds) {
            return false;
        }
        if (splitSeconds != other.splitSeconds) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + minutes + ":" + seconds + (splitSeconds > 0 ? "." + splitSeconds : "") + "]";
    }

}
