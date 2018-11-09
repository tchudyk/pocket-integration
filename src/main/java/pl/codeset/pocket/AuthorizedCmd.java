package pl.codeset.pocket;

public abstract class AuthorizedCmd {
    private String consumer_key;
    private String access_token;

    void setAuth(PocketAuth pocketAuth) {
        consumer_key = pocketAuth.getConsumerKey();
        access_token = pocketAuth.getAccessToken();
    }
}
