package mvp.vendingmachineapi.dto;

import java.util.ArrayList;
import java.util.List;

public class BuyResponse {

    private Integer totalSpent;
    private Long productId;
    private List<Integer> change;

    public BuyResponse() {
        change = new ArrayList<>();
    }

    public Integer getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(Integer totalSpent) {
        this.totalSpent = totalSpent;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public List<Integer> getChange() {
        return change;
    }

    public void setChange(List<Integer> change) {
        this.change = change;
    }
}
