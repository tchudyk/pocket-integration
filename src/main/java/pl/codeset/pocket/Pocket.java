package pl.codeset.pocket;

import pl.codeset.pocket.add.AddItemCmd;
import pl.codeset.pocket.add.AddItemResult;
import pl.codeset.pocket.modify.ModifyItemCmd;
import pl.codeset.pocket.modify.ModifyResult;
import pl.codeset.pocket.read.GetItemsCmd;
import pl.codeset.pocket.read.GetItemsResult;

import java.io.IOException;

import static java.util.Objects.nonNull;

public class Pocket {

    private final PocketAuth pocketAuth;

    public Pocket(PocketAuth pocketAuth) {
        this.pocketAuth = pocketAuth;
    }

    public GetItemsResult getItems(GetItemsCmd cmd) throws IOException {
        ((AuthorizedCmd) cmd).setAuth(pocketAuth);
        HttpRequest getRequest = HttpRequest.postJson("https://getpocket.com/v3/get", cmd);
        return processRequest(getRequest, GetItemsResult.class);
    }

    public AddItemResult addItem(AddItemCmd cmd) throws IOException {
        ((AuthorizedCmd) cmd).setAuth(pocketAuth);
        HttpRequest addRequest = HttpRequest.postJson("https://getpocket.com/v3/add", cmd);
        return processRequest(addRequest, AddItemResult.class);
    }

    public ModifyResult modify(ModifyItemCmd cmd) throws IOException {
        ((AuthorizedCmd) cmd).setAuth(pocketAuth);
        HttpRequest modifyRequest = HttpRequest.postJson("https://getpocket.com/v3/send", cmd);
        return processRequest(modifyRequest, ModifyResult.class);
    }

    private <T> T processRequest(HttpRequest request, Class<T> resultType) throws IOException {
        HttpResponse response = HttpClient.get().send(request);
        if (nonNull(response.headerErrorMessage)) {
            throw new PocketException(response.headerErrorCode, response.headerErrorMessage);
        }
        if (response.status >= 300) {
            throw new PocketException("Invalid response code: " + response.status + ". " + response.asString());
        }
        return response.asObject(resultType);
    }
}
