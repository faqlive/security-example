package com.faqlive.securityexample.features.authlocal.controller;

import com.faqlive.securityexample.features.authlocal.dto.LoginRequest;
import com.faqlive.securityexample.features.authlocal.service.AuthLocalService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/local")
public class AuthLocalController {

  private final AuthLocalService authLocalService;

  public AuthLocalController(AuthLocalService authLocalService) {
    this.authLocalService = authLocalService;
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@Valid @RequestBody LoginRequest request) {
    return ResponseEntity.ok(authLocalService.login(request));
  }
}
