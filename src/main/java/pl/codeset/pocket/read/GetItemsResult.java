package pl.codeset.pocket.read;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GetItemsResult {

    private final int status;
    private final int complete;
    private final Map<String, PocketItem> list;
    private final long since;

    public GetItemsResult(int status, int complete, Map<String, PocketItem> list, long since) {
        this.status = status;
        this.complete = complete;
        this.list = list;
        this.since = since;
    }

    public int getStatus() {
        return status;
    }

    public int getComplete() {
        return complete;
    }

    public List<PocketItem> getList() {
        return Collections.unmodifiableList(new ArrayList<>(list.values()));
    }

    public long getSince() {
        return since;
    }
}
