package com.faqlive.securityexample.features.authlocal.service;

import static org.junit.jupiter.api.Assertions.*;

import com.faqlive.securityexample.features.authlocal.dto.AuthResponse;
import com.faqlive.securityexample.features.authlocal.dto.LoginRequest;
import com.faqlive.securityexample.features.authlocal.dto.RegisterRequest;
import com.faqlive.securityexample.features.authlocal.repository.InMemoryLocalUserRepository;
import com.faqlive.securityexample.features.authlocal.security.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

class AuthLocalServiceTest {

  private AuthLocalService service;

  @BeforeEach
  void setUp() {
    service = new AuthLocalService(
      new InMemoryLocalUserRepository(),
      new BCryptPasswordEncoder(),
      new JwtService());
  }

  @Test
  void registerAndLoginShouldReturnBearerToken() {
    RegisterRequest registerRequest = new RegisterRequest("facu", "facu@example.com", "secret123");
    AuthResponse registerResponse = service.register(registerRequest);

    assertNotNull(registerResponse.accessToken());
    assertEquals("Bearer", registerResponse.tokenType());

    AuthResponse loginResponse = service.login(new LoginRequest("facu", "secret123"));
    assertNotNull(loginResponse.accessToken());
    assertEquals("Bearer", loginResponse.tokenType());
  }

  @Test
  void loginWithInvalidPasswordShouldFail() {
    service.register(new RegisterRequest("facu", "facu@example.com", "secret123"));

    assertThrows(ResponseStatusException.class,
      () -> service.login(new LoginRequest("facu", "wrong-pass")));
  }
}
