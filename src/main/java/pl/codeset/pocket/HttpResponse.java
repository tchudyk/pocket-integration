package pl.codeset.pocket;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.nio.charset.StandardCharsets;
import java.util.Map;

class HttpResponse {

    private final byte[] body;

    HttpResponse(byte[] body) {
        this.body = body;
    }

    <T> T asObject(Class<T> type) {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create().fromJson(new String(body, StandardCharsets.UTF_8), type);
    }

    String asString() {
        return new String(body, StandardCharsets.UTF_8);
    }

    Map<String, Object> asMap() {
        return new Gson().fromJson(asString(), new TypeToken<Map<String, String>>() {
        }.getType());
    }
}
