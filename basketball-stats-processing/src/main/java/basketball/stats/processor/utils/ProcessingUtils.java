package basketball.stats.processor.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import basketball.stats.enums.EventDetail;
import basketball.stats.enums.EventType;
import basketball.stats.enums.Players;
import basketball.stats.enums.Possession;
import basketball.stats.models.LogEntry;
import basketball.stats.models.StatsChunk;
import basketball.stats.util.GameTime;

public class ProcessingUtils {

    public static StatsChunk calculatePlayerStats(final Collection<StatsChunk> chunks) {
        final StatsChunk finalChunk = new StatsChunk();
        finalChunk.setStartTime(new GameTime());
        finalChunk.setEndTime(new GameTime());
        for (final StatsChunk chunk : chunks) {
            if (chunk.getPlayer() != null) {
                finalChunk.setPlayer(chunk.getPlayer());
            }
            finalChunk.getEndTime().addMinutes(chunk.getTimeDifferential().getMinutes());
            finalChunk.getEndTime().addSeconds(chunk.getTimeDifferential().getSeconds());

            finalChunk.setAssists(finalChunk.getAssists() + chunk.getAssists());
            finalChunk.setBlocks(finalChunk.getBlocks() + chunk.getBlocks());
            finalChunk.setDefensiveRebounds(finalChunk.getDefensiveRebounds() + chunk.getDefensiveRebounds());
            finalChunk.setOffensiveRebounds(finalChunk.getOffensiveRebounds() + chunk.getOffensiveRebounds());
            finalChunk.setDeflectionsAccident(finalChunk.getDeflectionsAccident() + chunk.getDeflectionsAccident());
            finalChunk.setDeflectionsCaused(finalChunk.getDeflectionsCaused() + chunk.getDeflectionsCaused());
            // finalChunk.setFastBreakPoints(fastBreakPoints)
            finalChunk.getOpponentMadeShots().addAll(chunk.getOpponentMadeShots());
            finalChunk.getOpponentMissedShots().addAll(chunk.getOpponentMissedShots());
            finalChunk.getMadeShots().addAll(chunk.getMadeShots());
            finalChunk.getMadeAssistedShots().addAll(chunk.getMadeAssistedShots());
            finalChunk.getMissedShots().addAll(chunk.getMissedShots());
            finalChunk.setNonShootingFouls(finalChunk.getNonShootingFouls() + chunk.getNonShootingFouls());
            finalChunk.setOffensiveFouls(finalChunk.getOffensiveFouls() + chunk.getOffensiveFouls());
            finalChunk.setShootingFouls(finalChunk.getShootingFouls() + chunk.getShootingFouls());
            finalChunk.setSteals(finalChunk.getSteals() + chunk.getSteals());
            finalChunk.setTurnovers(finalChunk.getTurnovers() + chunk.getTurnovers());
            finalChunk.setEndDifferential(finalChunk.getEndDifferential() + chunk.getDifferential());
        }

        // get final time
        finalChunk.getEndTime().addMinutes(finalChunk.getTimeDifferential().getSeconds() / 60);
        finalChunk.getEndTime().setSeconds(finalChunk.getTimeDifferential().getSeconds() % 60);
        return finalChunk;
    }

    public static List<LogEntry> buildLogEntries(final String fileName) {
        final File file = new File(fileName);
        final List<LogEntry> log = new ArrayList<>();
        try (final Scanner reader = new Scanner(file)) {

            while (reader.hasNext()) {
                final String line = reader.nextLine();
                final String[] splitLines = line.split("\\|");

                final LogEntry logEntry = new LogEntry();
                logEntry.setShotClock(Integer.parseInt(splitLines[0]));

                final String[] splitTimes = splitLines[1].split(":");
                final GameTime gameTime = new GameTime();
                gameTime.setMinutes(Integer.parseInt(splitTimes[0]));
                gameTime.setSeconds(Integer.parseInt(splitTimes[1].split("\\.")[0]));
                gameTime.setSplitSeconds(Integer.parseInt(splitTimes[1].split("\\.")[1]));
                logEntry.setGameTime(gameTime);

                logEntry.setPossession(Possession.valueOf(splitLines[2]));
                logEntry.setPlayers(splitLines[3].isEmpty() ? null : Players.valueOf(splitLines[3]));
                logEntry.setType(EventType.valueOf(splitLines[4]));
                logEntry.setDetail(splitLines[5].isEmpty() ? null : EventDetail.valueOf(splitLines[5]));
                logEntry.setTeamScore(Integer.parseInt(splitLines[6]));
                logEntry.setOpponentScore(Integer.parseInt(splitLines[7]));
                log.add(logEntry);
            }
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        }

        return log;
    }

    public static int getPoints(final List<String> madeShots, final List<String> assistedShots) {
        final List<String> allMadeShots = new ArrayList<>(madeShots);
        allMadeShots.addAll(assistedShots);

        return getTwos(allMadeShots) * 2 + getThrees(allMadeShots) * 3 + getFreeThrows(allMadeShots);
    }

    public static int getMadeShots(final List<String> madeShots) {
        int count = 0;
        for (final String madeShot : madeShots) {
            if (madeShot.contains("FREE_THROW")) {
                continue;
            }
            count++;
        }
        return count;
    }

    public static int getMadeShots(final List<String> madeShots, final List<String> assistedShots) {
        final List<String> allMadeShots = new ArrayList<>(madeShots);
        allMadeShots.addAll(assistedShots);
        int count = 0;
        for (final String madeShot : allMadeShots) {
            if (madeShot.contains("FREE_THROW")) {
                continue;
            }
            count++;
        }
        return count;
    }

    public static int getTwos(final List<String> madeShots) {
        int count = 0;
        for (final String madeShot : madeShots) {
            if (madeShot.contains("FREE_THROW") || madeShot.contains("THREE")) {
                continue;
            }
            count++;
        }
        return count;
    }

    public static int getThrees(final List<String> madeShots) {
        int count = 0;
        for (final String madeShot : madeShots) {
            if (madeShot.contains("THREE")) {
                count++;
            }
        }
        return count;
    }

    public static int getThrees(final List<String> madeShots, final List<String> assistedShots) {
        final List<String> allMadeShots = new ArrayList<>(madeShots);
        allMadeShots.addAll(assistedShots);
        int count = 0;
        for (final String madeShot : allMadeShots) {
            if (madeShot.contains("THREE")) {
                count++;
            }
        }
        return count;
    }

    public static int getFreeThrows(final List<String> madeShots) {
        int count = 0;
        for (final String madeShot : madeShots) {
            if (madeShot.contains("FREE_THROW")) {
                count++;
            }
        }
        return count;
    }

    public static int getAttemptedShots(final List<String> attemptedShots) {
        int count = 0;
        for (final String attemptedShot : attemptedShots) {
            if (attemptedShot.contains("FREE_THROW")) {
                continue;
            }

            count++;
        }
        return count;
    }

    public static int getAttemptedFreeThrows(final List<String> attemptedShots) {
        int count = 0;
        for (final String attemptedShot : attemptedShots) {
            if (attemptedShot.contains("FREE_THROW")) {
                count++;
            }
        }
        return count;
    }

    public static int getAttemptedFreeThrows(final List<String> madeShots, final List<String> missedShots) {
        final List<String> attemptedShots = new ArrayList<>(madeShots);
        attemptedShots.addAll(missedShots);
        int count = 0;
        for (final String attemptShot : attemptedShots) {
            if (attemptShot.contains("FREE_THROW")) {
                count++;
            }
        }
        return count;
    }

    public static int getAttemptedThrees(final List<String> attemptedShots) {
        int count = 0;
        for (final String attemptedShot : attemptedShots) {
            if (attemptedShot.contains("THREE")) {
                count++;
            }
        }
        return count;
    }

    public static int getAttemptedThrees(final List<String> madeShots, final List<String> madeAssistedShots, final List<String> missedShots) {
        final List<String> attemptedShots = new ArrayList<>(madeShots);
        attemptedShots.addAll(missedShots);
        attemptedShots.addAll(madeAssistedShots);
        int count = 0;
        for (final String attemptShot : attemptedShots) {
            if (attemptShot.contains("THREE")) {
                count++;
            }
        }
        return count;
    }

    public static int getAttemptedShots(final List<String> madeShots, final List<String> madeAssistedShots, final List<String> missedShots) {
        final List<String> attemptedShots = new ArrayList<>(madeShots);
        attemptedShots.addAll(missedShots);
        attemptedShots.addAll(madeAssistedShots);
        int count = 0;
        for (final String attemptShot : attemptedShots) {
            if (attemptShot.contains("FREE_THROW")) {
                continue;
            }

            count++;
        }
        return count;
    }
}
