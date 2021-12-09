package mvp.vendingmachineapi.dto;

import com.sun.istack.NotNull;
import mvp.vendingmachineapi.util.Constants;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class AuthDTO {

    @Pattern(regexp = Constants.USERNAME_REGEX)
    @NotNull
    @Size(min = Constants.USERNAME_MIN_LENGTH, max = Constants.USERNAME_MAX_LENGTH)
    private String username;

    @NotNull
    @Size(min = Constants.PASSWORD_MIN_LENGTH, max = Constants.PASSWORD_MAX_LENGTH)
    private String password;

    @NotNull
    private Boolean rememberMe;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Boolean isRememberMe() {
        return rememberMe;
    }

    @Override
    public String toString() {
        return "AuthDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", rememberMe=" + rememberMe +
                '}';
    }
}