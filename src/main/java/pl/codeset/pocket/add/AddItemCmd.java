package pl.codeset.pocket.add;

import pl.codeset.pocket.AuthorizedCmd;

import java.util.List;

public class AddItemCmd extends AuthorizedCmd {
    private final String url;
    private final String title;
    private final String tags;
    private final String tweet_id;

    private AddItemCmd(String url, String title, String tags, String tweetId) {
        this.url = url;
        this.title = title;
        this.tags = tags;
        this.tweet_id = tweetId;
    }

    public static class Builder {
        private final String url;
        private String title;
        private String tags;
        private String tweetId;

        public Builder(String url) {
            this.url = url;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder tags(String tags) {
            this.tags = tags;
            return this;
        }

        public Builder tags(List<String> tags) {
            if (tags != null) {
                this.tags = String.join(",", tags);
            }
            return this;
        }

        public Builder tweetId(String tweetId) {
            this.tweetId = tweetId;
            return this;
        }

        public AddItemCmd build() {
            return new AddItemCmd(url, title, tags, tweetId);
        }
    }
}
