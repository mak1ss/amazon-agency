package com.amazonagency.controllers;

import com.amazonagency.model.security.JwtAuthenticationResponse;
import com.amazonagency.model.security.SignInRequest;
import com.amazonagency.model.security.SignUpRequest;
import com.amazonagency.services.security.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private AuthenticationService authService;

    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest signUpRequest){
        return authService.signUp(signUpRequest);
    }

    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        return authService.signIn(request);
    }

    @Autowired
    public void setAuthService(AuthenticationService authService) {
        this.authService = authService;
    }
}
