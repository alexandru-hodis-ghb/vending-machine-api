package mvp.vendingmachineapi.services;

import mvp.vendingmachineapi.dto.AuthDTO;
import mvp.vendingmachineapi.security.JWTToken;
import mvp.vendingmachineapi.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public class AuthService {

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JWTToken login(AuthDTO authDto, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword());
        Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication, authDto.isRememberMe());
        response.addHeader(TokenProvider.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new JWTToken(jwt);
    }
}
