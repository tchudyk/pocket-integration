package pl.codeset.pocket.modify;

public class ArchiveAction extends ModifyAction {

    public ArchiveAction(String itemId) {
        super("archive");
        addAttribute("item_id", itemId);
    }
}
