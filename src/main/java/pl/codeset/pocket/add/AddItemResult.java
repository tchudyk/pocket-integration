package pl.codeset.pocket.add;

import pl.codeset.pocket.read.PocketItem;

import java.util.Objects;

public class AddItemResult {

    private int status;
    private PocketItem item;

    public int getStatus() {
        return status;
    }

    public PocketItem getItem() {
        return item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddItemResult that = (AddItemResult) o;
        return status == that.status &&
                Objects.equals(item, that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, item);
    }

    @Override
    public String toString() {
        return "AddItemResult{" +
                "status=" + status +
                ", item=" + item +
                '}';
    }
}
