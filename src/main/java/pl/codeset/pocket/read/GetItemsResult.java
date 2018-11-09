package pl.codeset.pocket.read;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GetItemsResult {

    private int status;
    private int complete;
    private Map<String, PocketItem> list;
    private long since;

    public int getStatus() {
        return status;
    }

    public int getComplete() {
        return complete;
    }

    public List<PocketItem> getList() {
        return Collections.unmodifiableList(new ArrayList<>(list.values()));
    }
}
