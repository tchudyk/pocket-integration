package pl.codeset.pocket.modify;

import java.util.List;
import java.util.Objects;

public class ModifyResult {

    private final List<Boolean> actionResults;
    private final Integer status;

    public ModifyResult(List<Boolean> actionResults, Integer status) {
        this.actionResults = actionResults;
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public List<Boolean> getActionResults() {
        return actionResults;
    }

    @Override
    public String toString() {
        return "ModifyResult{" +
                "actionResults=" + actionResults +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModifyResult that = (ModifyResult) o;
        return Objects.equals(actionResults, that.actionResults) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actionResults, status);
    }
}
