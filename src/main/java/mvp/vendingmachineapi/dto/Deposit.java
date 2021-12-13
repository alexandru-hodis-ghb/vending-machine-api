package mvp.vendingmachineapi.dto;

import javax.validation.constraints.NotNull;


public class Deposit {

    @NotNull
    private Long userId;
    @NotNull
    private Integer amount;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
