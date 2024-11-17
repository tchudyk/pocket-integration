package pl.codeset.pocket;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.codeset.pocket.read.GetItemsCmd;
import pl.codeset.pocket.read.GetItemsResult;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PocketListTest {

    @Mock
    HttpClient httpClient;

    Pocket pocket;

    @BeforeEach
    void setUp() {
        HttpProvider.change(httpClient);
        pocket = new Pocket(PocketAuthFactory.createForAccessToken("consumer-key", "access-token"));
    }

    @Test
    void shouldSerializeGetItemsCmd() {
        // given
        GetItemsCmd getItemsRequest = new GetItemsCmd.Builder()
            .count(1)
            .total(10)
            .build();
        // when
        String json = new Gson().toJson(getItemsRequest);
        // then
        assertThat(json).contains("\"total\":\"10\"");
        assertThat(json).contains("\"count\":1");
    }

    @Test
    void shouldReturnList() throws IOException {
        // given
        given(httpClient.send(any())).willReturn(new HttpResponse(HTTP_RESPONSE_LIST_JSON.getBytes()));
        GetItemsCmd getItemsRequest = new GetItemsCmd.Builder()
                .count(1)
                .build();

        // when
        GetItemsResult result = pocket.getItems(getItemsRequest);

        // then
        assertThat(result.getList().size()).isEqualTo(1);
        assertThat(result.getList().get(0).getItemId()).isEqualTo("229279689");
        assertThat(result.getList().get(0).getTags().size()).isEqualTo(2);
    }

    @Test
    void shouldThrowPocketException() throws IOException {
        // given
        given(httpClient.send(any())).willThrow(PocketException.class);
        GetItemsCmd getItemsRequest = new GetItemsCmd.Builder()
                .count(1)
                .build();

        // when
        assertThrows(PocketException.class, () -> pocket.getItems(getItemsRequest));
    }

    private static final String HTTP_RESPONSE_LIST_JSON = "{\"status\":1,\"list\":{\"229279689\":{\"item_id\":\"229279689\",\n" +
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
            "\"tags\": {\n" +
            "    \"tag1\": {\n" +
            "        \"item_id\": \"229279689\",\n" +
            "        \"tag\": \"tag1\"\n" +
            "    },\n" +
            "    \"tag2\": {\n" +
            "        \"item_id\": \"229279689\",\n" +
            "        \"tag\": \"tag2\"\n" +
            "    }},\n" +
            "\"images\":{\"1\":{\"item_id\":\"229279689\",\"image_id\":\"1\",\n" +
            "    \"src\":\"http:\\/\\/a.espncdn.com\\/combiner\\/i?img=\\/photo\\/2012\\/0927\\/grant_g_ryder_cr_640.jpg&w=640&h=360\",\n" +
            "    \"width\":\"0\",\"height\":\"0\",\"credit\":\"Jamie Squire\\/Getty Images\",\"caption\":\"\"}},\n" +
            "\"videos\":{\"1\":{\"item_id\":\"229279689\",\"video_id\":\"1\",\n" +
            "    \"src\":\"http:\\/\\/www.youtube.com\\/v\\/Er34PbFkVGk?version=3&hl=en_US&rel=0\",\n" +
            "    \"width\":\"420\",\"height\":\"315\",\"type\":\"1\",\"vid\":\"Er34PbFkVGk\"}}}}}";
}