package com.source_user_auth.controller;

import com.api.framework.security.BearerContextHolder;
import com.source_user_auth.domain.auth.LoginRequest;
import com.source_user_auth.domain.auth.LoginResponse;
import com.source_user_auth.domain.auth.RegisterRequest;
import com.source_user_auth.domain.auth.RegisterResponse;
import com.source_user_auth.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@Api(description = "Module Authenticate")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ApiOperation(value = "Đăng ký tài khoản")
    @PostMapping("/sign-up")
    public ResponseEntity<LoginResponse> register(@Valid @RequestBody RegisterRequest request) throws IOException {
        String masterAccount = BearerContextHolder.getContext().getMasterAccount();
        logger.info("{} [Register] {}", masterAccount, request);
        return ResponseEntity.ok(authService.register(request));
    }

    @ApiOperation(value = "Đăng nhập")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) throws IOException {
        return ResponseEntity.ok(authService.login(request));
    }
}
