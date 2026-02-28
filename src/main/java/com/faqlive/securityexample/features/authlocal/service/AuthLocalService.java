package com.faqlive.securityexample.features.authlocal.service;

import com.faqlive.securityexample.features.authlocal.dto.LoginRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthLocalService {

  public String login(LoginRequest request) {
    return "Login local recibido para: " + request.username();
  }
}
