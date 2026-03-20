package com.faqlive.securityexample.features.authlocal.domain;

import java.util.Set;

public record LocalUser(
  String id,
  String username,
  String email,
  String passwordHash,
  Set<String> roles
) {}
