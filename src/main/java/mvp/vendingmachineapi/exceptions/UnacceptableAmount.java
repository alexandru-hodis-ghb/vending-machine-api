package mvp.vendingmachineapi.exceptions;

public class UnacceptableAmount extends RuntimeException {

    public UnacceptableAmount() {
        super("Unacceptable amount.");
    }
}
