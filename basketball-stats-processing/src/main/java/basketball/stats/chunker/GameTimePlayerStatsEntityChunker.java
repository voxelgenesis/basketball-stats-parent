package basketball.stats.chunker;

import java.util.Map;

import basketball.stats.models.EntityChunk;
import basketball.stats.models.LogEntry;
import basketball.stats.models.TimeDivision;
import basketball.stats.util.GameTime;

public class GameTimePlayerStatsEntityChunker extends PlayerStatsEntityChunker implements EntityChunker {
    
    final GameTime start;
    final GameTime end;

    public GameTimePlayerStatsEntityChunker(final GameTime start, final GameTime end) {
        super();
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean isValid(LogEntry entry, TimeDivision timeDivision, Map<String, EntityChunk> data) {
        return (entry.getGameTime().compareTo(start) >= 0) && (entry.getGameTime().compareTo(end) < 0);
    }

}
