package basketball.stats.chunker;

import java.util.Set;

import basketball.stats.enums.Players;
import basketball.stats.models.LogEntry;
import basketball.stats.util.GameTime;

public class MultiManChunker extends GameTimeChunker implements Chunker {

    final Set<Players> players;

    public MultiManChunker(final Set<Players> players, final GameTime start, final GameTime end) {
        super(start, end);
        this.players = players;
    }

    @Override
    public boolean doProcess(final LogEntry entry, final Set<Players> players) {
        boolean contains = true;
        for (final Players player : this.players) {
            if (!players.contains(player)) {
                contains = false;
            }
        }
        return contains && super.doProcess(entry, players);
    }

    public static void getFiveManGroups() {

    }
}
