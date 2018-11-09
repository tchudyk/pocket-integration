package pl.codeset.pocket;

public class PocketAuth {

    private final String consumerKey;
    private final String accessToken;

    PocketAuth(String consumerKey, String accessToken) {
        this.consumerKey = consumerKey;
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getConsumerKey() {
        return consumerKey;
    }
}
