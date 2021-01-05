package pl.codeset.pocket.read;

import java.util.Objects;

public class ItemTag {
    private final String itemId;
    private final String tag;

    public String getItemId() {
        return itemId;
    }

    public String getTag() {
        return tag;
    }

    public ItemTag(String itemId, String tag) {
        this.itemId = itemId;
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "ItemTag{" +
                "itemId='" + itemId + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemTag itemTag = (ItemTag) o;
        return Objects.equals(itemId, itemTag.itemId) && Objects.equals(tag, itemTag.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, tag);
    }
}
