package pl.codeset.pocket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

class IOUtil {

    static byte[] toBytes(InputStream is) throws IOException {
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
