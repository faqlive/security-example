package com.faqlive.securityexample.features.authsaml.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/saml")
public class AuthSamlController {

  @GetMapping("/status")
  public String status() {
    return "SAML module ready";
  }
}
