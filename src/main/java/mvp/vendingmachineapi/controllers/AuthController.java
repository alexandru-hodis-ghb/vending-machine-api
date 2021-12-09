package mvp.vendingmachineapi.controllers;

import mvp.vendingmachineapi.dto.AuthDTO;
import mvp.vendingmachineapi.security.JWTToken;
import mvp.vendingmachineapi.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JWTToken> login(@RequestBody AuthDTO authDto, HttpServletResponse response) {
        return ResponseEntity.ok(authService.login(authDto, response));
    }
}