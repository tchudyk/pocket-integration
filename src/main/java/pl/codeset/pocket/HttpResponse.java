package pl.codeset.pocket;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpResponse {

    final byte[] body;
    final int status;
    final Integer headerErrorCode;
    final String headerErrorMessage;

    HttpResponse(byte[] body, int status, Integer headerErrorCode, String headerErrorMessage) {
        this.body = body;
        this.status = status;
        this.headerErrorCode = headerErrorCode;
        this.headerErrorMessage = headerErrorMessage;
    }

    public <T> T asObject(Class<T> type) {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create().fromJson(new String(body, StandardCharsets.UTF_8), type);
    }

    public String asString() {
        return new String(body, StandardCharsets.UTF_8);
    }

    public Map<String, Object> asMap() {
        return new Gson().fromJson(asString(), new TypeToken<Map<String, String>>() {
        }.getType());
    }
}
