package pl.codeset.pocket.add;

import pl.codeset.pocket.read.PocketItem;

import java.util.Objects;

public class AddItemResult {

    private final int status;
    private final PocketItem item;

    public AddItemResult(int status, PocketItem item) {
        this.status = status;
        this.item = item;
    }

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
