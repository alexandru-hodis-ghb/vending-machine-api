package mvp.vendingmachineapi.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("User not found.", new Throwable("Might not be registered."));
    }

}
