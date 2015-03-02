package basketball.stats.application;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import basketball.stats.enums.Quarter;

public class BasketballTimeTest {

    @Test
    public void setTime_StartFirst() {
        final BasketballTime time = new BasketballTime();
        time.setTime(0, 0, 0);

        assertTime(time, Quarter.FIRST, 12, 0, 0, 0, 0, 0);
    }

    @Test
    public void setTime_StartSecond() {
        final BasketballTime time = new BasketballTime();
        time.setTime(12, 0, 0);

        assertTime(time, Quarter.SECOND, 12, 0, 0, 12, 0, 0);
    }

    @Test
    public void setTime_StartThird() {
        final BasketballTime time = new BasketballTime();
        time.setTime(24, 0, 0);

        assertTime(time, Quarter.THIRD, 12, 0, 0, 24, 0, 0);
    }

    @Test
    public void setTime_StartFourth() {
        final BasketballTime time = new BasketballTime();
        time.setTime(36, 0, 0);

        assertTime(time, Quarter.FOURTH, 12, 0, 0, 36, 0, 0);
    }

    @Test
    public void setTime_StartOvertime() {
        final BasketballTime time = new BasketballTime();
        time.setTime(48, 0, 0);

        assertTime(time, Quarter.OVERTIME, 5, 0, 0, 48, 0, 0);
    }

    @Test
    public void setTime_StartDoubleOvertime() {
        final BasketballTime time = new BasketballTime();
        time.setTime(53, 0, 0);

        assertTime(time, Quarter.DOUBLE_OVERTIME, 5, 0, 0, 53, 0, 0);
    }

    @Test
    public void setTime_FirstSecondIntoFirst() {
        final BasketballTime time = new BasketballTime();
        time.setTime(0, 1, 0);

        assertTime(time, Quarter.FIRST, 11, 59, 0, 0, 1, 0);
    }

    @Test
    public void setTime_FirstSecondIntoSecond() {
        final BasketballTime time = new BasketballTime();
        time.setTime(12, 1, 0);

        assertTime(time, Quarter.SECOND, 11, 59, 0, 12, 1, 0);
    }

    @Test
    public void setTime_FirstSecondIntoThird() {
        final BasketballTime time = new BasketballTime();
        time.setTime(24, 1, 0);

        assertTime(time, Quarter.THIRD, 11, 59, 0, 24, 1, 0);
    }

    @Test
    public void setTime_FirstSecondIntoFourth() {
        final BasketballTime time = new BasketballTime();
        time.setTime(36, 1, 0);

        assertTime(time, Quarter.FOURTH, 11, 59, 0, 36, 1, 0);
    }

    @Test
    public void setTime_FirstSecondIntoOvertime() {
        final BasketballTime time = new BasketballTime();
        time.setTime(48, 1, 0);

        assertTime(time, Quarter.OVERTIME, 4, 59, 0, 48, 1, 0);
    }

    @Test
    public void setTime_FirstSecondIntoDoubleOvertime() {
        final BasketballTime time = new BasketballTime();
        time.setTime(53, 1, 0);

        assertTime(time, Quarter.DOUBLE_OVERTIME, 4, 59, 0, 53, 1, 0);
    }

    @Test
    public void setTime_FirstMinuteIntoFirst() {
        final BasketballTime time = new BasketballTime();
        time.setTime(1, 0, 0);

        assertTime(time, Quarter.FIRST, 11, 0, 0, 1, 0, 0);
    }

    @Test
    public void setTime_FirstMinuteIntoSecond() {
        final BasketballTime time = new BasketballTime();
        time.setTime(13, 0, 0);

        assertTime(time, Quarter.SECOND, 11, 0, 0, 13, 0, 0);
    }

    @Test
    public void setTime_FirstMinuteIntoThird() {
        final BasketballTime time = new BasketballTime();
        time.setTime(25, 0, 0);

        assertTime(time, Quarter.THIRD, 11, 0, 0, 25, 0, 0);
    }

    @Test
    public void setTime_FirstMinuteIntoFourth() {
        final BasketballTime time = new BasketballTime();
        time.setTime(37, 0, 0);

        assertTime(time, Quarter.FOURTH, 11, 0, 0, 37, 0, 0);
    }

    @Test
    public void setTime_FirstMinuteIntoOvertime() {
        final BasketballTime time = new BasketballTime();
        time.setTime(49, 0, 0);

        assertTime(time, Quarter.OVERTIME, 4, 0, 0, 49, 0, 0);
    }

    @Test
    public void setTime_FirstMinuteIntoDoubleOvertime() {
        final BasketballTime time = new BasketballTime();
        time.setTime(54, 0, 0);

        assertTime(time, Quarter.DOUBLE_OVERTIME, 4, 0, 0, 54, 0, 0);
    }

    @Test
    public void setTime_FirstMinuteFirstSecondIntoFirst() {
        final BasketballTime time = new BasketballTime();
        time.setTime(1, 1, 0);

        assertTime(time, Quarter.FIRST, 10, 59, 0, 1, 1, 0);
    }

    @Test
    public void setTime_FirstMinuteFirstSecondIntoSecond() {
        final BasketballTime time = new BasketballTime();
        time.setTime(13, 1, 0);

        assertTime(time, Quarter.SECOND, 10, 59, 0, 13, 1, 0);
    }

    @Test
    public void setTime_FirstMinuteFirstSecondIntoThird() {
        final BasketballTime time = new BasketballTime();
        time.setTime(25, 1, 0);

        assertTime(time, Quarter.THIRD, 10, 59, 0, 25, 1, 0);
    }

    @Test
    public void setTime_FirstMinuteFirstSecondIntoFourth() {
        final BasketballTime time = new BasketballTime();
        time.setTime(37, 1, 0);

        assertTime(time, Quarter.FOURTH, 10, 59, 0, 37, 1, 0);
    }

    @Test
    public void setTime_FirstMinuteFirstSecondIntoOvertime() {
        final BasketballTime time = new BasketballTime();
        time.setTime(49, 1, 0);

        assertTime(time, Quarter.OVERTIME, 3, 59, 0, 49, 1, 0);
    }

    @Test
    public void setTime_FirstMinuteFirstSecondIntoDoubleOvertime() {
        final BasketballTime time = new BasketballTime();
        time.setTime(54, 1, 0);

        assertTime(time, Quarter.DOUBLE_OVERTIME, 3, 59, 0, 54, 1, 0);
    }

    @Test
    public void setTime_SixtyOneSecondsRemainingInFirst() {
        final BasketballTime time = new BasketballTime();
        time.setTime(10, 59, 0);

        assertTime(time, Quarter.FIRST, 1, 1, 0, 10, 59, 0);
    }

    @Test
    public void setTime_SixtyOneSecondsRemainingInSecond() {
        final BasketballTime time = new BasketballTime();
        time.setTime(22, 59, 0);

        assertTime(time, Quarter.SECOND, 1, 1, 0, 22, 59, 0);
    }

    @Test
    public void setTime_SixtyOneSecondsRemainingInThird() {
        final BasketballTime time = new BasketballTime();
        time.setTime(34, 59, 0);

        assertTime(time, Quarter.THIRD, 1, 1, 0, 34, 59, 0);
    }

    @Test
    public void setTime_SixtyOneSecondsRemainingInFourth() {
        final BasketballTime time = new BasketballTime();
        time.setTime(46, 59, 0);

        assertTime(time, Quarter.FOURTH, 1, 1, 0, 46, 59, 0);
    }

    @Test
    public void setTime_SixtyOneSecondsRemainingInOvertime() {
        final BasketballTime time = new BasketballTime();
        time.setTime(51, 59, 0);

        assertTime(time, Quarter.OVERTIME, 1, 1, 0, 51, 59, 0);
    }

    @Test
    public void setTime_SixtyOneSecondsRemainingInDoubleOvertime() {
        final BasketballTime time = new BasketballTime();
        time.setTime(56, 59, 0);

        assertTime(time, Quarter.DOUBLE_OVERTIME, 1, 1, 0, 56, 59, 0);
    }

    @Test
    public void setTime_LastMinuteInFirst() {
        final BasketballTime time = new BasketballTime();
        time.setTime(11, 0, 0);

        assertTime(time, Quarter.FIRST, 1, 0, 0, 11, 0, 0);
    }

    @Test
    public void setTime_LastMinuteInSecond() {
        final BasketballTime time = new BasketballTime();
        time.setTime(23, 0, 0);

        assertTime(time, Quarter.SECOND, 1, 0, 0, 23, 0, 0);
    }

    @Test
    public void setTime_LastMinuteInThird() {
        final BasketballTime time = new BasketballTime();
        time.setTime(35, 0, 0);

        assertTime(time, Quarter.THIRD, 1, 0, 0, 35, 0, 0);
    }

    @Test
    public void setTime_LastMinuteInFourth() {
        final BasketballTime time = new BasketballTime();
        time.setTime(47, 0, 0);

        assertTime(time, Quarter.FOURTH, 1, 0, 0, 47, 0, 0);
    }

    @Test
    public void setTime_LastMinuteInOvertime() {
        final BasketballTime time = new BasketballTime();
        time.setTime(52, 0, 0);

        assertTime(time, Quarter.OVERTIME, 1, 0, 0, 52, 0, 0);
    }

    @Test
    public void setTime_LastMinuteInDoubleOvertime() {
        final BasketballTime time = new BasketballTime();
        time.setTime(57, 0, 0);

        assertTime(time, Quarter.DOUBLE_OVERTIME, 1, 0, 0, 57, 0, 0);
    }

    @Test
    public void setTime_FiftyNineSecondsRemainingInFirst() {
        final BasketballTime time = new BasketballTime();
        time.setTime(11, 1, 0);

        assertTime(time, Quarter.FIRST, 0, 59, 0, 11, 1, 0);
    }

    @Test
    public void setTime_FiftyNineSecondsRemainingInSecond() {
        final BasketballTime time = new BasketballTime();
        time.setTime(23, 1, 0);

        assertTime(time, Quarter.SECOND, 0, 59, 0, 23, 1, 0);
    }

    @Test
    public void setTime_FiftyNineSecondsRemainingInThird() {
        final BasketballTime time = new BasketballTime();
        time.setTime(35, 1, 0);

        assertTime(time, Quarter.THIRD, 0, 59, 0, 35, 1, 0);
    }

    @Test
    public void setTime_FiftyNineSecondsRemainingInFourth() {
        final BasketballTime time = new BasketballTime();
        time.setTime(47, 1, 0);

        assertTime(time, Quarter.FOURTH, 0, 59, 0, 47, 1, 0);
    }

    @Test
    public void setTime_FiftyNineSecondsRemainingInOvertime() {
        final BasketballTime time = new BasketballTime();
        time.setTime(52, 1, 0);

        assertTime(time, Quarter.OVERTIME, 0, 59, 0, 52, 1, 0);
    }

    @Test
    public void setTime_FiftyNineSecondsRemainingInDoubleOvertime() {
        final BasketballTime time = new BasketballTime();
        time.setTime(57, 1, 0);

        assertTime(time, Quarter.DOUBLE_OVERTIME, 0, 59, 0, 57, 1, 0);
    }

    @Test
    public void decrement_StartFirst() {
        final BasketballTime time = new BasketballTime();
        time.setTime(0, 0, 0);
        time.decrement();

        assertTime(time, Quarter.FIRST, 11, 59, 0, 0, 1, 0);
    }

    @Test
    public void decrement_StartSecond() {
        final BasketballTime time = new BasketballTime();
        time.setTime(12, 0, 0);
        time.decrement();

        assertTime(time, Quarter.SECOND, 11, 59, 0, 12, 1, 0);
    }

    @Test
    public void decrement_StartThird() {
        final BasketballTime time = new BasketballTime();
        time.setTime(24, 0, 0);
        time.decrement();

        assertTime(time, Quarter.THIRD, 11, 59, 0, 24, 1, 0);
    }

    @Test
    public void decrement_StartFourth() {
        final BasketballTime time = new BasketballTime();
        time.setTime(36, 0, 0);
        time.decrement();

        assertTime(time, Quarter.FOURTH, 11, 59, 0, 36, 1, 0);
    }

    @Test
    public void decrement_StartOvertime() {
        final BasketballTime time = new BasketballTime();
        time.setTime(48, 0, 0);
        time.decrement();

        assertTime(time, Quarter.OVERTIME, 4, 59, 0, 48, 1, 0);
    }

    @Test
    public void decrement_StartDoubleOvertime() {
        final BasketballTime time = new BasketballTime();
        time.setTime(53, 0, 0);
        time.decrement();

        assertTime(time, Quarter.DOUBLE_OVERTIME, 4, 59, 0, 53, 1, 0);
    }

    @Test
    public void decrement_FiftyNineSecondsInFirst() {
        final BasketballTime time = new BasketballTime();
        time.setTime(0, 59, 0);
        time.decrement();

        assertTime(time, Quarter.FIRST, 11, 0, 0, 1, 0, 0);
    }

    @Test
    public void decrement_FiftyNineSecondsInSecond() {
        final BasketballTime time = new BasketballTime();
        time.setTime(12, 59, 0);
        time.decrement();

        assertTime(time, Quarter.SECOND, 11, 0, 0, 13, 0, 0);
    }

    @Test
    public void decrement_FiftyNineSecondsInThird() {
        final BasketballTime time = new BasketballTime();
        time.setTime(24, 59, 0);
        time.decrement();

        assertTime(time, Quarter.THIRD, 11, 0, 0, 25, 0, 0);
    }

    @Test
    public void decrement_FiftyNineSecondsInFourth() {
        final BasketballTime time = new BasketballTime();
        time.setTime(36, 59, 0);
        time.decrement();

        assertTime(time, Quarter.FOURTH, 11, 0, 0, 37, 0, 0);
    }

    @Test
    public void decrement_FiftyNineSecondsInOvertime() {
        final BasketballTime time = new BasketballTime();
        time.setTime(48, 59, 0);
        time.decrement();

        assertTime(time, Quarter.OVERTIME, 4, 0, 0, 49, 0, 0);
    }

    @Test
    public void decrement_FiftyNineSecondsInDoubleOvertime() {
        final BasketballTime time = new BasketballTime();
        time.setTime(53, 59, 0);
        time.decrement();

        assertTime(time, Quarter.DOUBLE_OVERTIME, 4, 0, 0, 54, 0, 0);
    }

    @Test
    public void decrement_OneMinuteInFirst() {
        final BasketballTime time = new BasketballTime();
        time.setTime(1, 0, 0);
        time.decrement();

        assertTime(time, Quarter.FIRST, 10, 59, 0, 1, 1, 0);
    }

    @Test
    public void decrement_OneMinuteInSecond() {
        final BasketballTime time = new BasketballTime();
        time.setTime(13, 0, 0);
        time.decrement();

        assertTime(time, Quarter.SECOND, 10, 59, 0, 13, 1, 0);
    }

    @Test
    public void decrement_OneMinuteInThird() {
        final BasketballTime time = new BasketballTime();
        time.setTime(25, 0, 0);
        time.decrement();

        assertTime(time, Quarter.THIRD, 10, 59, 0, 25, 1, 0);
    }

    @Test
    public void decrement_OneMinuteInFourth() {
        final BasketballTime time = new BasketballTime();
        time.setTime(37, 0, 0);
        time.decrement();

        assertTime(time, Quarter.FOURTH, 10, 59, 0, 37, 1, 0);
    }

    @Test
    public void decrement_OneMinuteInOvertime() {
        final BasketballTime time = new BasketballTime();
        time.setTime(49, 0, 0);
        time.decrement();

        assertTime(time, Quarter.OVERTIME, 3, 59, 0, 49, 1, 0);
    }

    @Test
    public void decrement_OneMinuteInDoubleOvertime() {
        final BasketballTime time = new BasketballTime();
        time.setTime(54, 0, 0);
        time.decrement();

        assertTime(time, Quarter.DOUBLE_OVERTIME, 3, 59, 0, 54, 1, 0);
    }

    @Test
    public void decrement_SixtyOneSecondsInFirst() {
        final BasketballTime time = new BasketballTime();
        time.setTime(1, 1, 0);
        time.decrement();

        assertTime(time, Quarter.FIRST, 10, 58, 0, 1, 2, 0);
    }

    @Test
    public void decrement_SixtyOneSecondsInSecond() {
        final BasketballTime time = new BasketballTime();
        time.setTime(13, 1, 0);
        time.decrement();

        assertTime(time, Quarter.SECOND, 10, 58, 0, 13, 2, 0);
    }

    @Test
    public void decrement_SixtyOneSecondsInThird() {
        final BasketballTime time = new BasketballTime();
        time.setTime(25, 1, 0);
        time.decrement();

        assertTime(time, Quarter.THIRD, 10, 58, 0, 25, 2, 0);
    }

    @Test
    public void decrement_SixtyOneSecondsInFourth() {
        final BasketballTime time = new BasketballTime();
        time.setTime(37, 1, 0);
        time.decrement();

        assertTime(time, Quarter.FOURTH, 10, 58, 0, 37, 2, 0);
    }

    @Test
    public void decrement_SixtyOneSecondsInOvertime() {
        final BasketballTime time = new BasketballTime();
        time.setTime(49, 1, 0);
        time.decrement();

        assertTime(time, Quarter.OVERTIME, 3, 58, 0, 49, 2, 0);
    }

    @Test
    public void decrement_SixtyOneSecondsInDoubleOvertime() {
        final BasketballTime time = new BasketballTime();
        time.setTime(54, 1, 0);
        time.decrement();

        assertTime(time, Quarter.DOUBLE_OVERTIME, 3, 58, 0, 54, 2, 0);
    }

    @Test
    public void decrement_SixtyOneSecondsLeftInFirst() {
        final BasketballTime time = new BasketballTime();
        time.setTime(10, 59, 0);
        time.decrement();

        assertTime(time, Quarter.FIRST, 1, 0, 0, 11, 0, 0);
    }

    @Test
    public void decrement_SixtyOneSecondsLeftInSecond() {
        final BasketballTime time = new BasketballTime();
        time.setTime(22, 59, 0);
        time.decrement();

        assertTime(time, Quarter.SECOND, 1, 0, 0, 23, 0, 0);
    }

    @Test
    public void decrement_SixtyOneSecondsLeftInThird() {
        final BasketballTime time = new BasketballTime();
        time.setTime(34, 59, 0);
        time.decrement();

        assertTime(time, Quarter.THIRD, 1, 0, 0, 35, 0, 0);
    }

    @Test
    public void decrement_SixtyOneSecondsLeftInFourth() {
        final BasketballTime time = new BasketballTime();
        time.setTime(46, 59, 0);
        time.decrement();

        assertTime(time, Quarter.FOURTH, 1, 0, 0, 47, 0, 0);
    }

    @Test
    public void decrement_SixtyOneSecondsLeftInOvertime() {
        final BasketballTime time = new BasketballTime();
        time.setTime(51, 59, 0);
        time.decrement();

        assertTime(time, Quarter.OVERTIME, 1, 0, 0, 52, 0, 0);
    }

    @Test
    public void decrement_SixtyOneSecondsLeftInDoubleOvertime() {
        final BasketballTime time = new BasketballTime();
        time.setTime(56, 59, 0);
        time.decrement();

        assertTime(time, Quarter.DOUBLE_OVERTIME, 1, 0, 0, 57, 0, 0);
    }

    @Test
    public void decrement_SixtySecondsLeftInFirst() {
        final BasketballTime time = new BasketballTime();
        time.setTime(11, 0, 0);
        time.decrement();

        assertTime(time, Quarter.FIRST, 0, 59, 0, 11, 1, 0);
    }

    @Test
    public void decrement_SixtySecondsLeftInSecond() {
        final BasketballTime time = new BasketballTime();
        time.setTime(23, 0, 0);
        time.decrement();

        assertTime(time, Quarter.SECOND, 0, 59, 0, 23, 1, 0);
    }

    @Test
    public void decrement_SixtySecondsLeftInThird() {
        final BasketballTime time = new BasketballTime();
        time.setTime(35, 0, 0);
        time.decrement();

        assertTime(time, Quarter.THIRD, 0, 59, 0, 35, 1, 0);
    }

    @Test
    public void decrement_SixtySecondsLeftInFourth() {
        final BasketballTime time = new BasketballTime();
        time.setTime(47, 0, 0);
        time.decrement();

        assertTime(time, Quarter.FOURTH, 0, 59, 0, 47, 1, 0);
    }

    @Test
    public void decrement_SixtySecondsLeftInOvertime() {
        final BasketballTime time = new BasketballTime();
        time.setTime(52, 0, 0);
        time.decrement();

        assertTime(time, Quarter.OVERTIME, 0, 59, 0, 52, 1, 0);
    }

    @Test
    public void decrement_SixtySecondsLeftInDoubleOvertime() {
        final BasketballTime time = new BasketballTime();
        time.setTime(57, 0, 0);
        time.decrement();

        assertTime(time, Quarter.DOUBLE_OVERTIME, 0, 59, 0, 57, 1, 0);
    }

    @Test
    public void decrement_OneSecondInFirst() {
        final BasketballTime time = new BasketballTime();
        time.setTime(11, 59, 0);
        time.decrement();

        assertTime(time, Quarter.SECOND, 12, 0, 0, 12, 0, 0);
    }

    @Test
    public void decrement_OneSecondInSecond() {
        final BasketballTime time = new BasketballTime();
        time.setTime(23, 59, 0);
        time.decrement();

        assertTime(time, Quarter.THIRD, 12, 0, 0, 24, 0, 0);
    }

    @Test
    public void decrement_OneSecondInThird() {
        final BasketballTime time = new BasketballTime();
        time.setTime(35, 59, 0);
        time.decrement();

        assertTime(time, Quarter.FOURTH, 12, 0, 0, 36, 0, 0);
    }

    @Test
    public void decrement_OneSecondInFourth() {
        final BasketballTime time = new BasketballTime();
        time.setTime(47, 59, 0);
        time.decrement();

        assertTime(time, Quarter.OVERTIME, 5, 0, 0, 48, 0, 0);
    }

    @Test
    public void decrement_OneSecondInOvertime() {
        final BasketballTime time = new BasketballTime();
        time.setTime(52, 59, 0);
        time.decrement();

        assertTime(time, Quarter.DOUBLE_OVERTIME, 5, 0, 0, 53, 0, 0);
    }

    @Test
    public void decrement_OneSecondInDoubleOvertime() {
        final BasketballTime time = new BasketballTime();
        time.setTime(57, 59, 0);
        time.decrement();

        assertTime(time, Quarter.TRIPLE_OVERTIME, 5, 0, 0, 58, 0, 0);
    }

    public void assertTime(final BasketballTime time, final Quarter quarter, final int remainingMinutes, final int remainingSeconds, final int remainingSplitSeconds,
            final int totalMinutes, final int totalSeconds, final int totalSplitSeconds) {
        assertEquals(quarter, time.getQuarter());
        assertEquals(remainingMinutes, time.getRemainingMinutes());
        assertEquals(remainingSeconds, time.getRemainingSeconds());
        assertEquals(remainingSplitSeconds, time.getRemainingSplitSeconds());
        assertEquals(totalMinutes, time.getTotalMinutes());
        assertEquals(totalSeconds, time.getTotalSeconds());
        assertEquals(totalSplitSeconds, time.getTotalSplitSeconds());
    }
}
