package basketball.stats.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import basketball.stats.enums.Players;
import basketball.stats.processor.utils.ProcessingUtils;
import basketball.stats.util.GameTime;

public class StatsChunk {

    private GameTime startTime;
    private GameTime endTime;
    private int startDifferential;
    private int endDifferential;
    private Players player;
    private int assists;
    private int defensiveRebounds;
    private int offensiveRebounds;
    private int steals;
    private int blocks;
    private List<String> madeShots = new ArrayList<>();
    private List<String> madeAssistedShots = new ArrayList<>();
    private List<String> missedShots = new ArrayList<>();
    private List<String> opponentMadeShots = new ArrayList<>();
    private List<String> opponentMissedShots = new ArrayList<>();
    private int turnovers;
    private int deflectionsCaused;
    private int deflectionsAccident;
    private int offensiveFouls;
    private int nonShootingFouls;
    private int shootingFouls;
    private int fastBreakPoints;

    public int getDifferential() {
        return startDifferential < endDifferential ? Math.abs(startDifferential - endDifferential) : endDifferential - startDifferential;
    }

    public static StatsChunk combine(final StatsChunk chunk1, final StatsChunk chunk2) {
        if (chunk1.getPlayer() != chunk2.getPlayer()) {
            throw new IllegalArgumentException("Players not the same.");
        }
        final StatsChunk chunk = new StatsChunk();
        final int minutes = chunk1.getTimeDifferential().getMinutes() + chunk2.getTimeDifferential().getMinutes();
        final int seconds = chunk1.getTimeDifferential().getSeconds() + chunk2.getTimeDifferential().getSeconds();

        // TODO time and differential
        chunk.setStartTime(new GameTime(0, 0, 0));
        chunk.setEndTime(new GameTime(minutes + (seconds / 60), seconds % 60, 0));
        chunk.setStartDifferential(chunk1.getStartDifferential() + chunk2.getStartDifferential());
        chunk.setEndDifferential(chunk1.getEndDifferential() + chunk2.getEndDifferential());
        chunk.setPlayer(chunk1.getPlayer());
        chunk.setAssists(chunk1.getAssists() + chunk2.getAssists());
        chunk.setDefensiveRebounds(chunk1.getDefensiveRebounds() + chunk2.getDefensiveRebounds());
        chunk.setOffensiveRebounds(chunk1.getOffensiveRebounds() + chunk2.getOffensiveRebounds());
        chunk.setSteals(chunk1.getSteals() + chunk2.getSteals());
        chunk.setBlocks(chunk1.getBlocks() + chunk2.getBlocks());
        chunk.getMadeShots().addAll(chunk1.getMadeShots());
        chunk.getMadeShots().addAll(chunk2.getMadeShots());
        chunk.getMadeAssistedShots().addAll(chunk1.getMadeAssistedShots());
        chunk.getMadeAssistedShots().addAll(chunk2.getMadeAssistedShots());
        chunk.getMissedShots().addAll(chunk1.getMissedShots());
        chunk.getMissedShots().addAll(chunk2.getMissedShots());
        chunk.getOpponentMadeShots().addAll(chunk1.getOpponentMadeShots());
        chunk.getOpponentMadeShots().addAll(chunk2.getOpponentMadeShots());
        chunk.getOpponentMissedShots().addAll(chunk1.getOpponentMissedShots());
        chunk.getOpponentMissedShots().addAll(chunk2.getOpponentMissedShots());
        chunk.setTurnovers(chunk1.getTurnovers() + chunk2.getTurnovers());
        chunk.setDeflectionsCaused(chunk1.getDeflectionsCaused() + chunk2.getDeflectionsCaused());
        chunk.setDeflectionsAccident(chunk1.getDeflectionsAccident() + chunk2.getDeflectionsAccident());
        chunk.setOffensiveFouls(chunk1.getOffensiveFouls() + chunk2.getOffensiveFouls());
        chunk.setNonShootingFouls(chunk1.getNonShootingFouls() + chunk2.getNonShootingFouls());
        chunk.setShootingFouls(chunk1.getShootingFouls() + chunk2.getShootingFouls());
        chunk.setFastBreakPoints(chunk1.getFastBreakPoints() + chunk2.getFastBreakPoints());
        return chunk;
    }

    public GameTime getTimeDifferential() {
        final GameTime diff = new GameTime();
        if (startTime.getMinutes() == endTime.getMinutes()) {
            diff.setSeconds(endTime.getSeconds() - startTime.getSeconds());
        } else {
            diff.setSeconds(endTime.getSeconds() + (startTime.getSeconds() == 0 ? 0 : 60 - startTime.getSeconds()));
            diff.setMinutes(endTime.getMinutes() - (startTime.getSeconds() == 0 ? startTime.getMinutes() : startTime.getMinutes() + 1));
        }
        return diff;
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

    public Players getPlayer() {
        return player;
    }

    public void setPlayer(final Players player) {
        this.player = player;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(final int assists) {
        this.assists = assists;
    }

    public int getDefensiveRebounds() {
        return defensiveRebounds;
    }

    public void setDefensiveRebounds(final int defensiveRebounds) {
        this.defensiveRebounds = defensiveRebounds;
    }

    public int getOffensiveRebounds() {
        return offensiveRebounds;
    }

    public void setOffensiveRebounds(final int offensiveRebounds) {
        this.offensiveRebounds = offensiveRebounds;
    }

    public int getSteals() {
        return steals;
    }

    public void setSteals(final int steals) {
        this.steals = steals;
    }

    public int getBlocks() {
        return blocks;
    }

    public void setBlocks(final int blocks) {
        this.blocks = blocks;
    }

    public List<String> getMadeShots() {
        return madeShots;
    }

    public void setMadeShots(final List<String> madeShots) {
        this.madeShots = madeShots;
    }

    public List<String> getMadeAssistedShots() {
        return madeAssistedShots;
    }

    public void setMadeAssistedShots(final List<String> madeAssistedShots) {
        this.madeAssistedShots = madeAssistedShots;
    }

    public List<String> getMissedShots() {
        return missedShots;
    }

    public void setMissedShots(final List<String> missedShots) {
        this.missedShots = missedShots;
    }

    public int getTurnovers() {
        return turnovers;
    }

    public void setTurnovers(final int turnovers) {
        this.turnovers = turnovers;
    }

    public int getDeflectionsCaused() {
        return deflectionsCaused;
    }

    public void setDeflectionsCaused(final int deflectionsCaused) {
        this.deflectionsCaused = deflectionsCaused;
    }

    public int getDeflectionsAccident() {
        return deflectionsAccident;
    }

    public void setDeflectionsAccident(final int deflectionsAccident) {
        this.deflectionsAccident = deflectionsAccident;
    }

    public int getOffensiveFouls() {
        return offensiveFouls;
    }

    public void setOffensiveFouls(final int offensiveFouls) {
        this.offensiveFouls = offensiveFouls;
    }

    public int getNonShootingFouls() {
        return nonShootingFouls;
    }

    public void setNonShootingFouls(final int nonShootingFouls) {
        this.nonShootingFouls = nonShootingFouls;
    }

    public int getShootingFouls() {
        return shootingFouls;
    }

    public void setShootingFouls(final int shootingFouls) {
        this.shootingFouls = shootingFouls;
    }

    public int getFastBreakPoints() {
        return fastBreakPoints;
    }

    public void setFastBreakPoints(final int fastBreakPoints) {
        this.fastBreakPoints = fastBreakPoints;
    }

    public int getStartDifferential() {
        return startDifferential;
    }

    public void setStartDifferential(final int startDifferential) {
        this.startDifferential = startDifferential;
    }

    public int getEndDifferential() {
        return endDifferential;
    }

    public void setEndDifferential(final int endDifferential) {
        this.endDifferential = endDifferential;
    }

    public List<String> getOpponentMadeShots() {
        return opponentMadeShots;
    }

    public void setOpponentMadeShots(final List<String> opponentMadeShots) {
        this.opponentMadeShots = opponentMadeShots;
    }

    public List<String> getOpponentMissedShots() {
        return opponentMissedShots;
    }

    public void setOpponentMissedShots(final List<String> opponentMissedShots) {
        this.opponentMissedShots = opponentMissedShots;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + assists;
        result = prime * result + blocks;
        result = prime * result + defensiveRebounds;
        result = prime * result + deflectionsAccident;
        result = prime * result + deflectionsCaused;
        result = prime * result + endDifferential;
        result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
        result = prime * result + fastBreakPoints;
        result = prime * result + ((madeAssistedShots == null) ? 0 : madeAssistedShots.hashCode());
        result = prime * result + ((madeShots == null) ? 0 : madeShots.hashCode());
        result = prime * result + ((missedShots == null) ? 0 : missedShots.hashCode());
        result = prime * result + nonShootingFouls;
        result = prime * result + offensiveFouls;
        result = prime * result + offensiveRebounds;
        result = prime * result + ((opponentMadeShots == null) ? 0 : opponentMadeShots.hashCode());
        result = prime * result + ((opponentMissedShots == null) ? 0 : opponentMissedShots.hashCode());
        result = prime * result + ((player == null) ? 0 : player.hashCode());
        result = prime * result + shootingFouls;
        result = prime * result + startDifferential;
        result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
        result = prime * result + steals;
        result = prime * result + turnovers;
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
        final StatsChunk other = (StatsChunk) obj;
        if (assists != other.assists) {
            return false;
        }
        if (blocks != other.blocks) {
            return false;
        }
        if (defensiveRebounds != other.defensiveRebounds) {
            return false;
        }
        if (deflectionsAccident != other.deflectionsAccident) {
            return false;
        }
        if (deflectionsCaused != other.deflectionsCaused) {
            return false;
        }
        if (endDifferential != other.endDifferential) {
            return false;
        }
        if (endTime == null) {
            if (other.endTime != null) {
                return false;
            }
        } else if (!endTime.equals(other.endTime)) {
            return false;
        }
        if (fastBreakPoints != other.fastBreakPoints) {
            return false;
        }
        if (madeAssistedShots == null) {
            if (other.madeAssistedShots != null) {
                return false;
            }
        } else if (!madeAssistedShots.equals(other.madeAssistedShots)) {
            return false;
        }
        if (madeShots == null) {
            if (other.madeShots != null) {
                return false;
            }
        } else if (!madeShots.equals(other.madeShots)) {
            return false;
        }
        if (missedShots == null) {
            if (other.missedShots != null) {
                return false;
            }
        } else if (!missedShots.equals(other.missedShots)) {
            return false;
        }
        if (nonShootingFouls != other.nonShootingFouls) {
            return false;
        }
        if (offensiveFouls != other.offensiveFouls) {
            return false;
        }
        if (offensiveRebounds != other.offensiveRebounds) {
            return false;
        }
        if (opponentMadeShots == null) {
            if (other.opponentMadeShots != null) {
                return false;
            }
        } else if (!opponentMadeShots.equals(other.opponentMadeShots)) {
            return false;
        }
        if (opponentMissedShots == null) {
            if (other.opponentMissedShots != null) {
                return false;
            }
        } else if (!opponentMissedShots.equals(other.opponentMissedShots)) {
            return false;
        }
        if (player != other.player) {
            return false;
        }
        if (shootingFouls != other.shootingFouls) {
            return false;
        }
        if (startDifferential != other.startDifferential) {
            return false;
        }
        if (startTime == null) {
            if (other.startTime != null) {
                return false;
            }
        } else if (!startTime.equals(other.startTime)) {
            return false;
        }
        if (steals != other.steals) {
            return false;
        }
        if (turnovers != other.turnovers) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return new String("StatsChunk [" + (startTime != null ? "startTime=" + startTime + ", " : "") + (endTime != null ? "endTime=" + endTime + ", " : "")
                + (player != null ? "player=" + player + ", " : "") + (startDifferential == 0 ? " " : "startDifferential=" + startDifferential)
                + (endDifferential == 0 ? " " : "endDifferential=" + endDifferential) + (assists == 0 ? " " : "assists=" + assists)
                + (defensiveRebounds == 0 ? " " : ", defensiveRebounds=" + defensiveRebounds)
                + (offensiveRebounds == 0 ? " " : ", offensiveRebounds=" + offensiveRebounds) + (steals == 0 ? " " : ", steals=" + steals)
                + (blocks == 0 ? " " : ", blocks=" + blocks) + ", " + (turnovers == 0 ? " " : "turnovers=" + turnovers)
                + (deflectionsCaused == 0 ? " " : ", deflectionsCaused=" + deflectionsCaused)
                + (deflectionsAccident == 0 ? " " : ", deflectionsAccident=" + deflectionsAccident) + (offensiveFouls == 0 ? " " : ", offensiveFouls=" + offensiveFouls)
                + (nonShootingFouls == 0 ? " " : ", nonShootingFouls=" + nonShootingFouls) + (shootingFouls == 0 ? " " : ", shootingFouls=" + shootingFouls)
                + (fastBreakPoints == 0 ? " " : ", fastBreakPoints=" + fastBreakPoints) + (madeShots != null ? "madeShots=" + madeShots + ", " : "")
                + (madeAssistedShots != null ? "madeAssistedShots=" + madeAssistedShots + ", " : "") + (missedShots != null ? "missedShots=" + missedShots + ", " : "")
                + "]").replaceAll("\\s+", " ");
    }

    public String boxScore(final double divisor) {
        final int points = ProcessingUtils.getPoints(madeShots, madeAssistedShots);
        final int rebounds = offensiveRebounds + defensiveRebounds;
        final int differential = startDifferential < endDifferential ? Math.abs(startDifferential - endDifferential) : endDifferential - startDifferential;
        final String fieldGoals = String.format("%5.1f", ProcessingUtils.getMadeShots(madeShots, madeAssistedShots) / divisor) + "/"
                + String.format("%-5.1f", ProcessingUtils.getAttemptedShots(madeShots, madeAssistedShots, missedShots) / divisor);
        final String threePointers = String.format("%5.1f", ProcessingUtils.getThrees(madeShots, madeAssistedShots) / divisor) + "/"
                + String.format("%-5.1f", ProcessingUtils.getAttemptedThrees(madeShots, madeAssistedShots, missedShots) / divisor);
        final String freeThrows = String.format("%5.1f", ProcessingUtils.getFreeThrows(madeShots) / divisor) + "/"
                + String.format("%-5.1f", ProcessingUtils.getAttemptedFreeThrows(madeShots, missedShots) / divisor);

        final double fieldGoalPercentage = (ProcessingUtils.getMadeShots(madeShots, madeAssistedShots) * 1.0)
                / ProcessingUtils.getAttemptedShots(madeShots, madeAssistedShots, missedShots) * 100;
        final double threePointPercentage = (ProcessingUtils.getThrees(madeShots, madeAssistedShots) * 1.0)
                / ProcessingUtils.getAttemptedThrees(madeShots, madeAssistedShots, missedShots) * 100;
        final double freeThrowPercentage = (ProcessingUtils.getFreeThrows(madeShots) * 1.0) / ProcessingUtils.getAttemptedFreeThrows(madeShots, missedShots) * 100;

        final String opponentFieldGoals = ProcessingUtils.getMadeShots(opponentMadeShots) / divisor + "/"
                + ProcessingUtils.getAttemptedShots(opponentMadeShots, Collections.<String> emptyList(), opponentMissedShots) / divisor;
        final String opponentThreePointers = ProcessingUtils.getThrees(opponentMadeShots) / divisor + "/"
                + ProcessingUtils.getAttemptedThrees(opponentMadeShots, Collections.<String> emptyList(), opponentMissedShots) / divisor;
        final int fouls = nonShootingFouls + offensiveFouls + shootingFouls;
        final double minutes = this.endTime.getMinutes() + (this.endTime.getSeconds() / 60.0);
        final String result = String.format("%-18s%-6.1f%-6.1f%-6.1f%-6.1f%-6.1f%-6.1f%-6.1f%-11s%-11s%-11s%6.1f%6.1f%6.1f%-6.1f%-6.1f%-6.1f%-6.1f%-6.1f%-6.1f%-11s%-11s",
                new Object[] { player == null ? "" : player.toString(), minutes / divisor, points / divisor, rebounds / divisor, assists / divisor, steals / divisor,
                        blocks / divisor, turnovers / divisor, fieldGoals, threePointers, freeThrows, fieldGoalPercentage, threePointPercentage, freeThrowPercentage,
                        offensiveRebounds / divisor, fouls / divisor, differential / divisor, deflectionsAccident / divisor, deflectionsCaused / divisor,
                        madeAssistedShots.size() / divisor, opponentFieldGoals, opponentThreePointers });
        return result;
    }
}
