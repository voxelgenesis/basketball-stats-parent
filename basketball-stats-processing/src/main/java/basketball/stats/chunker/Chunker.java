package basketball.stats.chunker;

import java.util.Set;

import basketball.stats.enums.Players;
import basketball.stats.models.LogEntry;

public interface Chunker {

    public boolean doProcess(final LogEntry entry, final Set<Players> players);

    // public void handleNoProcess(final LogEntry entry, final Set<Players> players);
}
