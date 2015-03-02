package basketball.stats.chunker;

import java.util.Set;

import basketball.stats.enums.Players;
import basketball.stats.models.LogEntry;
import basketball.stats.util.GameTime;

public class GameTimeChunker implements Chunker {

    final GameTime start;
    final GameTime end;

    public GameTimeChunker(final GameTime start, final GameTime end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean doProcess(final LogEntry entry, final Set<Players> players) {
        return (entry.getGameTime().compareTo(start) >= 0) && (entry.getGameTime().compareTo(end) < 0);
    }

}
