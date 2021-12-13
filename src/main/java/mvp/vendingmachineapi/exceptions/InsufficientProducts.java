package mvp.vendingmachineapi.exceptions;

public class InsufficientProducts extends RuntimeException {

    public InsufficientProducts() {
        super("Insufficient products.");
    }
}
