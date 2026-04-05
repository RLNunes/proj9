package pt.ual.auth.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordService {
  private static final String HASH_PREFIX = "PBKDF2";
  private static final int ITERATIONS = 65536;
  private static final int KEY_LENGTH = 256;

  public String hash(String rawPassword) {
    if (rawPassword == null) {
      return null;
    }
    return BCrypt.hashpw(rawPassword, BCrypt.gensalt());
  }

  public String encodeForStorage(String password) {
    if (password == null) {
      return null;
    }
    return (isBcryptHash(password) || isPbkdf2Hash(password)) ? password : hash(password);
  }

  public boolean matches(String rawPassword, String storedPassword) {
    if (rawPassword == null || storedPassword == null) {
      return false;
    }
    if (isBcryptHash(storedPassword)) {
      return BCrypt.checkpw(rawPassword, storedPassword);
    }
    if (isPbkdf2Hash(storedPassword)) {
      String[] parts = storedPassword.split("\\$");
      if (parts.length != 4) {
        return false;
      }
      int iterations = Integer.parseInt(parts[1]);
      byte[] salt = Base64.getDecoder().decode(parts[2]);
      byte[] expectedHash = Base64.getDecoder().decode(parts[3]);
      byte[] actualHash = deriveKey(rawPassword.toCharArray(), salt, iterations, expectedHash.length * 8);
      return constantTimeEquals(expectedHash, actualHash);
    }
    return rawPassword.equals(storedPassword);
  }

  public boolean isPbkdf2Hash(String value) {
    return value != null && value.startsWith(HASH_PREFIX + "$");
  }

  public boolean isBcryptHash(String value) {
    return value != null && value.matches("^\\$2[aby]\\$\\d{2}\\$.*");
  }

  private byte[] deriveKey(char[] password, byte[] salt, int iterations, int keyLength) {
    try {
      PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength);
      SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
      return factory.generateSecret(spec).getEncoded();
    } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
      throw new IllegalStateException("Unable to hash password", ex);
    }
  }

  private boolean constantTimeEquals(byte[] a, byte[] b) {
    if (a.length != b.length) {
      return false;
    }
    int result = 0;
    for (int i = 0; i < a.length; i++) {
      result |= a[i] ^ b[i];
    }
    return result == 0;
  }
}
