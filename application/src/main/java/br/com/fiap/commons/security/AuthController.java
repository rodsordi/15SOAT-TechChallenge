package br.com.fiap.commons.security;

import br.com.fiap.garage.application.v1.swagger.AuthSwagger;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/auth")
public class AuthController implements AuthSwagger {

    private final TokenService tokenService;

    @PostMapping(path = "/login",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody
            LoginRequestDTO data) {
        var userDetails = tokenService.loadUserByUsername("");
        var token = tokenService.generateToken(userDetails.getUsername());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    record LoginRequestDTO(String username, String password) {}

    record LoginResponseDTO(String token) {}
}
