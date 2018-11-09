package pl.codeset.pocket.modify;

public class TagRenameAction extends ModifyAction {

    public TagRenameAction(String oldTag, String newTag) {
        super("tag_rename");
        addAttribute("old_tag", oldTag);
        addAttribute("new_tag", newTag);
    }
}
