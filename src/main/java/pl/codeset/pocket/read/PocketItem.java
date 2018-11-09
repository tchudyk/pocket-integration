package pl.codeset.pocket.read;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class PocketItem {

    private String itemId;
    private String resolvedId;
    private String givenUrl;
    private String givenTitle;
    private short favorite;
    private short status;
    private long timeAdded;
    private long timeUpdated;
    private long timeRead;
    private long timeFavorited;
    private String resolvedTitle;
    private String resolvedUrl;
    private String excerpt;
    private short isArticle;
    private short isIndex;
    private short hasVideo;
    private short hasImage;
    private int wordCount;
    private String lang;
    private String topImageUrl;
    private long listenDurationEstimate;
    private DomainMeta domainMetadata;
    private Map<String, Image> images;
    private Map<String, Video> videos;

    public String getItemId() {
        return itemId;
    }

    public String getResolvedId() {
        return resolvedId;
    }

    public String getGivenUrl() {
        return givenUrl;
    }

    public String getGivenTitle() {
        return givenTitle;
    }

    public boolean isFavorite() {
        return favorite == 1;
    }

    public boolean isArchived() {
        return status == 1;
    }

    public boolean isDeleted() {
        return status == 2;
    }

    public LocalDateTime getTimeAdded() {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(timeAdded), ZoneId.of("UTC"));
    }

    public LocalDateTime getTimeUpdated() {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(timeUpdated), ZoneId.of("UTC"));
    }

    public Optional<LocalDateTime> getTimeRead() {
        return Optional.of(timeRead)
                .map(t -> t == 0 ? null : LocalDateTime.ofInstant(Instant.ofEpochSecond(timeRead), ZoneId.of("UTC")));
    }

    public Optional<LocalDateTime> getTimeFavorited() {
        return Optional.of(timeFavorited)
                .map(t -> t == 0 ? null : LocalDateTime.ofInstant(Instant.ofEpochSecond(timeFavorited), ZoneId.of("UTC")));
    }

    public String getResolvedTitle() {
        return resolvedTitle;
    }

    public String getResolvedUrl() {
        return resolvedUrl;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public boolean isArticle() {
        return isArticle == 1;
    }

    public boolean isIndex() {
        return isIndex == 1;
    }

    public boolean hasImage() {
        return hasImage > 0;
    }

    public boolean hasVideo() {
        return hasVideo > 0;
    }

    public int getWordCount() {
        return wordCount;
    }

    public String getLang() {
        return lang;
    }

    public String getTopImageUrl() {
        return topImageUrl;
    }

    public long getListenDurationEstimate() {
        return listenDurationEstimate;
    }

    public DomainMeta getDomainMetadata() {
        return domainMetadata;
    }

    public List<Image> getImages() {
        return Optional.ofNullable(images)
                .map(i -> Collections.unmodifiableList(new ArrayList<>(i.values())))
                .orElse(Collections.emptyList());
    }

    public List<Video> getVideos() {
        return Optional.ofNullable(videos)
                .map(i -> Collections.unmodifiableList(new ArrayList<>(i.values())))
                .orElse(Collections.emptyList());
    }

    public Optional<DomainMeta> getDomainMeta() {
        return Optional.ofNullable(domainMetadata);
    }

    @Override
    public String toString() {
        return "PocketItem{" +
                "itemId='" + itemId + '\'' +
                ", resolvedTitle='" + resolvedTitle + '\'' +
                ", resolvedUrl='" + resolvedUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PocketItem that = (PocketItem) o;
        return favorite == that.favorite &&
                status == that.status &&
                timeAdded == that.timeAdded &&
                timeUpdated == that.timeUpdated &&
                timeRead == that.timeRead &&
                timeFavorited == that.timeFavorited &&
                isArticle == that.isArticle &&
                isIndex == that.isIndex &&
                hasVideo == that.hasVideo &&
                hasImage == that.hasImage &&
                wordCount == that.wordCount &&
                listenDurationEstimate == that.listenDurationEstimate &&
                Objects.equals(itemId, that.itemId) &&
                Objects.equals(resolvedId, that.resolvedId) &&
                Objects.equals(givenUrl, that.givenUrl) &&
                Objects.equals(givenTitle, that.givenTitle) &&
                Objects.equals(resolvedTitle, that.resolvedTitle) &&
                Objects.equals(resolvedUrl, that.resolvedUrl) &&
                Objects.equals(excerpt, that.excerpt) &&
                Objects.equals(lang, that.lang) &&
                Objects.equals(topImageUrl, that.topImageUrl) &&
                Objects.equals(domainMetadata, that.domainMetadata) &&
                Objects.equals(images, that.images) &&
                Objects.equals(videos, that.videos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, resolvedId, givenUrl, givenTitle, favorite, status, timeAdded, timeUpdated,
                timeRead, timeFavorited, resolvedTitle, resolvedUrl, excerpt, isArticle, isIndex, hasVideo, hasImage,
                wordCount, lang, topImageUrl, listenDurationEstimate, domainMetadata, images, videos);
    }
}
