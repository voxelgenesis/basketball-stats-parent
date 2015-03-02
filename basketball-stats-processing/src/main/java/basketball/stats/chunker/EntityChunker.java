package basketball.stats.chunker;

import java.util.Map;

import basketball.stats.models.EntityChunk;
import basketball.stats.models.LogEntry;
import basketball.stats.models.TimeDivision;

public interface EntityChunker {

    public void beforeProcessing(Map<String, EntityChunk> data);

    public void afterProcessing(Map<String, EntityChunk> data);

    public void startValid(LogEntry entry, TimeDivision timeDivision, Map<String, EntityChunk> data);

    public void endValid(LogEntry entry, TimeDivision timeDivision, Map<String, EntityChunk> data);

    public boolean isValid(LogEntry entry, TimeDivision timeDivision, Map<String, EntityChunk> data);

    public void process(LogEntry entry, TimeDivision timeDivision, Map<String, EntityChunk> data);

    public void preValidation(LogEntry entry, TimeDivision timeDivision, Map<String, EntityChunk> data);

    public void postValidation(LogEntry entry, TimeDivision timeDivision, Map<String, EntityChunk> data);
}
