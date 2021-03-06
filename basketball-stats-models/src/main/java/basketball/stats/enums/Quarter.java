package basketball.stats.enums;

public enum Quarter {

    FIRST(0, "Q1"), SECOND(12, "Q2"), THIRD(24, "Q3"), FOURTH(36, "Q4"), OVERTIME(48, "OT"), DOUBLE_OVERTIME(53, "2OT"), TRIPLE_OVERTIME(58, "3OT");

    private int startMinutes;
    private String quarter;

    private Quarter(final int startMinutes, final String quarter) {
        this.startMinutes = startMinutes;
        this.quarter = quarter;
    }

    public int getStartMinutes() {
        return this.startMinutes;
    }

    public String getQuarter() {
        return this.quarter;
    }
}
