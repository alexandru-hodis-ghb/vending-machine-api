package mvp.vendingmachineapi.security;

public class JWTToken {

    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    String getToken() {
        return token;
    }

    void setToken(String token) {
        this.token = token;
    }
}