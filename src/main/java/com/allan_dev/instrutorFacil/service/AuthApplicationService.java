package com.allan_dev.instrutorFacil.service;


import com.allan_dev.instrutorFacil.dto.request.LoginRequest;
import com.allan_dev.instrutorFacil.dto.response.LoginResponse;
import com.allan_dev.instrutorFacil.entity.UserEntity;
import com.allan_dev.instrutorFacil.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthApplicationService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public LoginResponse login(LoginRequest loginRequest) {

        var credentials = new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());

        var auth = authenticationManager.authenticate(credentials);

        UserEntity user = (UserEntity) auth.getPrincipal();

        String token = tokenService.generateToken(user);

        return new LoginResponse(user.getUsername(), token);
    }
}
