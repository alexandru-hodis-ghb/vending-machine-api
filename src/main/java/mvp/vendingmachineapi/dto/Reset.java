package mvp.vendingmachineapi.dto;

import java.util.ArrayList;
import java.util.List;

public class Reset {

    private List<Integer> change;

    public Reset() {
        change = new ArrayList<>();
    }

    public List<Integer> getChange() {
        return change;
    }

    public void setChange(List<Integer> change) {
        this.change = change;
    }
}
