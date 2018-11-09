package pl.codeset.pocket;

import javax.net.ssl.HttpsURLConnection;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;

import static java.util.Objects.nonNull;

class HttpClient implements AutoCloseable {

    private static HttpClient instance = new HttpClient();

    private HttpClient() {
    }

    public static HttpClient get() {
        return instance;
    }

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

    @Override
    public void close() {
    }

    private HttpResponse readResponse(HttpsURLConnection con) throws IOException {
        byte[] body;
        int statusCode = con.getResponseCode();
        Integer headerErrorCode = Optional.ofNullable(con.getHeaderField("x-error-code"))
                .map(Integer::parseInt)
                .orElse(null);
        String headerErrorMessage = Optional.ofNullable(con.getHeaderField("x-error"))
                .orElse(null);

        if (statusCode == 200) {
            try (InputStream is = con.getInputStream()) {
                body = toBytes(is);
            }
        } else {
            try (InputStream is = con.getErrorStream()) {
                body = toBytes(is);
            }
        }

        return new HttpResponse(body, statusCode, headerErrorCode, headerErrorMessage);
    }

    private static byte[] toBytes(InputStream is) throws IOException {
        try (ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
            int nRead;
            byte[] data = new byte[8192];
            while ((nRead = is.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }

            buffer.flush();
            return buffer.toByteArray();
        }
    }
}
