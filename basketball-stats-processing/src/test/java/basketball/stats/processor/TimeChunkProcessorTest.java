package basketball.stats.processor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import basketball.stats.chunker.GameTimeChunker;
import basketball.stats.enums.Players;
import basketball.stats.models.GameGrouping;
import basketball.stats.models.LogEntry;
import basketball.stats.models.StatsChunk;
import basketball.stats.models.TimeDivision;
import basketball.stats.processor.utils.ProcessingUtils;
import basketball.stats.util.GameTime;

public class TimeChunkProcessorTest {
    
    @Test
    public void test() {
        int minHours = 15;
        int maxHours = 20;
        int laborHours = 51;
        for (int i = minHours; i <= maxHours; i++) {
            for (int j = minHours; j <= maxHours; j++) {
                for (int k = minHours; k <= maxHours; k++) {
                    if (i + j + k == laborHours) {
                        System.out.println("i=" + i + " j=" + j + " k=" + k);
                        return;
                    }
                }
            }
        }
    }

    @Test
    public void processTimeDivisions_FullGame() {
        // final List<LogEntry> log = ProcessingUtils.buildLogEntries("src/test/resources/test.bbl");
        final List<LogEntry> log = ProcessingUtils.buildLogEntries("C:/Users/Anonymous/Desktop/games/basketballStats-HOME-CHICAGO_BULLS-S1-G1.bbl");
        final TimeChunkProcessor processor = new TimeChunkProcessor();
        final TimeDivision fullGame = new TimeDivision(new GameTime(0, 0, 0), new GameTime(60, 0, 0), "Full Game");

        final Map<TimeDivision, Map<Players, StatsChunk>> obj = processor.processTimeDivisions(new GameTimeChunker(new GameTime(0, 0, 0), new GameTime(100, 0, 0)), log,
                Arrays.asList(fullGame));
        System.out.println(obj);
        for (final Entry<TimeDivision, Map<Players, StatsChunk>> blah : obj.entrySet()) {
            System.out.println("\n" + blah.getKey().getName());
            System.out
                    .println("NAME--------------MIN---PTS---REB---AST---STL---BLK---TO-----FG----------3PT--------FT---------FG%---3P%---FT%-OR----F-----+/----DA----DC----AFG---OFG--------O3PT----");
            for (final Entry<Players, StatsChunk> entry : blah.getValue().entrySet()) {
                System.out.println(entry.getValue().boxScore(1));
            }
        }
    }

    @Test
    public void processTimeDivisions_PerQuarter() {
        final List<LogEntry> log = ProcessingUtils.buildLogEntries("src/test/resources/test.bbl");
        final TimeChunkProcessor processor = new TimeChunkProcessor();
        final TimeDivision firstQuarter = new TimeDivision(new GameTime(0, 0, 0), new GameTime(12, 0, 0), "First Quarter");
        final TimeDivision secondQuarter = new TimeDivision(new GameTime(12, 0, 0), new GameTime(24, 0, 0), "Second Quarter");
        final TimeDivision thirdQuarter = new TimeDivision(new GameTime(24, 0, 0), new GameTime(36, 0, 0), "Third Quarter");
        final TimeDivision fourthQuarter = new TimeDivision(new GameTime(36, 0, 0), new GameTime(48, 0, 0), "Fourth Quarter");
        final TimeDivision overtimeQuarter = new TimeDivision(new GameTime(48, 0, 0), new GameTime(53, 0, 0), "Overtime");

        final Map<TimeDivision, Map<Players, StatsChunk>> obj = processor.processTimeDivisions(new GameTimeChunker(new GameTime(0, 0, 0), new GameTime(100, 0, 0)), log,
                Arrays.asList(firstQuarter, secondQuarter, thirdQuarter, fourthQuarter, overtimeQuarter));
        for (final Entry<TimeDivision, Map<Players, StatsChunk>> blah : obj.entrySet()) {
            System.out.println("\n" + blah.getKey().getName());
            System.out
                    .println("NAME--------------MIN---PTS---REB---AST---STL---BLK---TO-----FG----------3PT--------FT---------FG%---3P%---FT%-OR----F-----+/----DA----DC----AFG---OFG--------O3PT----");
            for (final Entry<Players, StatsChunk> entry : blah.getValue().entrySet()) {
                System.out.println(entry.getValue().boxScore(1));
            }
        }
    }

    @Test
    public void gameGroupings_ByQuarterAcrossGames() {
        final TimeChunkProcessor processor = new TimeChunkProcessor();
        final TimeDivision firstQuarter = new TimeDivision(new GameTime(0, 0, 0), new GameTime(12, 0, 0), "First Quarter");
        final TimeDivision secondQuarter = new TimeDivision(new GameTime(12, 0, 0), new GameTime(24, 0, 0), "Second Quarter");
        final TimeDivision thirdQuarter = new TimeDivision(new GameTime(24, 0, 0), new GameTime(36, 0, 0), "Third Quarter");
        final TimeDivision fourthQuarter = new TimeDivision(new GameTime(36, 0, 0), new GameTime(48, 0, 0), "Fourth Quarter");
        final TimeDivision overtimeQuarter = new TimeDivision(new GameTime(48, 0, 0), new GameTime(53, 0, 0), "Overtime");

        processor
                .processGameGroupings(new GameTimeChunker(new GameTime(0, 0, 0), new GameTime(65, 0, 0)),
                        Collections.singletonList(new GameGrouping(1, 2, "Games 1-82")),
                        Arrays.asList(firstQuarter, secondQuarter, thirdQuarter, fourthQuarter, overtimeQuarter));
    }

    @Test
    public void gameGroupings_ByQuarterPerGame() {
        final TimeChunkProcessor processor = new TimeChunkProcessor();
        final TimeDivision firstQuarter = new TimeDivision(new GameTime(0, 0, 0), new GameTime(12, 0, 0), "First Quarter");
        final TimeDivision secondQuarter = new TimeDivision(new GameTime(12, 0, 0), new GameTime(24, 0, 0), "Second Quarter");
        final TimeDivision thirdQuarter = new TimeDivision(new GameTime(24, 0, 0), new GameTime(36, 0, 0), "Third Quarter");
        final TimeDivision fourthQuarter = new TimeDivision(new GameTime(36, 0, 0), new GameTime(48, 0, 0), "Fourth Quarter");
        final TimeDivision overtimeQuarter = new TimeDivision(new GameTime(48, 0, 0), new GameTime(53, 0, 0), "Overtime");

        final GameGrouping g1 = new GameGrouping(1, 1, "Game 1");
        final GameGrouping g2 = new GameGrouping(2, 2, "Game 2");
        processor.processGameGroupings(new GameTimeChunker(new GameTime(0, 0, 0), new GameTime(65, 0, 0)), Arrays.asList(g1, g2),
                Arrays.asList(firstQuarter, secondQuarter, thirdQuarter, fourthQuarter, overtimeQuarter));
    }

    @Test
    public void gameGroupings_FullGamePerGame() {
        final TimeChunkProcessor processor = new TimeChunkProcessor();
        final TimeDivision fullGame = new TimeDivision(new GameTime(0, 0, 0), new GameTime(53, 0, 0), "Full Game");

        final GameGrouping g1 = new GameGrouping(1, 1, "Game 1");
        final GameGrouping g2 = new GameGrouping(2, 2, "Game 2");
        processor.processGameGroupings(new GameTimeChunker(new GameTime(0, 0, 0), new GameTime(65, 0, 0)), Arrays.asList(g1, g2), Arrays.asList(fullGame));
    }

    @Test
    public void gameGroupings_FullGameAcrossGames() {
        final TimeChunkProcessor processor = new TimeChunkProcessor();
        final TimeDivision fullGame = new TimeDivision(new GameTime(0, 0, 0), new GameTime(53, 0, 0), "Full Game");
        processor.processGameGroupings(new GameTimeChunker(new GameTime(0, 0, 0), new GameTime(65, 0, 0)),
                Collections.singletonList(new GameGrouping(1, 2, "Games 1-82")), Arrays.asList(fullGame));
    }
}
