package pl.codeset.pocket.modify;

import java.util.List;

public class TagsRemoveAction extends ModifyAction {

    public TagsRemoveAction(String itemId, String tags) {
        super("tags_remove");
        addAttribute("item_id", itemId);
        addAttribute("tags", tags);
    }

    public TagsRemoveAction(String itemId, List<String> tags) {
        this(itemId, String.join(",", tags));
    }
}
