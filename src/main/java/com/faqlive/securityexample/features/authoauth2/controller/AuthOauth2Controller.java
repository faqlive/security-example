package com.faqlive.securityexample.features.authoauth2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/oauth2")
public class AuthOauth2Controller {

  @GetMapping("/status")
  public String status() {
    return "OAuth2 module ready";
  }
}
