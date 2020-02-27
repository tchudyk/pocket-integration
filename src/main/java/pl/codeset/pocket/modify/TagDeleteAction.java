package pl.codeset.pocket.modify;

public class TagDeleteAction extends ModifyAction {

    public TagDeleteAction(String tag) {
        super("tag_delete");
        addAttribute("tag", tag);
    }
}
