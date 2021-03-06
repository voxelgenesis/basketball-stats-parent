package basketball.stats.processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import basketball.stats.chunker.EntityChunker;
import basketball.stats.models.EntityChunk;
import basketball.stats.models.LogEntry;
import basketball.stats.models.TimeDivision;

public class LogProcessor implements ChunkProcessor {
    
    public Map<TimeDivision, Map<String, EntityChunk>> processTimeDivisions(final EntityChunker chunker, final List<LogEntry> log, final List<TimeDivision> timeDivisions) {
        final Map<TimeDivision, Map<String, EntityChunk>> map = new HashMap<>();
        for (final TimeDivision timeDivision : timeDivisions) {
            final Map<String, EntityChunk> timePeriod = processTimePeriod(chunker, timeDivision, log);
            map.put(timeDivision, timePeriod);
        }

        return map;
    }

    public Map<String, EntityChunk> processTimePeriod(final EntityChunker chunker, final TimeDivision timeDivision, final List<LogEntry> log) {
        final Map<String, EntityChunk> data = new HashMap<>();

        chunker.beforeProcessing(data);

        int i = 0;
        for (; i < log.size(); i++) {
            final LogEntry entry = log.get(i);

            chunker.preValidation(entry, timeDivision, data);

            final boolean doTime = (entry.getGameTime().compareTo(timeDivision.getStartTime()) >= 0) && (entry.getGameTime().compareTo(timeDivision.getEndTime()) < 0);
            if (doTime && chunker.isValid(entry, timeDivision, data)) {
                chunker.startValid(entry, timeDivision, data);
            } else {
                chunker.endValid(entry, timeDivision, data);
                continue;
            }

            chunker.postValidation(entry, timeDivision, data);
        }

        chunker.afterProcessing(data);
        System.out.println(data);
        return data;
    }
}
