package pl.codeset.pocket;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;

import static java.util.Objects.nonNull;

class HttpClient {

    HttpResponse send(HttpRequest request) throws IOException {
        HttpsURLConnection con = (HttpsURLConnection) request.url.openConnection();
        con.setRequestMethod(request.method.name());
        if (nonNull(request.body)) {
            con.setDoOutput(true);
            con.setRequestProperty("x-accept", "application/json");
            con.setRequestProperty("content-type", request.contentType);
            try (OutputStream os = con.getOutputStream()) {
                os.write(request.body);
                os.flush();
            }
        }
        return readResponse(con);
    }

    private HttpResponse readResponse(HttpsURLConnection con) throws IOException {
        int statusCode = con.getResponseCode();
        if (statusCode == HttpsURLConnection.HTTP_OK) {
            try (InputStream is = con.getInputStream()) {
                return new HttpResponse(IOUtil.toBytes(is));
            }
        } else {
            Integer headerErrorCode = Optional.ofNullable(con.getHeaderField("x-error-code"))
                    .map(Integer::parseInt)
                    .orElse(statusCode);
            String headerErrorMessage = Optional.ofNullable(con.getHeaderField("x-error"))
                    .orElseGet(() -> {
                        try (InputStream is = con.getErrorStream()) {
                            return new String(IOUtil.toBytes(is));
                        } catch (IOException e) {
                            return null;
                        }
                    });

            throw new PocketException(headerErrorCode, headerErrorMessage);
        }
    }
}
