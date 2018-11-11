package pl.codeset.pocket;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.codeset.pocket.modify.ArchiveAction;
import pl.codeset.pocket.modify.DeleteAction;
import pl.codeset.pocket.modify.FavoriteAction;
import pl.codeset.pocket.modify.ModifyItemCmd;
import pl.codeset.pocket.modify.ModifyResult;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PocketModifyTest {

    @Mock
    HttpClient httpClient;

    Pocket pocket;

    @BeforeEach
    void setUp() {
        HttpProvider.change(httpClient);
        pocket = new Pocket(PocketAuthFactory.createForAccessToken("consumer-key", "access-token"));
    }

    @Test
    void shouldExecuteModifyActions() throws IOException {
        // given
        given(httpClient.send(any())).willReturn(new HttpResponse(HTTP_RESPONSE_JSON.getBytes()));
        ModifyItemCmd cmd = new ModifyItemCmd.Builder()
                .action(new ArchiveAction("some-id"))
                .action(new DeleteAction("other-id"))
                .action(new FavoriteAction("some-id"))
                .build();

        // when
        ModifyResult result = pocket.modify(cmd);

        // then
        assertThat(result.getActionResults())
                .containsExactly(true, true, false);
    }


    @Test
    void shouldThrowPocketException() throws IOException {
        // given
        given(httpClient.send(any())).willThrow(PocketException.class);
        ModifyItemCmd cmd = new ModifyItemCmd.Builder()
                .action(new ArchiveAction("some-id"))
                .action(new DeleteAction("other-id"))
                .action(new FavoriteAction("some-id"))
                .build();

        // when
        Assertions.assertThrows(PocketException.class, () -> {
            pocket.modify(cmd);
        });
    }

    private static final String HTTP_RESPONSE_JSON = "{\"action_results\":[true,true,false],\"status\":1}";
}