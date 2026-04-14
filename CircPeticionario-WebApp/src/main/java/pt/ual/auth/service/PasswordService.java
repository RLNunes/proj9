package pt.ual.auth.service;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordService {
  private static final int BCRYPT_ROUNDS = 10;

  public String hash(String rawPassword) {
    if (rawPassword == null || rawPassword.isEmpty()) {
      return null;
    }
    return BCrypt.hashpw(rawPassword, BCrypt.gensalt(BCRYPT_ROUNDS));
  }

  public String encodeForStorage(String password) {
    if (password == null || password.isEmpty()) {
      return null;
    }
    return isBcryptHash(password) ? password : hash(password);
  }

  public boolean matches(String rawPassword, String storedPassword) {
    if (rawPassword == null || storedPassword == null) {
      return false;
    }

    String compatibleHash = toJbcryptCompatibleHash(storedPassword);

    try {
      return BCrypt.checkpw(rawPassword, compatibleHash);
    } catch (IllegalArgumentException ex) {
      return false;
    }
  }

  public boolean isBcryptHash(String value) {
    return value != null && value.matches("^\\$2[aby]\\$\\d{2}\\$[./A-Za-z0-9]{53}$");
  }

  private String toJbcryptCompatibleHash(String hash) {
    if (hash == null || hash.length() < 4) {
      return hash;
    }
    if (hash.startsWith("$2b$") || hash.startsWith("$2y$")) {
      return "$2a$" + hash.substring(4);
    }
    return hash;
  }
}