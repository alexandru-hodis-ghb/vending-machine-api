package mvp.vendingmachineapi.exceptions;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException() {
        super("Unauthorized operation.");
    }
}
