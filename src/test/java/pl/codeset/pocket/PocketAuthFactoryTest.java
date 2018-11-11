package pl.codeset.pocket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PocketAuthFactoryTest {

    private static final String CONSUMER_KEY = "12345-1122334455";

    @Mock
    HttpClient httpClient;

    @BeforeEach
    void setUp() {
        HttpProvider.change(httpClient);
    }

    @Test
    void shouldCreateAuthForToken() {
        // given
        String givenAccessToken = "some-valid-token";

        // when
        PocketAuth pocketAuth = PocketAuthFactory.createForAccessToken(CONSUMER_KEY, givenAccessToken);

        // then
        assertThat(pocketAuth.getAccessToken()).isEqualTo(givenAccessToken);
        assertThat(pocketAuth.getConsumerKey()).isEqualTo(CONSUMER_KEY);
    }

    @Test
    void shouldCreateAuthForCode() throws IOException {
        // given
        String authCode = "valid-code";
        given(httpClient.send(any())).willReturn(
                new HttpResponse("{\"access_token\":\"5678defg-5678-defg-5678-defg56\",\"username\":\"pocketuser\"}".getBytes())
        );

        // when
        PocketAuth pocketAuth = PocketAuthFactory.createForCode(CONSUMER_KEY, authCode);

        // then
        assertThat(pocketAuth.getConsumerKey()).isEqualTo(CONSUMER_KEY);
        assertThat(pocketAuth.getAccessToken()).isEqualTo("5678defg-5678-defg-5678-defg56");
    }

    @Test
    void shouldCreateAuthWithoutCode() throws IOException {
        // given
        String redirectUrl = "https://getpocket.com/";
        given(httpClient.send(any())).willReturn(
                new HttpResponse("{\"code\":\"dcba4321-dcba-4321-dcba-4321dc\"}".getBytes()),
                new HttpResponse("{\"access_token\":\"5678defg-5678-defg-5678-defg56\",\"username\":\"pocketuser\"}".getBytes())
        );

        // when
        PocketAuthFactory authFactory = PocketAuthFactory.create(CONSUMER_KEY, redirectUrl);
        PocketAuth pocketAuth = authFactory.create();

        // then
        assertThat(authFactory.getCode()).isEqualTo("dcba4321-dcba-4321-dcba-4321dc");
        assertThat(authFactory.getAuthUrl()).endsWith(redirectUrl);
        assertThat(pocketAuth.getConsumerKey()).isEqualTo(CONSUMER_KEY);
        assertThat(pocketAuth.getAccessToken()).isEqualTo("5678defg-5678-defg-5678-defg56");
    }
}