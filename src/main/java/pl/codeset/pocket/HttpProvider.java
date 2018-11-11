package pl.codeset.pocket;

class HttpProvider {

    private static HttpClient client = new HttpClient();

    static void change(HttpClient client) {
        HttpProvider.client = client;
    }

    static HttpClient get() {
        return client;
    }

}
