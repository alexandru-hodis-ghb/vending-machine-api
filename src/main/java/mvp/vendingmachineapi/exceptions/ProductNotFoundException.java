package mvp.vendingmachineapi.exceptions;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException() {
        super("Product not found.", new Throwable("Product might not be registered."));
    }
}
