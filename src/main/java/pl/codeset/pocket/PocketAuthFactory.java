package pl.codeset.pocket;

import java.io.IOException;
import java.util.HashMap;

public class PocketAuthFactory {

    private final String consumerKey;
    private final String redirectUrl;
    private final String code;

    private PocketAuthFactory(String consumerKey, String redirectUrl) throws IOException {
        this.consumerKey = consumerKey;
        this.redirectUrl = redirectUrl;
        this.code = generateAccessCode();
    }

    public static PocketAuth createForAccessToken(String consumerKey, String accessToken) {
        return new PocketAuth(consumerKey, accessToken);
    }

    public static PocketAuth createForCode(String consumerKey, String code) throws IOException {
        String accessToken = generateAccessToken(consumerKey, code);
        return new PocketAuth(consumerKey, accessToken);
    }

    public static PocketAuthFactory create(String consumerKey, String redirectUrl) throws IOException {
        return new PocketAuthFactory(consumerKey, redirectUrl);
    }

    public String getCode() {
        return code;
    }

    public String getAuthUrl() {
        return "https://getpocket.com/auth/authorize?request_token=" + code + "&redirect_uri=" + redirectUrl;
    }

    public PocketAuth create() throws IOException {
        return createForCode(consumerKey, code);
    }

    private static String generateAccessToken(String consumerKey, String accessCode) throws IOException {
        HashMap<String, String> params = new HashMap<>();
        params.put("consumer_key", consumerKey);
        params.put("code", accessCode);
        HttpRequest request = HttpRequest.postJson("https://getpocket.com/v3/oauth/authorize", params);
        HttpResponse response = HttpClient.get().send(request);

        System.out.println(response.status);
        System.out.println(response.headerErrorCode);
        return (String) response.asMap().get("access_token");
    }

    private String generateAccessCode() throws IOException {
        HashMap<String, String> params = new HashMap<>();
        params.put("consumer_key", consumerKey);
        params.put("redirect_uri", redirectUrl);
        HttpRequest request = HttpRequest.postJson("https://getpocket.com/v3/oauth/request", params);
        HttpResponse response = HttpClient.get().send(request);

        System.out.println(response.status);
        System.out.println(response.headerErrorCode);
        System.out.println(response.asString());
        return (String) response.asMap().get("code");
    }
}
