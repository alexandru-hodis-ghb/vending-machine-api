package mvp.vendingmachineapi.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
@ConfigurationProperties("application")
public class ApplicationProperties {

    @Value("${auth.secret}")
    private String secret;
    @Value("${auth.token-validity-in-seconds}")
    private long tokenValidityInSeconds;
    @Value("${auth.token-validity-in-seconds-for-remember-me}")
    private long tokenValidityInSecondsForRememberMe;

    public String getSecret() {
        return secret;
    }

    public long getTokenValidityInSeconds() {
        return tokenValidityInSeconds;
    }

    public long getTokenValidityInSecondsForRememberMe() {
        return tokenValidityInSecondsForRememberMe;
    }

}