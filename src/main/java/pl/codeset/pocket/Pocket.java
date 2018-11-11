package pl.codeset.pocket;

import pl.codeset.pocket.add.AddItemCmd;
import pl.codeset.pocket.add.AddItemResult;
import pl.codeset.pocket.modify.ModifyItemCmd;
import pl.codeset.pocket.modify.ModifyResult;
import pl.codeset.pocket.read.GetItemsCmd;
import pl.codeset.pocket.read.GetItemsResult;

import java.io.IOException;

public class Pocket {

    private final PocketAuth pocketAuth;

    public Pocket(PocketAuth pocketAuth) {
        this.pocketAuth = pocketAuth;
    }

    public GetItemsResult getItems(GetItemsCmd cmd) throws IOException {
        ((AuthorizedCmd) cmd).setAuth(pocketAuth);
        HttpRequest getRequest = HttpRequest.postJson("https://getpocket.com/v3/get", cmd);
        return HttpProvider.get().send(getRequest).asObject(GetItemsResult.class);
    }

    public AddItemResult addItem(AddItemCmd cmd) throws IOException {
        ((AuthorizedCmd) cmd).setAuth(pocketAuth);
        HttpRequest addRequest = HttpRequest.postJson("https://getpocket.com/v3/add", cmd);
        return HttpProvider.get().send(addRequest).asObject(AddItemResult.class);
    }

    public ModifyResult modify(ModifyItemCmd cmd) throws IOException {
        ((AuthorizedCmd) cmd).setAuth(pocketAuth);
        HttpRequest modifyRequest = HttpRequest.postJson("https://getpocket.com/v3/send", cmd);
        return HttpProvider.get().send(modifyRequest).asObject(ModifyResult.class);
    }
}
