package mvp.vendingmachineapi.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "amount_available")
    @NotNull
    private Integer amountAvailable;

    @Column(name = "cost")
    @NotNull
    private Integer cost;

    @Column(name = "product_name")
    @NotNull
    private String productName;

    @Column(name = "seller_id")
    @NotNull
    private Long sellerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAmountAvailable() {
        return amountAvailable;
    }

    public void setAmountAvailable(Integer amountAvailable) {
        this.amountAvailable = amountAvailable;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
}
