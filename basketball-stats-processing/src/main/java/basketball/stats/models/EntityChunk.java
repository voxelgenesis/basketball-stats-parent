package basketball.stats.models;

import java.util.List;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class EntityChunk {

    private MultiValueMap<String, String> keyValues = new LinkedMultiValueMap<>();

    public List<String> getValue(String key) {
        return keyValues.get(key);
    }

    public void setValue(String key, String value) {
        keyValues.set(key, value);
    }

    public void clearKey(String key) {
        keyValues.remove(key);
    }

    public void addValue(String key, String value) {
        List<String> keyValue = getValue(key);
        if (keyValue == null) {
            setValue(key, value);
            return;
        }

        keyValues.add(key, value);
    }

    @Override
    public String toString() {
        return "EntityChunk [" + (keyValues != null ? "keyValues=" + keyValues : "") + "]";
    }

}
