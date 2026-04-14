package pt.ual.auth.service;

import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import pt.ual.auth.dto.AuthLoginRequestDto;
import pt.ual.auth.dto.AuthUserDto;
import pt.ual.auth.model.AuthUser;
import pt.ual.auth.repository.AuthRepository;
import pt.ual.auth.service.PasswordService;

@ApplicationScoped
public class AuthService {
  public static final String SESSION_ATTRIBUTE = "AUTH_USER";

  private final AuthRepository authRepository;
  private final PasswordService passwordService;

  @Inject
  public AuthService(AuthRepository authRepository, PasswordService passwordService) {
    this.authRepository = authRepository;
    this.passwordService = passwordService;
  }

  public Optional<AuthUserDto> authenticate(AuthLoginRequestDto request) throws Exception {
    if (request == null) {
      return Optional.empty();
    }

    String username = request.getUsername();
    String password = request.getPassword();

    if (username == null || username.trim().isEmpty() || password == null || password.isEmpty()) {
      return Optional.empty();
    }

    AuthUser user = this.authRepository.findByUsername(username.trim());
    if (user == null) {
      return Optional.empty();
    }

    if (!this.passwordService.matches(password, user.getPasswordHash())) {
      return Optional.empty();
    }

    return Optional.of(toDto(user));
  }

  public AuthUserDto getAuthenticatedUser(HttpSession session) {
    if (session == null) {
      return null;
    }

    Object value = session.getAttribute(SESSION_ATTRIBUTE);
    return (value instanceof AuthUserDto) ? (AuthUserDto) value : null;
  }

  public void establishSession(HttpSession session, AuthUserDto user) {
    if (session != null && user != null) {
      session.setAttribute(SESSION_ATTRIBUTE, user);
    }
  }

  public void logout(HttpSession session) {
    if (session != null) {
      session.invalidate();
    }
  }

  private AuthUserDto toDto(AuthUser user) {
    if (user == null) {
      return null;
    }

    AuthUserDto dto = new AuthUserDto();
    dto.setUserId(user.getUserId());
    dto.setUsername(user.getUsername());
    dto.setEmail(user.getEmail());
    dto.setNome(user.getNome());
    dto.setAdmin(user.getAdmin());
    return dto;
  }
}