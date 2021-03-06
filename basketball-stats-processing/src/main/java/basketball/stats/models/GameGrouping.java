package basketball.stats.models;

import java.util.ArrayList;
import java.util.List;

public class GameGrouping {

    private int startGame;
    private int endGame;
    private String name;

    public GameGrouping(final int startGame, final int endGame, final String name) {
        super();
        this.startGame = startGame;
        this.endGame = endGame;
        this.name = name;
    }

    public List<Integer> getGames() {
        final List<Integer> games = new ArrayList<>();
        for (int i = startGame; i <= endGame; i++) {
            games.add(i);
        }
        return games;
    }

    public int getStartGame() {
        return startGame;
    }

    public void setStartGame(final int startGame) {
        this.startGame = startGame;
    }

    public int getEndGame() {
        return endGame;
    }

    public void setEndGame(final int endGame) {
        this.endGame = endGame;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + endGame;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + startGame;
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
        final GameGrouping other = (GameGrouping) obj;
        if (endGame != other.endGame) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (startGame != other.startGame) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GameGrouping [startGame=" + startGame + ", endGame=" + endGame + ", " + (name != null ? "name=" + name : "") + "]";
    }

}
