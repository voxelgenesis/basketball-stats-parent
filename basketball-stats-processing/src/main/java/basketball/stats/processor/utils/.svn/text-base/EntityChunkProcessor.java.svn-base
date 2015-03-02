package basketball.stats.processor.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import basketball.stats.models.EntityChunk;

public class EntityChunkProcessor {

    final private Map<String, EntityChunk> data;

    public EntityChunkProcessor(final Map<String, EntityChunk> data) {
        this.data = data;
    }
    
    public int count(final String entity, final String field) {
        final EntityChunk chunk = data.get(entity);
        if (chunk == null) {
            return 0;
        }
        final List<String> fieldValues = chunk.getValue(field);
        if (fieldValues != null) {
            return fieldValues.size();
        }
        return 0;
    }

    public int sum(final String entity, final String field) {
        final EntityChunk chunk = data.get(entity);
        if (chunk == null) {
            return 0;
        }
        final List<String> fieldValues = chunk.getValue(field);
        if (fieldValues != null) {
            int sum = 0;
            for (final String fieldValue : fieldValues) {
                sum += Integer.parseInt(fieldValue);
            }
            return sum;
        }
        return 0;
    }

    public List<String> list(final String entity, final String field) {
        final EntityChunk chunk = data.get(entity);
        if (chunk == null) {
            return Collections.<String> emptyList();
        }
        final List<String> fieldValues = chunk.getValue(field);
        if (fieldValues != null) {
            return fieldValues;
        }
        return Collections.<String> emptyList();
    }

    public List<String> containsValue(final String entity, final String field, final String value) {
        final EntityChunk chunk = data.get(entity);
        if (chunk == null) {
            return Collections.<String> emptyList();
        }
        final List<String> fieldValues = chunk.getValue(field);
        if (fieldValues != null) {
            final List<String> returnValues = new ArrayList<>();
            for (final String fieldValue : fieldValues) {
                if (fieldValue.contains(value)) {
                    returnValues.add(fieldValue);
                }
            }
            return returnValues;
        }
        return Collections.<String> emptyList();
    }
}
