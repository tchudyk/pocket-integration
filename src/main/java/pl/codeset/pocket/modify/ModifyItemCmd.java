package pl.codeset.pocket.modify;

import pl.codeset.pocket.AuthorizedCmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ModifyItemCmd extends AuthorizedCmd {

    private final List<Map<String, Object>> actions;

    private ModifyItemCmd(List<Map<String, Object>> actions) {
        this.actions = actions;
    }

    public static class Builder {
        private final List<Map<String, Object>> actions = new ArrayList<>();

        public Builder() {
        }

        public Builder action(ModifyAction action) {
            actions.add(action.toMap());
            return this;
        }

        public ModifyItemCmd build() {
            return new ModifyItemCmd(actions);
        }
    }
}
