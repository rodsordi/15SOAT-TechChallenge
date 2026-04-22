package br.com.fiap.garage.domain.service;

import br.com.fiap.garage.domain.entity.User;
import br.com.fiap.garage.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final UserRepository repository;

    public User login(String username) {
        return User.builder()
                .username(username)
                .password("abcd1234")
                .build();
//        return repository.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("Username not found"));
    }
}
