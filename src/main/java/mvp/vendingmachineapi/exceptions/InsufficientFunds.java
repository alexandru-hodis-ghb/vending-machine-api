package mvp.vendingmachineapi.exceptions;

public class InsufficientFunds extends RuntimeException {

    public InsufficientFunds() {
        super("Insufficient funds.");
    }
}
