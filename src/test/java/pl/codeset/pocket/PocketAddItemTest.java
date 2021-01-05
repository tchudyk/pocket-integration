package pl.codeset.pocket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.codeset.pocket.add.AddItemCmd;
import pl.codeset.pocket.add.AddItemResult;

import java.io.IOException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PocketAddItemTest {

    @Mock
    HttpClient httpClient;

    Pocket pocket;

    @BeforeEach
    void setUp() {
        HttpProvider.change(httpClient);
        pocket = new Pocket(PocketAuthFactory.createForAccessToken("consumer-key", "access-token"));
    }

    @Test
    void shouldAddNewItem() throws IOException {
        // given
        given(httpClient.send(any())).willReturn(new HttpResponse(HTTP_RESPONSE_JSON.getBytes()));
        AddItemCmd cmd = new AddItemCmd.Builder("https://some-website.com/")
                .title("Item title")
                .tags(Arrays.asList("tag1", "tag2"))
                .build();

        // when
        AddItemResult result = pocket.addItem(cmd);

        // then
        assertThat(result.getItem().getItemId()).isEqualTo("229279689");
    }

    @Test
    void shouldThrowPocketException() throws IOException {
        // given
        given(httpClient.send(any())).willThrow(PocketException.class);
        AddItemCmd cmd = new AddItemCmd.Builder("https://some-website.com/")
                .title("Item title")
                .tags(Arrays.asList("tag1", "tag2"))
                .build();

        // when
        assertThrows(PocketException.class, () -> pocket.addItem(cmd));
    }

    private static final String HTTP_RESPONSE_JSON = "{\"status\":1,\"item\":{\"item_id\":\"229279689\",\n" +
            "\"resolved_id\":\"229279689\",\n" +
            "\"given_url\":\"http:\\/\\/www.grantland.com\\/blog\\/the-triangle\\/post\\/_\\/id\\/38347\\/ryder-cup-preview\",\n" +
            "\"given_title\":\"The Massive Ryder Cup Preview - The Triangle Blog - Grantland\",\n" +
            "\"favorite\":\"0\",\n" +
            "\"status\":\"0\",\n" +
            "\"resolved_title\":\"The Massive Ryder Cup Preview\",\n" +
            "\"resolved_url\":\"http:\\/\\/www.grantland.com\\/blog\\/the-triangle\\/post\\/_\\/id\\/38347\\/ryder-cup-preview\",\n" +
            "\"excerpt\":\"The list of things I love about the Ryder Cup is so long that it could fill a (tedious) novel, and golf fans can probably guess most of them.\",\n" +
            "\"is_article\":\"1\",\n" +
            "\"has_video\":\"1\",\n" +
            "\"has_image\":\"1\",\n" +
            "\"word_count\":\"3197\",\n" +
            "\"images\":{\"1\":{\"item_id\":\"229279689\",\"image_id\":\"1\",\n" +
            "    \"src\":\"http:\\/\\/a.espncdn.com\\/combiner\\/i?img=\\/photo\\/2012\\/0927\\/grant_g_ryder_cr_640.jpg&w=640&h=360\",\n" +
            "    \"width\":\"0\",\"height\":\"0\",\"credit\":\"Jamie Squire\\/Getty Images\",\"caption\":\"\"}},\n" +
            "\"videos\":{\"1\":{\"item_id\":\"229279689\",\"video_id\":\"1\",\n" +
            "    \"src\":\"http:\\/\\/www.youtube.com\\/v\\/Er34PbFkVGk?version=3&hl=en_US&rel=0\",\n" +
            "    \"width\":\"420\",\"height\":\"315\",\"type\":\"1\",\"vid\":\"Er34PbFkVGk\"}}}}";
}