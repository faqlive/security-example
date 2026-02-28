package com.faqlive.securityexample.features.authlocal.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
  @NotBlank String username,
  @NotBlank String password
) {}
