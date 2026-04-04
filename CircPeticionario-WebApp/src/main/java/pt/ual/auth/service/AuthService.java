package pt.ual.auth.service;

import java.util.Optional;
import javax.servlet.http.HttpSession;
import pt.ual.auth.dto.AuthLoginRequestDto;
import pt.ual.auth.dto.AuthUserDto;
import pt.ual.auth.model.AuthUser;
import pt.ual.auth.repository.AuthRepository;

public class AuthService {
  public static final String SESSION_ATTRIBUTE = "AUTH_USER";

  private final AuthRepository authRepository;
  private final PasswordService passwordService;

  public AuthService() {
    this(new AuthRepository(), new PasswordService());
  }

  AuthService(AuthRepository authRepository, PasswordService passwordService) {
    this.authRepository = authRepository;
    this.passwordService = passwordService;
  }

  public Optional<AuthUserDto> authenticate(AuthLoginRequestDto request) throws Exception {
    if (request == null || request.getUsername() == null || request.getPassword() == null) {
      return Optional.empty();
    }
    AuthUser user = this.authRepository.findByUsername(request.getUsername());
    if (user == null) {
      return Optional.empty();
    }
    if (!this.passwordService.matches(request.getPassword(), user.getPasswordHash())) {
      return Optional.empty();
    }
    return Optional.of(toDto(user));
  }

  public AuthUserDto getAuthenticatedUser(HttpSession session) {
    if (session == null) {
      return null;
    }
    Object value = session.getAttribute(SESSION_ATTRIBUTE);
    if (value instanceof AuthUserDto) {
      return (AuthUserDto) value;
    }
    if (value instanceof AuthUser) {
      AuthUser user = (AuthUser) value;
      AuthUserDto dto = toDto(user);
      session.setAttribute(SESSION_ATTRIBUTE, dto);
      return dto;
    }
    return null;
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

  public AuthUserDto toDto(AuthUser user) {
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


