package pl.codeset.pocket.modify;

import java.net.URL;
import java.util.List;

public class AddItemAction extends ModifyAction {

    public AddItemAction(String itemId) {
        super("add");
        addAttribute("item_id", itemId);
    }

    public AddItemAction(URL url) {
        super("add");
        addAttribute("url", url.toString());
    }

    public void setTitle(String title) {
        addAttribute("title", title);
    }

    public void setTweeterId(String tweeterId) {
        addAttribute("ref_id", tweeterId);
    }

    public void setTags(String tags) {
        addAttribute("tags", tags);
    }

    public void setTags(List<String> tags) {
        if (tags != null) {
            addAttribute("tags", String.join(",", tags));
        }
    }
}
