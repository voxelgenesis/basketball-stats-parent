package basketball.stats.processor;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import basketball.stats.chunker.GameTimePlayerStatsEntityChunker;
import basketball.stats.models.EntityChunk;
import basketball.stats.models.LogEntry;
import basketball.stats.models.TimeDivision;
import basketball.stats.processor.utils.BoxScore;
import basketball.stats.processor.utils.EntityChunkProcessor;
import basketball.stats.processor.utils.ProcessingUtils;
import basketball.stats.util.GameTime;

public class LogProcessorTest {

    @Test
    public void test2() {
        final LogProcessor proc = new LogProcessor();
        // final List<LogEntry> log = ProcessingUtils.buildLogEntries("src/test/resources/test.bbl");
        final List<LogEntry> log = ProcessingUtils.buildLogEntries("C:/Users/Anonymous/Desktop/games/basketballStats-HOME-CHICAGO_BULLS-S1-G1.bbl");
        final TimeDivision firstQuarter = new TimeDivision(new GameTime(0, 0, 0), new GameTime(12, 0, 0), "First Quarter");
        final TimeDivision secondQuarter = new TimeDivision(new GameTime(12, 0, 0), new GameTime(24, 0, 0), "Second Quarter");
        final TimeDivision thirdQuarter = new TimeDivision(new GameTime(24, 0, 0), new GameTime(36, 0, 0), "Third Quarter");
        final TimeDivision fourthQuarter = new TimeDivision(new GameTime(36, 0, 0), new GameTime(48, 0, 0), "Fourth Quarter");
        final TimeDivision overtimeQuarter = new TimeDivision(new GameTime(48, 0, 0), new GameTime(53, 0, 0), "Overtime");
        final GameTimePlayerStatsEntityChunker chunker = new GameTimePlayerStatsEntityChunker(new GameTime(), new GameTime(60, 0, 0));

        final Map<TimeDivision, Map<String, EntityChunk>> result = proc.processTimeDivisions(chunker, log,
                Arrays.asList(firstQuarter, secondQuarter, thirdQuarter, fourthQuarter, overtimeQuarter));
        for (final Entry<TimeDivision, Map<String, EntityChunk>> division : result.entrySet()) {
            System.out.println(division.getKey().getName());
            BoxScore.boxScore(division.getValue(), 1);
        }

    }

    @Test
    public void test1() {
        final LogProcessor proc = new LogProcessor();
        final List<LogEntry> log = ProcessingUtils.buildLogEntries("src/test/resources/test.bbl");
        // final List<LogEntry> log = ProcessingUtils.buildLogEntries("C:/Users/Anonymous/Desktop/games/basketballStats-HOME-CHICAGO_BULLS-S1-G1.bbl");
        final TimeDivision fullGame = new TimeDivision(new GameTime(0, 0, 0), new GameTime(60, 0, 0), "Full Game");
        final GameTimePlayerStatsEntityChunker chunker = new GameTimePlayerStatsEntityChunker(new GameTime(), new GameTime(60, 0, 0));

        final Map<String, EntityChunk> data = proc.processTimePeriod(chunker, fullGame, log);
        final EntityChunkProcessor ecp = new EntityChunkProcessor(data);
        assertBoxScore(ecp, "DURANT_KEVIN", 2981, 13, 1, 2, 0, 0, 3, 3, 1, 2, 1, 1, 0, 3, 2, 1, 4, 2, 1, 1, 0, 0, 0, 1, 1, 0);
        assertBoxScore(ecp, "BLEDSOE_ERIC", 2318, 3, 1, 1, 0, 1, 2, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1, 2, 0, 0, 0, 1, 1, 1, 0);
        assertBoxScore(ecp, "GREEN_WILLIE", 246, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        assertBoxScore(ecp, "AZUBUKKE_KELENNA", 466, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, -2, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        assertBoxScore(ecp, "ANDERSON_RYAN", 875, 3, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 2, 1, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0);
        assertBoxScore(ecp, "DAVIS_ANTHONY", 2455, 4, 0, 1, 2, 0, 0, 2, 1, 0, 0, 0, 2, 1, 1, 1, 3, 1, 1, 2, 0, 1, 0, 0, 1, 0);
        assertBoxScore(ecp, "THABEET_HASHEEM", 199, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        assertBoxScore(ecp, "UNGUARDED", 0, 4, 0, 0, 0, 0, 0, 2, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        assertBoxScore(ecp, "MULTIPLE", 0, 3, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        assertBoxScore(ecp, "TEAM", 0, 23, 2, 5, 2, 2, 6, 5, 4, 4, 1, 1, 2, 4, 6, 3, 0, 5, 5, 3, 0, 1, 1, 2, 3, 0);
        assertBoxScore(ecp, "OPPONENT", 0, 21, 0, 0, 3, 3, 3, 7, 5, 1, 2, 4, 1, 0, 3, 2, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1);

        BoxScore.boxScore(data, 1);
    }

    private void assertBoxScore(final EntityChunkProcessor proc, String key, int minutes, int points, int defensiveRebounds, int assists, int blocks, int steals,
            int turnovers, int twoPointFieldGoalsMade, int twoPointFieldGoalsMissed, int threesMade, int threesMissed, int freeThrowsMade, int freeThrowsMissed,
            int offensiveRebounds, int fouls, int shootingFouls, int plusMinus, int assistedFieldGoals, int opponentMadeFieldGoals, int opponentMissedFieldGoals,
            int opponentMadeThrees, int opponentMissedThrees, int deflectionsSelf, int deflectionsCaused, int blockedTwoPointShots, int blockedThreePointShots) {

        assertEquals(minutes, proc.sum(key, "TOTAL_TIME"));
        assertEquals(points, proc.sum(key, "POINTS"));
        assertEquals(defensiveRebounds, proc.count(key, "DEFENSIVE_REBOUNDS"));
        assertEquals(offensiveRebounds, proc.count(key, "OFFENSIVE_REBOUNDS"));
        assertEquals(assists, proc.count(key, "ASSISTS"));
        assertEquals(blocks, proc.count(key, "BLOCKS"));
        assertEquals(steals, proc.count(key, "STEALS"));
        assertEquals(turnovers, proc.count(key, "TURNOVERS"));
        assertEquals(twoPointFieldGoalsMade, proc.count(key, "MADE_TWO_POINT_SHOTS") + proc.count(key, "MADE_ASSISTED_TWO_POINT_SHOTS"));
        assertEquals(twoPointFieldGoalsMissed, proc.count(key, "MISSED_TWO_POINT_SHOTS") + proc.count(key, "MISSED_BLOCKED_TWO_POINT_SHOTS"));
        assertEquals(threesMade, proc.count(key, "MADE_THREE_POINT_SHOTS") + proc.count(key, "MADE_ASSISTED_THREE_POINT_SHOTS"));
        assertEquals(threesMissed, proc.count(key, "MISSED_THREE_POINT_SHOTS") + proc.count(key, "MISSED_BLOCKED_THREE_POINT_SHOTS"));
        assertEquals(freeThrowsMade, proc.count(key, "MADE_FREE_THROWS"));
        assertEquals(freeThrowsMissed, proc.count(key, "MISSED_FREE_THROWS"));
        assertEquals(fouls, proc.count(key, "FOULS"));
        assertEquals(shootingFouls, proc.count(key, "SHOOTING_FOULS"));
        assertEquals(deflectionsSelf, proc.count(key, "DEFLECTIONS_SELF"));
        assertEquals(deflectionsCaused, proc.count(key, "DEFLECTIONS_CAUSED"));
        assertEquals(assistedFieldGoals, proc.count(key, "MADE_ASSISTED_TWO_POINT_SHOTS") + proc.count(key, "MADE_ASSISTED_THREE_POINT_SHOTS"));
        assertEquals(opponentMadeFieldGoals, proc.count(key, "OPPONENT_MADE_TWO_POINT_SHOTS"));
        assertEquals(opponentMissedFieldGoals, proc.count(key, "OPPONENT_MISSED_TWO_POINT_SHOTS") + proc.count(key, "OPPONENT_BLOCKED_TWO_POINT_SHOTS"));
        assertEquals(opponentMadeThrees, proc.count(key, "OPPONENT_MADE_THREE_POINT_SHOTS"));
        assertEquals(opponentMissedThrees, proc.count(key, "OPPONENT_MISSED_THREE_POINT_SHOTS") + proc.count(key, "OPPONENT_BLOCKED_THREE_POINT_SHOTS"));
        assertEquals(blockedTwoPointShots, proc.count(key, "MISSED_BLOCKED_TWO_POINT_SHOTS"));
        assertEquals(blockedThreePointShots, proc.count(key, "MISSED_BLOCKED_THREE_POINT_SHOTS"));
        assertEquals(plusMinus, proc.sum(key, "TOTAL_DIFF"));
    }
}
