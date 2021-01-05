package pl.codeset.pocket.read;

import java.util.Objects;

public class Video {
    private final String itemId;
    private final String videoId;
    private final String src;
    private final String width;
    private final String height;
    private final String type;
    private final String vid;

    public Video(String itemId, String videoId, String src, String width, String height, String type, String vid) {
        this.itemId = itemId;
        this.videoId = videoId;
        this.src = src;
        this.width = width;
        this.height = height;
        this.type = type;
        this.vid = vid;
    }

    public String getItemId() {
        return itemId;
    }

    public String getVideoId() {
        return videoId;
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

    public String getType() {
        return type;
    }

    public String getVid() {
        return vid;
    }

    @Override
    public String toString() {
        return "Video{" +
                "itemId='" + itemId + '\'' +
                ", videoId='" + videoId + '\'' +
                ", src='" + src + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
        return Objects.equals(itemId, video.itemId) && Objects.equals(videoId, video.videoId) && Objects.equals(src, video.src) && Objects.equals(width, video.width) && Objects.equals(height, video.height) && Objects.equals(type, video.type) && Objects.equals(vid, video.vid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, videoId, src, width, height, type, vid);
    }
}
