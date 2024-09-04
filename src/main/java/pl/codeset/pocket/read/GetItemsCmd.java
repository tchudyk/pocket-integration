package pl.codeset.pocket.read;

import pl.codeset.pocket.AuthorizedCmd;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

public class GetItemsCmd extends AuthorizedCmd {

    /**
     * Official tag used to find untagged items.
     */
    public static final String TAG_UNTAGGED = "_untagged_";

    private final ItemState state;
    private final Short favorite;
    private final String tag;
    private final ContentType contentType;
    private final Sort sort;
    private final DetailType detailType;
    private final String search;
    private final String domain;
    private final Long since;
    private final Integer count;
    private final Integer offset;
    private final Integer total;

    private GetItemsCmd(ItemState state, Short favorite, String tag,
            ContentType contentType, Sort sort, DetailType detailType, String search, String domain,
            Long since, Integer count, Integer offset, Integer total) {
        this.state = state;
        this.favorite = favorite;
        this.tag = tag;
        this.contentType = contentType;
        this.sort = sort;
        this.detailType = detailType;
        this.search = search;
        this.domain = domain;
        this.since = since;
        this.count = count;
        this.offset = offset;
        this.total = total;
    }

    public static class Builder {
        private ItemState state;
        private Short favorite;
        private String tag;
        private ContentType contentType;
        private Sort sort;
        private DetailType detailType;
        private String search;
        private String domain;
        private Long since;
        private Integer count;
        private Integer offset;
        private Integer total;

        public Builder() {
        }

        public Builder state(ItemState state) {
            this.state = state;
            return this;
        }

        public Builder favorite(Boolean favourite) {
            this.favorite = Optional.ofNullable(favourite)
                    .map(f -> f ? (short) 1 : 0)
                    .orElse(null);
            return this;
        }

        public Builder tag(String tag) {
            this.tag = tag;
            return this;
        }

        public Builder contentType(ContentType contentType) {
            this.contentType = contentType;
            return this;
        }

        public Builder sort(Sort sort) {
            this.sort = sort;
            return this;
        }

        public Builder detailType(DetailType detailType) {
            this.detailType = detailType;
            return this;
        }

        public Builder search(String search) {
            this.search = search;
            return this;
        }

        public Builder domain(String domain) {
            this.domain = domain;
            return this;
        }

        public Builder since(LocalDateTime localDateTime) {
            this.since = Optional.ofNullable(localDateTime)
                    .map(d -> d.toEpochSecond(ZoneOffset.UTC))
                    .orElse(null);
            return this;
        }

        public Builder count(Integer count) {
            this.count = count;
            return this;
        }

        public Builder offset(Integer offset) {
            this.offset = offset;
            return this;
        }

        public Builder total(Integer total) {
            this.total = total;
            return this;
        }

        public GetItemsCmd build() {
            return new GetItemsCmd(state, favorite, tag, contentType, sort, detailType,
                    search, domain, since, count, offset, total);
        }
    }
}
