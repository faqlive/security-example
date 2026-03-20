package com.faqlive.securityexample.features.authlocal.repository;

import com.faqlive.securityexample.features.authlocal.domain.LocalUser;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryLocalUserRepository {

  private final Map<String, LocalUser> byUsername = new ConcurrentHashMap<>();
  private final Map<String, LocalUser> byEmail = new ConcurrentHashMap<>();

  public Optional<LocalUser> findByUsername(String username) {
    return Optional.ofNullable(byUsername.get(username.toLowerCase()));
  }

  public Optional<LocalUser> findByEmail(String email) {
    return Optional.ofNullable(byEmail.get(email.toLowerCase()));
  }

  public LocalUser save(LocalUser user) {
    byUsername.put(user.username().toLowerCase(), user);
    byEmail.put(user.email().toLowerCase(), user);
    return user;
  }
}
