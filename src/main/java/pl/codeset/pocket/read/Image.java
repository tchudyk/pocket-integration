package pl.codeset.pocket.read;

import java.util.Objects;

public class Image {
    private final String itemId;
    private final String imageId;
    private final String src;
    private final String width;
    private final String height;
    private final String credit;
    private final String caption;

    public Image(String itemId, String imageId, String src, String width, String height, String credit, String caption) {
        this.itemId = itemId;
        this.imageId = imageId;
        this.src = src;
        this.width = width;
        this.height = height;
        this.credit = credit;
        this.caption = caption;
    }

    public String getItemId() {
        return itemId;
    }

    public String getImageId() {
        return imageId;
    }

    public String getSrc() {
        return src;
    }

    public String getWidth() {
        return width;
    }

    public String getHeight() {
        return height;
    }

    public String getCredit() {
        return credit;
    }

    public String getCaption() {
        return caption;
    }

    @Override
    public String toString() {
        return "Image{" +
                "itemId='" + itemId + '\'' +
                ", imageId='" + imageId + '\'' +
                ", src='" + src + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(itemId, image.itemId) && Objects.equals(imageId, image.imageId) && Objects.equals(src, image.src) && Objects.equals(width, image.width) && Objects.equals(height, image.height) && Objects.equals(credit, image.credit) && Objects.equals(caption, image.caption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, imageId, src, width, height, credit, caption);
    }
}
