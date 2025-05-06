package dev.codejar.controller;


import dev.codejar.model.entity.UserEntity;
import dev.codejar.payload.request.LoginRequest;
import dev.codejar.payload.request.SignupRequest;
import dev.codejar.payload.response.AuthenticationResponse;

import dev.codejar.repository.UserRepository;
import dev.codejar.security.service.AuthenticationService;
import dev.codejar.security.service.JwtService;
import dev.codejar.security.service.TokenService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository usersRepository;

    @Autowired
    private TokenService tokenService;

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }


    @PostMapping("/signup")
    public ResponseEntity<UserEntity> signup(@RequestBody SignupRequest registerRequest){
        UserEntity user = authenticationService.signup(registerRequest);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest){
        AuthenticationResponse saved = authenticationService.login(loginRequest);
        return ResponseEntity.ok(saved);

    }

}
