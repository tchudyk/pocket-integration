package pl.codeset.pocket.modify;

import java.util.List;

public class TagsAddAction extends ModifyAction {

    public TagsAddAction(String itemId, String tags) {
        super("tags_add");
        addAttribute("item_id", itemId);
        addAttribute("tags", tags);
    }

    public TagsAddAction(String itemId, List<String> tags) {
        this(itemId, String.join(",", tags));
    }
}
