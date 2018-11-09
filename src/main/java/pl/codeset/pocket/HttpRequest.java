package pl.codeset.pocket;

import com.google.gson.Gson;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpRequest {

    final Method method;
    final URL url;
    final String contentType;
    final byte[] body;

    enum Method {
        GET, POST
    }

    private HttpRequest(Method method, URL url, String contentType, byte[] body) {
        this.method = method;
        this.url = url;
        this.contentType = contentType;
        this.body = body;
    }

    public static HttpRequest get(String url) {
        try {
            return new HttpRequest(Method.GET, new URL(url), null, null);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static HttpRequest postJson(String url, Object value) {
        return post(url, "application/json; charset=UTF-8",
                new Gson().toJson(value).getBytes(StandardCharsets.UTF_8));
    }

    public static HttpRequest post(String url, String contentType, byte[] body) {
        try {
            return new HttpRequest(Method.POST, new URL(url), contentType, body);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
