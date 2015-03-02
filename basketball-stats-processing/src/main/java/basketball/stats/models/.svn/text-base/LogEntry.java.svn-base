package basketball.stats.models;

import basketball.stats.enums.EventDetail;
import basketball.stats.enums.EventType;
import basketball.stats.enums.Players;
import basketball.stats.enums.Possession;
import basketball.stats.util.GameTime;

public class LogEntry {

    private int shotClock;
    private GameTime gameTime;
    private Possession possession;
    private Players player;
    private EventType type;
    private EventDetail detail;
    private int teamScore;
    private int opponentScore;

    public int getShotClock() {
        return shotClock;
    }

    public void setShotClock(final int shotClock) {
        this.shotClock = shotClock;
    }

    public GameTime getGameTime() {
        return gameTime;
    }

    public void setGameTime(final GameTime gameTime) {
        this.gameTime = gameTime;
    }

    public Possession getPossession() {
        return possession;
    }

    public void setPossession(final Possession possession) {
        this.possession = possession;
    }

    public Players getPlayers() {
        return player;
    }

    public void setPlayers(final Players player) {
        this.player = player;
    }

    public EventType getType() {
        return type;
    }

    public void setType(final EventType type) {
        this.type = type;
    }

    public EventDetail getDetail() {
        return detail;
    }

    public void setDetail(final EventDetail detail) {
        this.detail = detail;
    }

    public int getTeamScore() {
        return teamScore;
    }

    public void setTeamScore(final int teamScore) {
        this.teamScore = teamScore;
    }

    public int getOpponentScore() {
        return opponentScore;
    }

    public void setOpponentScore(final int opponentScore) {
        this.opponentScore = opponentScore;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((detail == null) ? 0 : detail.hashCode());
        result = prime * result + ((gameTime == null) ? 0 : gameTime.hashCode());
        result = prime * result + opponentScore;
        result = prime * result + ((player == null) ? 0 : player.hashCode());
        result = prime * result + ((possession == null) ? 0 : possession.hashCode());
        result = prime * result + shotClock;
        result = prime * result + teamScore;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        final LogEntry other = (LogEntry) obj;
        if (detail != other.detail) {
            return false;
        }
        if (gameTime == null) {
            if (other.gameTime != null) {
                return false;
            }
        } else if (!gameTime.equals(other.gameTime)) {
            return false;
        }
        if (opponentScore != other.opponentScore) {
            return false;
        }
        if (player == null) {
            if (other.player != null) {
                return false;
            }
        } else if (!player.equals(other.player)) {
            return false;
        }
        if (possession != other.possession) {
            return false;
        }
        if (shotClock != other.shotClock) {
            return false;
        }
        if (teamScore != other.teamScore) {
            return false;
        }
        if (type != other.type) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LogEntry [shotClock=" + shotClock + ", " + (gameTime != null ? "gameTime=" + gameTime + ", " : "")
                + (possession != null ? "possession=" + possession + ", " : "") + (player != null ? "player=" + player + ", " : "")
                + (type != null ? "type=" + type + ", " : "") + (detail != null ? "detail=" + detail + ", " : "") + "teamScore=" + teamScore + ", opponentScore="
                + opponentScore + "]";
    }

}
