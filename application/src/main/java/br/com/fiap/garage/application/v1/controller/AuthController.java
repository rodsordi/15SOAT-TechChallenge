package br.com.fiap.garage.application.v1.controller;

import br.com.fiap.commons.security.TokenService;
import br.com.fiap.garage.application.v1.swagger.AuthSwagger;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/auth")
public class AuthController implements AuthSwagger {

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    @PostMapping(path = "/login",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody
            LoginRequestDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken(auth.getName());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    record LoginRequestDTO(String username, String password) {}

    record LoginResponseDTO(String token) {}
}
