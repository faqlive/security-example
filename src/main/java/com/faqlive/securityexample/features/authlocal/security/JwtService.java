package com.faqlive.securityexample.features.authlocal.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.faqlive.securityexample.features.authlocal.domain.LocalUser;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  private static final String ISSUER = "security-example";
  private static final long EXPIRATION_SECONDS = 3600;
  private static final String DEV_SECRET = "change-this-dev-secret-before-production";

  public String createToken(LocalUser user) {
    Instant now = Instant.now();
    Instant expires = now.plus(EXPIRATION_SECONDS, ChronoUnit.SECONDS);

    return JWT.create()
      .withIssuer(ISSUER)
      .withSubject(user.id())
      .withClaim("username", user.username())
      .withClaim("email", user.email())
      .withArrayClaim("roles", user.roles().toArray(new String[0]))
      .withIssuedAt(now)
      .withExpiresAt(expires)
      .sign(Algorithm.HMAC256(DEV_SECRET));
  }

  public long expirationSeconds() {
    return EXPIRATION_SECONDS;
  }
}
