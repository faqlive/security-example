package com.faqlive.securityexample.features.authlocal.service;

import com.faqlive.securityexample.features.authlocal.domain.LocalUser;
import com.faqlive.securityexample.features.authlocal.dto.AuthResponse;
import com.faqlive.securityexample.features.authlocal.dto.LoginRequest;
import com.faqlive.securityexample.features.authlocal.dto.RegisterRequest;
import com.faqlive.securityexample.features.authlocal.repository.InMemoryLocalUserRepository;
import com.faqlive.securityexample.features.authlocal.security.JwtService;
import java.util.Set;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthLocalService {

  private final InMemoryLocalUserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;

  public AuthLocalService(InMemoryLocalUserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          JwtService jwtService) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
  }

  public AuthResponse register(RegisterRequest request) {
    if (userRepository.findByUsername(request.username()).isPresent()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
    }
    if (userRepository.findByEmail(request.email()).isPresent()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
    }

    LocalUser user = new LocalUser(
      UUID.randomUUID().toString(),
      request.username().trim(),
      request.email().trim().toLowerCase(),
      passwordEncoder.encode(request.password()),
      Set.of("USER")
    );

    userRepository.save(user);
    String token = jwtService.createToken(user);
    return new AuthResponse(token, "Bearer", jwtService.expirationSeconds());
  }

  public AuthResponse login(LoginRequest request) {
    LocalUser user = userRepository.findByUsername(request.username().trim())
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));

    if (!passwordEncoder.matches(request.password(), user.passwordHash())) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
    }

    String token = jwtService.createToken(user);
    return new AuthResponse(token, "Bearer", jwtService.expirationSeconds());
  }
}
