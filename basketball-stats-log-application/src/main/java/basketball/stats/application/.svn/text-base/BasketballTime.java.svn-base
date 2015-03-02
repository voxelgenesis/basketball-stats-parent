package basketball.stats.application;

import basketball.stats.enums.Quarter;

public class BasketballTime {

    private Quarter quarter;
    private int remainingMinutes;
    private int remainingSeconds;
    private int remainingSplitSeconds;
    private int totalMinutes;
    private int totalSeconds;
    private int totalSplitSeconds;

    public BasketballTime() {
        this.quarter = Quarter.FIRST;
        this.remainingMinutes = 12;
        this.remainingSeconds = 0;
    }

    public BasketballTime(final int totalMinutes, final int totalSeconds, final int totalSplitSeconds) {
        setTime(totalMinutes, totalSeconds, totalSplitSeconds);
    }

    public void setTime(final int totalMinutes, final int totalSeconds, final int totalSplitSeconds) {
        this.totalMinutes = totalMinutes;
        this.totalSeconds = totalSeconds;
        this.totalSplitSeconds = totalSplitSeconds;
        if (totalMinutes <= 11) {
            quarter = Quarter.FIRST;
        } else if (totalMinutes < 24) {
            quarter = Quarter.SECOND;
        } else if (totalMinutes < 36) {
            quarter = Quarter.THIRD;
        } else if (totalMinutes < 48) {
            quarter = Quarter.FOURTH;
        } else if (totalMinutes < 53) {
            quarter = Quarter.OVERTIME;
        } else if (totalMinutes < 58) {
            quarter = Quarter.DOUBLE_OVERTIME;
        } else {
            quarter = Quarter.TRIPLE_OVERTIME;
        }
        this.remainingSplitSeconds = totalSplitSeconds == 0 ? 0 : 9 - totalSplitSeconds;
        if (totalSeconds == 0) {
            this.remainingSeconds = 0;
        } else {
            this.remainingSeconds = 60 - totalSeconds;
        }
        if (totalMinutes == quarter.getStartMinutes() && totalSeconds == 0) {
            this.remainingMinutes = 12;
        } else if (totalMinutes == quarter.getStartMinutes() && totalSeconds > 0) {
            this.remainingMinutes = 11;
        } else if (totalMinutes > quarter.getStartMinutes() && totalMinutes < (quarter.getStartMinutes() + 12) && totalSeconds == 0) {
            this.remainingMinutes = 12 - (totalMinutes - quarter.getStartMinutes());
        } else if (totalMinutes > quarter.getStartMinutes() && totalMinutes < (quarter.getStartMinutes() + 12) && totalSeconds > 0) {
            this.remainingMinutes = 11 - (totalMinutes - quarter.getStartMinutes());
        }
        if (quarter.getStartMinutes() >= 48) {
            this.remainingMinutes = this.remainingMinutes - 7;
        }
    }

    private void incrementTotalTime() {
        if (totalSeconds == 59) {
            totalMinutes++;
            totalSeconds = 0;
            totalSplitSeconds = 0;
            return;
        }

        totalSeconds++;
    }

    private void incrementTotalSplitSecondTime() {
        if (totalSplitSeconds == 9) {
            totalSplitSeconds = 0;
            incrementTotalTime();
            return;
        }

        totalSplitSeconds++;
        return;
    }

    public void decrement() {
        incrementTotalTime();
        if (remainingMinutes == 0 && remainingSeconds == 0) {
            if (quarter == Quarter.FIRST) {
                quarter = Quarter.SECOND;
                remainingMinutes = 12;
            } else if (quarter == Quarter.SECOND) {
                quarter = Quarter.THIRD;
                remainingMinutes = 12;
            } else if (quarter == Quarter.THIRD) {
                quarter = Quarter.FOURTH;
                remainingMinutes = 12;
            } else if (quarter == Quarter.FOURTH) {
                quarter = Quarter.OVERTIME;
                remainingMinutes = 5;
            } else if (quarter == Quarter.OVERTIME) {
                quarter = Quarter.DOUBLE_OVERTIME;
                remainingMinutes = 5;
            } else if (quarter == Quarter.DOUBLE_OVERTIME) {
                quarter = Quarter.TRIPLE_OVERTIME;
                remainingMinutes = 5;
            }
            remainingSeconds = 0;
            remainingSplitSeconds = 0;
            return;
        }

        if (remainingMinutes == 12 && remainingSeconds == 0) {
            remainingMinutes = 11;
            remainingSeconds = 59;
            return;
        } else if (remainingMinutes == 5 && remainingSeconds == 0
                && (quarter == Quarter.OVERTIME || quarter == Quarter.DOUBLE_OVERTIME || quarter == Quarter.TRIPLE_OVERTIME)) {
            remainingMinutes = 4;
            remainingSeconds = 59;
            return;
        }

        if (remainingSeconds == 0) {
            remainingMinutes = remainingMinutes - 1;
            remainingSeconds = 59;
            return;
        }

        remainingSeconds--;
        if (remainingMinutes == 0 && remainingSeconds == 0) {
            if (quarter == Quarter.FIRST) {
                quarter = Quarter.SECOND;
                remainingMinutes = 12;
            } else if (quarter == Quarter.SECOND) {
                quarter = Quarter.THIRD;
                remainingMinutes = 12;
            } else if (quarter == Quarter.THIRD) {
                quarter = Quarter.FOURTH;
                remainingMinutes = 12;
            } else if (quarter == Quarter.FOURTH) {
                quarter = Quarter.OVERTIME;
                remainingMinutes = 5;
            } else if (quarter == Quarter.OVERTIME) {
                quarter = Quarter.DOUBLE_OVERTIME;
                remainingMinutes = 5;
            } else if (quarter == Quarter.DOUBLE_OVERTIME) {
                quarter = Quarter.TRIPLE_OVERTIME;
                remainingMinutes = 5;
            }
            remainingSeconds = 0;
            remainingSplitSeconds = 0;
            return;
        }

    }

    public void decrementSplitSecond() {
        if (remainingMinutes != 0 || remainingSeconds > 1) {
            return;
        }
        if (remainingSeconds == 1 && remainingSplitSeconds != 0) {
            return;
        }
        if (remainingSeconds == 1) {
            remainingSeconds = 0;
            remainingSplitSeconds = 9;
            incrementTotalSplitSecondTime();
            return;
        }
        if (remainingSeconds == 0 && remainingSplitSeconds > 0) {
            remainingSplitSeconds = remainingSplitSeconds - 1;
            incrementTotalSplitSecondTime();
        }

        if (remainingSeconds == 0 && remainingSplitSeconds == 0) {
            if (quarter == Quarter.FIRST) {
                quarter = Quarter.SECOND;
                remainingMinutes = 12;
                remainingSeconds = 0;
            } else if (quarter == Quarter.SECOND) {
                quarter = Quarter.THIRD;
                remainingMinutes = 12;
                remainingSeconds = 0;
            } else if (quarter == Quarter.THIRD) {
                quarter = Quarter.FOURTH;
                remainingMinutes = 12;
                remainingSeconds = 0;
            } else if (quarter == Quarter.FOURTH) {
                quarter = Quarter.OVERTIME;
                remainingMinutes = 5;
                remainingSeconds = 0;
            } else if (quarter == Quarter.OVERTIME) {
                quarter = Quarter.DOUBLE_OVERTIME;
                remainingMinutes = 5;
                remainingSeconds = 0;
            } else if (quarter == Quarter.DOUBLE_OVERTIME) {
                quarter = Quarter.TRIPLE_OVERTIME;
                remainingMinutes = 5;
                remainingSeconds = 0;
            }
        }
    }

    public String formatRealTime() {
        return "" + String.format("%02d", totalMinutes) + ":" + String.format("%02d", totalSeconds) + "." + totalSplitSeconds;
    }

    public String formatRemainingTime() {
        return quarter.getQuarter() + ":" + String.format("%02d", remainingMinutes) + ":" + String.format("%02d", remainingSeconds) + "." + remainingSplitSeconds;
    }

    public Quarter getQuarter() {
        return quarter;
    }

    public void setQuarter(final Quarter quarter) {
        this.quarter = quarter;
    }

    public int getRemainingMinutes() {
        return remainingMinutes;
    }

    public void setRemainingMinutes(final int remainingMinutes) {
        this.remainingMinutes = remainingMinutes;
    }

    public int getRemainingSeconds() {
        return remainingSeconds;
    }

    public void setRemainingSeconds(final int remainingSeconds) {
        this.remainingSeconds = remainingSeconds;
    }

    public int getRemainingSplitSeconds() {
        return remainingSplitSeconds;
    }

    public void setRemainingSplitSeconds(final int remainingSplitSeconds) {
        this.remainingSplitSeconds = remainingSplitSeconds;
    }

    public int getTotalMinutes() {
        return totalMinutes;
    }

    public void setTotalMinutes(final int totalMinutes) {
        this.totalMinutes = totalMinutes;
    }

    public int getTotalSeconds() {
        return totalSeconds;
    }

    public void setTotalSeconds(final int totalSeconds) {
        this.totalSeconds = totalSeconds;
    }

    public int getTotalSplitSeconds() {
        return totalSplitSeconds;
    }

    public void setTotalSplitSeconds(final int totalSplitSeconds) {
        this.totalSplitSeconds = totalSplitSeconds;
    }

}
