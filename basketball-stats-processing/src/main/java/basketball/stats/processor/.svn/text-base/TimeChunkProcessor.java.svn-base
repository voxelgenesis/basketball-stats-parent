package basketball.stats.processor;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import basketball.stats.chunker.Chunker;
import basketball.stats.chunker.GameTimeChunker;
import basketball.stats.enums.EventDetail;
import basketball.stats.enums.EventType;
import basketball.stats.enums.Players;
import basketball.stats.enums.Possession;
import basketball.stats.models.GameGrouping;
import basketball.stats.models.LogEntry;
import basketball.stats.models.StatsChunk;
import basketball.stats.models.TimeDivision;
import basketball.stats.processor.utils.ProcessingUtils;
import basketball.stats.util.GameTime;

public class TimeChunkProcessor {

    public void processSeasonGroupings() {

    }

    public void processGameGroupings(final Chunker chunker, final List<GameGrouping> gameGroupings, final List<TimeDivision> timeDivisions) {
        final Map<GameGrouping, Map<TimeDivision, Map<Players, StatsChunk>>> map = new HashMap<>();
        for (final GameGrouping gameGrouping : gameGroupings) {
            final Map<TimeDivision, Map<Players, StatsChunk>> timeDivMap = new HashMap<>();
            map.put(gameGrouping, timeDivMap);

            for (final TimeDivision timeDivision : timeDivisions) {
                timeDivMap.put(timeDivision, new HashMap<Players, StatsChunk>());
            }

            for (final String fileName : retrieveGameFilePaths(gameGrouping.getGames())) {
                final List<LogEntry> log = ProcessingUtils.buildLogEntries(fileName);
                final Map<TimeDivision, Map<Players, StatsChunk>> itemMap = processTimeDivisions(chunker, log, timeDivisions);

                for (final Entry<TimeDivision, Map<Players, StatsChunk>> entry : itemMap.entrySet()) {
                    final Map<Players, StatsChunk> timeDivMap2 = timeDivMap.get(entry.getKey());
                    for (final Entry<Players, StatsChunk> entry2 : entry.getValue().entrySet()) {
                        final Players player = entry2.getKey();
                        final StatsChunk otherChunk = timeDivMap2.get(entry2.getKey());
                        if (otherChunk == null) {
                            timeDivMap2.put(player, entry2.getValue());
                        } else {
                            timeDivMap2.put(player, StatsChunk.combine(entry2.getValue(), otherChunk));
                        }
                    }
                }
            }
        }

        for (final Entry<GameGrouping, Map<TimeDivision, Map<Players, StatsChunk>>> entry : map.entrySet()) {
            System.out.println(entry.getKey().getName());
            for (final Entry<TimeDivision, Map<Players, StatsChunk>> entry2 : entry.getValue().entrySet()) {
                System.out.println(entry2.getKey().getName());
                System.out
                        .println("NAME--------------MIN---PTS---REB---AST---STL---BLK---TO-----FG----------3PT--------FT---------FG%---3P%---FT%-OR----F-----+/----DA----DC----AFG---OFG--------O3PT----");
                for (final Entry<Players, StatsChunk> entry3 : entry2.getValue().entrySet()) {
                    System.out.println(entry3.getValue().boxScore(entry.getKey().getGames().size() * 1.0));
                }
            }
        }
    }

    public Map<TimeDivision, Map<Players, StatsChunk>> processTimeDivisions(final Chunker chunker, final List<LogEntry> log, final List<TimeDivision> timeDivisions) {
        final Map<TimeDivision, Map<Players, StatsChunk>> map = new HashMap<>();
        for (final TimeDivision timeDivision : timeDivisions) {
            // System.out.println(timeDivision.getName());
            // System.out.println("NAME--------------MIN--PTS--REB--AST--STL--BLK--TO---FG----3PT---FT----OR--F---+/---DA---DC---AFG--OFG---O3PT--");
            // TODO not actually doing time divisions. chunker does not split by quarters
            final Map<Players, StatsChunk> timePeriod = processTimePeriod(chunker, timeDivision, log);
            // for (final Entry<Players, StatsChunk> entry : timePeriod.entrySet()) {
            // System.out.println(entry.getValue().boxScore());
            // }
            map.put(timeDivision, timePeriod);
        }

        return map;
    }

    public List<String> retrieveGameFilePaths(final List<Integer> games) {
        final File file = new File("C:/Users/Anonymous/Desktop/games/");
        if (!file.isDirectory()) {
            return Collections.<String> emptyList();
        }

        final List<String> gameFilePaths = new ArrayList<>();
        final File[] files = file.listFiles();
        for (final Integer game : games) {
            for (final File theFile : files) {
                if (theFile.getAbsolutePath().toLowerCase().endsWith(".bbl") && theFile.getAbsolutePath().contains("-G" + game)) {
                    gameFilePaths.add(theFile.getAbsolutePath());
                }
            }
        }
        return gameFilePaths;
    }

    public Map<Players, StatsChunk> processTimePeriod(final Chunker chunker, final TimeDivision timeDivision, final List<LogEntry> log) {

        final MultiValueMap<Players, StatsChunk> fullStatsMap = new LinkedMultiValueMap<>();
        final Map<Players, StatsChunk> returnMap = new HashMap<>();

        final Map<Players, StatsChunk> currentStatsMap = new HashMap<>();

        final StatsChunk noDefender = new StatsChunk();
        noDefender.setStartTime(new GameTime());
        noDefender.setEndTime(new GameTime());
        currentStatsMap.put(Players.NONE, noDefender);

        StatsChunk multiple = new StatsChunk();
        multiple.setStartTime(new GameTime());
        multiple.setEndTime(new GameTime());
        multiple.setPlayer(Players.MULTIPLE);
        currentStatsMap.put(Players.MULTIPLE, multiple);

        final StatsChunk opponent = new StatsChunk();
        opponent.setStartTime(new GameTime());
        opponent.setEndTime(new GameTime());
        opponent.setPlayer(Players.OPPONENT);
        currentStatsMap.put(Players.OPPONENT, opponent);

        final Set<Players> currentPlayers = new HashSet<>();
        int i = 0;
        for (; i < log.size(); i++) {
            final LogEntry entry = log.get(i);
            if (entry.getDetail() == EventDetail.SUBSTITUTION_IN) {
                currentPlayers.add(entry.getPlayers());
            } else if (entry.getDetail() == EventDetail.SUBSTITUTION_OUT || i == log.size()) {
                currentPlayers.remove(entry.getPlayers());

                final StatsChunk chunk = currentStatsMap.get(entry.getPlayers());
                if (chunk == null) {
                    // System.out.println("WEIRD ERROR");
                    continue;
                }

                // ensure end of time division is added to time if this log entry is greater than time division end
                if (entry.getGameTime().compareTo(timeDivision.getEndTime()) > 0) {
                    chunk.setEndTime(timeDivision.getEndTime());
                }
                fullStatsMap.add(chunk.getPlayer(), chunk);
                currentStatsMap.remove(chunk.getPlayer());
            }

            final boolean doTime = (entry.getGameTime().compareTo(timeDivision.getStartTime()) >= 0) && (entry.getGameTime().compareTo(timeDivision.getEndTime()) < 0);
            if (doTime && chunker.doProcess(entry, currentPlayers)) {
                for (final Players player : currentPlayers) {
                    final StatsChunk theChunk = currentStatsMap.get(player);
                    if (theChunk == null) {
                        final StatsChunk chunk = new StatsChunk();
                        chunk.setPlayer(player);
                        chunk.setStartTime(entry.getGameTime());
                        chunk.setEndTime(entry.getGameTime());
                        chunk.setStartDifferential(entry.getTeamScore() - entry.getOpponentScore());
                        currentStatsMap.put(player, chunk);

                        // ensure start of time division is added to time if previous log entry is greater than time division start
                        if (i - 1 >= 0) {
                            if (log.get(i - 1).getGameTime().compareTo(timeDivision.getStartTime()) < 0) {
                                chunk.setStartTime(timeDivision.getStartTime());
                            }
                        }
                    } else {
                        theChunk.setEndTime(entry.getGameTime());
                        theChunk.setEndDifferential(entry.getTeamScore() - entry.getOpponentScore());
                    }
                }
                multiple = new StatsChunk();
                multiple.setStartTime(new GameTime());
                multiple.setEndTime(new GameTime());
                multiple.setPlayer(Players.MULTIPLE);
                currentStatsMap.put(Players.MULTIPLE, multiple);
            } else {
                for (final Entry<Players, StatsChunk> other : currentStatsMap.entrySet()) {
                    final StatsChunk chunk = currentStatsMap.get(other.getKey());
                    if (chunk == null) {
                        continue;
                    }

                    // ensure end of time division is added to time if this log entry is greater than time division end
                    // key point: this is first log that does not get processed
                    if (entry.getGameTime().compareTo(timeDivision.getEndTime()) > 0) {
                        chunk.setEndTime(timeDivision.getEndTime());
                    }
                    fullStatsMap.add(chunk.getPlayer(), chunk);
                }
                currentStatsMap.clear();
                continue;
            }

            // OFFENSIVE POSSESSION
            if (entry.getPossession() == Possession.OFFENSE) {

                // ASSIST
                if (entry.getType() == EventType.ASSIST) {
                    final StatsChunk chunk = currentStatsMap.get(entry.getPlayers());
                    chunk.setAssists(chunk.getAssists() + 1);
                    continue;
                }

                if (entry.getType() == EventType.TURNOVER) {
                    final StatsChunk chunk = currentStatsMap.get(entry.getPlayers());
                    chunk.setTurnovers(chunk.getTurnovers() + 1);
                    if (entry.getDetail() == EventDetail.TURNOVER_STOLEN) {
                        opponent.setSteals(opponent.getSteals() + 1);
                    }
                    continue;
                }

                if (entry.getType() == EventType.REBOUND) {
                    final StatsChunk chunk = currentStatsMap.get(entry.getPlayers());
                    chunk.setOffensiveRebounds(chunk.getOffensiveRebounds() + 1);
                    continue;
                }

                if (entry.getType() == EventType.MADE_SHOT) {
                    final StatsChunk chunk = currentStatsMap.get(entry.getPlayers());
                    chunk.getMadeShots().add(entry.getDetail().toString());
                    continue;
                }

                if (entry.getType() == EventType.MADE_ASSISTED_SHOT) {
                    final StatsChunk chunk = currentStatsMap.get(entry.getPlayers());
                    chunk.getMadeAssistedShots().add(entry.getDetail().toString());
                    continue;
                }

                if (entry.getType() == EventType.MISSED_SHOT) {
                    final StatsChunk chunk = currentStatsMap.get(entry.getPlayers());
                    chunk.getMissedShots().add(entry.getDetail().toString());
                    if (i + 1 < log.size()) {
                        final LogEntry logEntry = log.get(i + 1);
                        if (logEntry.getPossession() == Possession.DEFENSE && logEntry.getType() != EventType.FAST_BREAK) {
                            opponent.setDefensiveRebounds(opponent.getDefensiveRebounds() + 1);
                        }
                    }
                    continue;
                }

                if (entry.getType() == EventType.BLOCK) {
                    final StatsChunk chunk = currentStatsMap.get(entry.getPlayers());
                    chunk.getMissedShots().add(entry.getDetail().toString());
                    opponent.setBlocks(opponent.getBlocks() + 1);
                    continue;
                }

                if (entry.getType() == EventType.FOUL) {
                    final StatsChunk chunk = currentStatsMap.get(entry.getPlayers());
                    if (chunk == null) {
                        continue;
                    }
                    chunk.setOffensiveFouls(chunk.getOffensiveFouls() + 1);
                    continue;
                }

                if (entry.getType() == EventType.DEFLECTION) {
                    final StatsChunk chunk = currentStatsMap.get(entry.getPlayers());
                    chunk.setDeflectionsAccident(chunk.getDeflectionsAccident() + 1);
                    opponent.setDeflectionsCaused(opponent.getDeflectionsCaused() + 1);
                    continue;
                }

                if (entry.getType() == EventType.TEAM_REBOUND) {
                    opponent.setDefensiveRebounds(opponent.getDefensiveRebounds() + 1);
                    continue;
                }
            } else if (entry.getPossession() == Possession.DEFENSE) {

                if (entry.getType() == EventType.MADE_SHOT) {
                    if (entry.getPlayers() == null) {
                        noDefender.getMadeShots().add(entry.getDetail().toString());
                        opponent.getMadeShots().add(entry.getDetail().toString());
                        continue;
                    }

                    final StatsChunk chunk = currentStatsMap.get(entry.getPlayers());
                    chunk.getOpponentMadeShots().add(entry.getDetail().toString());
                    opponent.getMadeShots().add(entry.getDetail().toString());
                    continue;
                }

                if (entry.getType() == EventType.MISSED_SHOT) {
                    if (entry.getPlayers() == null) {
                        noDefender.getMissedShots().add(entry.getDetail().toString());
                        opponent.getMissedShots().add(entry.getDetail().toString());
                        continue;
                    }

                    final StatsChunk chunk = currentStatsMap.get(entry.getPlayers());
                    chunk.getOpponentMissedShots().add(entry.getDetail().toString());
                    opponent.getMissedShots().add(entry.getDetail().toString());

                    if (i + 1 < log.size()) {
                        final LogEntry logEntry = log.get(i + 1);
                        if (logEntry.getPossession() == Possession.DEFENSE
                                && Arrays.asList(EventType.MADE_SHOT, EventType.MISSED_SHOT, EventType.BLOCK, EventType.TURNOVER, EventType.TEAM_REBOUND, EventType.FOUL)
                                        .contains(logEntry.getType())) {
                            opponent.setOffensiveRebounds(opponent.getOffensiveRebounds() + 1);
                        }
                    }
                    continue;
                }

                if (entry.getType() == EventType.REBOUND) {
                    final StatsChunk chunk = currentStatsMap.get(entry.getPlayers());
                    chunk.setDefensiveRebounds(chunk.getDefensiveRebounds() + 1);
                    continue;
                }

                if (entry.getType() == EventType.BLOCK) {
                    final StatsChunk chunk = currentStatsMap.get(entry.getPlayers());
                    chunk.setBlocks(chunk.getBlocks() + 1);
                    chunk.getOpponentMissedShots().add(entry.getDetail().toString());
                    opponent.getMissedShots().add(entry.getDetail().toString());

                    continue;
                }

                if (entry.getType() == EventType.STEAL) {
                    final StatsChunk chunk = currentStatsMap.get(entry.getPlayers());
                    chunk.setSteals(chunk.getSteals() + 1);
                    opponent.setTurnovers(opponent.getTurnovers() + 1);
                    continue;
                }

                if (entry.getType() == EventType.SHOOTING_FOUL) {
                    final StatsChunk chunk = currentStatsMap.get(entry.getPlayers());
                    chunk.setShootingFouls(chunk.getShootingFouls() + 1);
                    continue;
                }

                if (entry.getType() == EventType.DEFLECTION) {
                    final StatsChunk chunk = currentStatsMap.get(entry.getPlayers());
                    chunk.setDeflectionsCaused(chunk.getDeflectionsCaused() + 1);
                    opponent.setDeflectionsAccident(opponent.getDeflectionsAccident() + 1);
                    continue;
                }

                if (entry.getType() == EventType.TURNOVER) {
                    opponent.setTurnovers(opponent.getTurnovers() + 1);
                    continue;
                }

                if (entry.getType() == EventType.FOUL) {
                    if (entry.getPlayers() == null || entry.getPlayers() == Players.OPPONENT) {
                        opponent.setTurnovers(opponent.getTurnovers() + 1);
                        continue;
                    }
                    final StatsChunk chunk = currentStatsMap.get(entry.getPlayers());
                    chunk.setNonShootingFouls(chunk.getNonShootingFouls() + 1);
                    continue;
                }
            }
        }

        if (!currentStatsMap.isEmpty()) {
            for (final Entry<Players, StatsChunk> other : currentStatsMap.entrySet()) {
                final StatsChunk chunk = currentStatsMap.get(other.getKey());
                if (chunk == null) {
                    continue;
                }

                // some activity must have occurred in the last minute for this to be accurate -- this will always be true
                chunk.setEndTime(new GameTime(chunk.getEndTime().getMinutes() + 1, 0, 0));
                fullStatsMap.add(chunk.getPlayer(), chunk);
            }
        }

        for (final Entry<Players, List<StatsChunk>> xxx : fullStatsMap.entrySet()) {
            returnMap.put(xxx.getKey() == null ? Players.NONE : xxx.getKey(), ProcessingUtils.calculatePlayerStats(xxx.getValue()));
            // if (xxx.getKey() != null && xxx.getKey() != Players.MULTIPLE && xxx.getKey() != Players.OPPONENT) {
            // teamStats.addAll(xxx.getValue());
            // }
        }
        // returnMap.put(Players.TEAM, ProcessingUtils.calculatePlayerStats(teamStats));

        return returnMap;
    }

    public static void main(final String[] args) {
        final List<LogEntry> log = ProcessingUtils.buildLogEntries("src/test/resources/test.bbl");
        final TimeChunkProcessor processor = new TimeChunkProcessor();
        // processor.processTimePeriod(log, new GameTime(0, 0, 0), new GameTime(3, 0, 0));
        // processor.retrieveGameFilePaths(Arrays.asList(1));
        final TimeDivision firstQuarter = new TimeDivision(new GameTime(0, 0, 0), new GameTime(12, 0, 0), "First Quarter");
        final TimeDivision secondQuarter = new TimeDivision(new GameTime(12, 0, 0), new GameTime(24, 0, 0), "Second Quarter");
        final TimeDivision thirdQuarter = new TimeDivision(new GameTime(24, 0, 0), new GameTime(36, 0, 0), "Third Quarter");
        final TimeDivision fourthQuarter = new TimeDivision(new GameTime(36, 0, 0), new GameTime(48, 0, 0), "Fourth Quarter");
        final TimeDivision overtimeQuarter = new TimeDivision(new GameTime(48, 0, 0), new GameTime(53, 0, 0), "Overtime");
        final TimeDivision fullGame = new TimeDivision(new GameTime(0, 0, 0), new GameTime(53, 0, 0), "Full Game");
        final TimeDivision lastFive = new TimeDivision(new GameTime(43, 0, 0), new GameTime(48, 0, 0), "Last Five Minutes");

        final TimeDivision lastFirstQuarter = new TimeDivision(new GameTime(11, 30, 0), new GameTime(12, 0, 0), "First Quarter");
        final TimeDivision lastSecondQuarter = new TimeDivision(new GameTime(23, 30, 0), new GameTime(24, 0, 0), "Second Quarter");
        final TimeDivision lastThirdQuarter = new TimeDivision(new GameTime(35, 30, 0), new GameTime(36, 0, 0), "Third Quarter");
        final TimeDivision lastFourthQuarter = new TimeDivision(new GameTime(47, 30, 0), new GameTime(48, 0, 0), "Fourth Quarter");
        // processor.processTimeDivisions(log, Arrays.asList(firstQuarter, secondQuarter, thirdQuarter, fourthQuarter));
        // processor.processTimeDivisions(log, Arrays.asList(fullGame));
        // processor.processTimeDivisions(log, Arrays.asList(lastFive));
        // processor.processTimeDivisions(log, Arrays.asList(lastFirstQuarter, lastSecondQuarter, lastThirdQuarter, lastFourthQuarter));

        final Map<TimeDivision, Map<Players, StatsChunk>> obj = processor.processTimeDivisions(new GameTimeChunker(new GameTime(0, 0, 0), new GameTime(100, 0, 0)), log,
        // Arrays.asList(firstQuarter, secondQuarter, thirdQuarter, fourthQuarter, overtimeQuarter));
                Arrays.asList(fullGame));
        for (final Entry<TimeDivision, Map<Players, StatsChunk>> blah : obj.entrySet()) {
            System.out.println("\n" + blah.getKey().getName());
            System.out
                    .println("NAME--------------MIN---PTS---REB---AST---STL---BLK---TO-----FG----------3PT--------FT---------FG%---3P%---FT%-OR----F-----+/----DA----DC----AFG---OFG--------O3PT----");
            for (final Entry<Players, StatsChunk> entry : blah.getValue().entrySet()) {
                System.out.println(entry.getValue().boxScore(1));
            }
        }

        // final GameGrouping g1 = new GameGrouping(1, 1, "Game 1");
        // final GameGrouping g2 = new GameGrouping(2, 2, "Game 2");
        // processor.processGameGroupings(new GameTimeChunker(new GameTime(0, 0, 0), new GameTime(65, 0, 0)),
        // // Collections.singletonList(new GameGrouping(1, 2, "Games 1-82")), Arrays.asList(firstQuarter, secondQuarter, thirdQuarter, fourthQuarter));
        // Arrays.asList(g1, g2), Arrays.asList(fullGame));
        // Collections.singletonList(new GameGrouping(1, 2, "Games 1-82")), Arrays.asList(fullGame));
        // Arrays.asList(g1, g2), Arrays.asList(firstQuarter, secondQuarter, thirdQuarter, fourthQuarter, overtimeQuarter));
        // processor.processGameGroupings(new GameTimeChunker(new GameTime(0, 0, 0), new GameTime(48, 0, 0)),
        // Collections.singletonList(new GameGrouping(1, 2, "Games 1-82")), Arrays.asList(fullGame));

        // processor.processGameGroupings(Collections.singletonList(new GameGrouping(1, 82, "Games 1-82")), Arrays.asList(fullGame));
        // final GameGrouping g1 = new GameGrouping(1, 1, "Game 1");
        // final GameGrouping g2 = new GameGrouping(2, 2, "Game 2");
        // processor.processGameGroupings(new GameTimeChunker(new GameTime(0, 0, 0), new GameTime(48, 0, 0)), Arrays.asList(g1, g2), Arrays.asList(fullGame));

        // Map<Players, StatsChunk> timePeriod = processor.processTimePeriod(new MultiManChunker(new HashSet<>(Arrays.asList(Players.DURANT_KEVIN,
        // Players.BLEDSOE_ERIC)),
        // Map<Players, StatsChunk> timePeriod = processor.processTimePeriod(new GameTimeChunker(new GameTime(0, 0, 0), new GameTime(48, 0, 0)), log);
        // for (final Entry<Players, StatsChunk> entry : timePeriod.entrySet()) {
        // System.out.println(entry.getValue().boxScore());
        // }
    }
}
