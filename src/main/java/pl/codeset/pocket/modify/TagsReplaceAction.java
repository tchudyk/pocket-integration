package pl.codeset.pocket.modify;

import java.util.List;

public class TagsReplaceAction extends ModifyAction {

    public TagsReplaceAction(String itemId, String tags) {
        super("tags_replace");
        addAttribute("item_id", itemId);
        addAttribute("tags", tags);
    }

    public TagsReplaceAction(String itemId, List<String> tags) {
        this(itemId, String.join(",", tags));
    }
}
