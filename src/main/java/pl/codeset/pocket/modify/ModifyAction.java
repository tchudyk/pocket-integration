package pl.codeset.pocket.modify;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class ModifyAction {

    private Map<String, Object> attributes = new HashMap<>();

    protected ModifyAction(String action) {
        addAttribute("action", action);
        addAttribute("time", String.valueOf(System.currentTimeMillis() / 1000));
    }

    protected void addAttribute(String name, Object value) {
        attributes.put(name, value);
    }

    public Map<String, Object> toMap() {
        return Collections.unmodifiableMap(attributes);
    }
}
