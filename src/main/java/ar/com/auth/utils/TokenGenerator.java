package ar.com.auth.utils;

import ar.com.auth.model.User;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

@Component
public class TokenGenerator {

  JwtEncoder accessTokenEncoder;
  JwtEncoder refreshTokenEncoder;

  @Autowired
  public TokenGenerator(JwtEncoder accessTokenEncoder,
      @Qualifier("jwtRefreshTokenEncoder") JwtEncoder refreshTokenEncoder) {
    this.accessTokenEncoder = accessTokenEncoder;
    this.refreshTokenEncoder = refreshTokenEncoder;
  }

  public String createAccessToken(User user) {
    Instant now = Instant.now();

    JwtClaimsSet claimsSet = JwtClaimsSet.builder()
        .issuer("myApp")
        .issuedAt(now)
        .expiresAt(now.plus(15, ChronoUnit.MINUTES))
        .subject(user.getUsername())
        .build();

    return accessTokenEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
  }

  public String createRefreshToken(User user) {
    Instant now = Instant.now();

    JwtClaimsSet claimsSet = JwtClaimsSet.builder()
        .issuer("myApp")
        .issuedAt(now)
        .expiresAt(now.plus(60, ChronoUnit.MINUTES))
        .subject(user.getUsername())
        .build();

    return refreshTokenEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
  }
}
