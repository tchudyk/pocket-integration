package pl.codeset.pocket.modify;

public class DeleteAction extends ModifyAction {

    public DeleteAction(String itemId) {
        super("delete");
        addAttribute("item_id", itemId);
    }
}
