package ar.com.auth.converters;

import ar.com.auth.model.User;
import ar.com.auth.repositories.UserRepository;
import java.text.MessageFormat;
import org.hibernate.FetchNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class JwtToUserConverter implements Converter<Jwt, UsernamePasswordAuthenticationToken> {

  private final UserRepository userRepository;

  @Autowired
  public JwtToUserConverter(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UsernamePasswordAuthenticationToken convert(Jwt jwt) {
    User user = User.fromForUser(
        userRepository.findUserByUserNameAndUserIsEnabledTrue(jwt.getSubject())
            .orElseThrow(() -> new FetchNotFoundException(
                "User",
                MessageFormat.format(
                    "username {0} not found", jwt.getSubject())
            )));
    return new UsernamePasswordAuthenticationToken(user, jwt, user.getAuthorities());
  }
}
