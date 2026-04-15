package pt.ual.auth.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.enterprise.context.ApplicationScoped;
import pt.ual.auth.model.AuthUser;
import pt.ual.utils.Utils;

@ApplicationScoped
public class AuthRepository {

  private static final String BASE_SELECT =
          "select user_id, username, email, password, admin, nome from app_hist.utilizadores ";

  private static final String FIND_BY_USERNAME =
          BASE_SELECT + "where lower(username) = lower(?)";

  private static final String FIND_BY_ID =
          BASE_SELECT + "where user_id = ?";

  public AuthUser findByUsername(String username) throws Exception {
    try (
            Connection c = Utils.getConnectionStock();
            PreparedStatement ps = c.prepareStatement(FIND_BY_USERNAME)
    ) {
      ps.setString(1, username);

      try (ResultSet rs = ps.executeQuery()) {
        return rs.next() ? mapUser(rs) : null;
      }
    }
  }

  public AuthUser findById(int userId) throws Exception {
    try (
            Connection c = Utils.getConnectionStock();
            PreparedStatement ps = c.prepareStatement(FIND_BY_ID)
    ) {
      ps.setInt(1, userId);

      try (ResultSet rs = ps.executeQuery()) {
        return rs.next() ? mapUser(rs) : null;
      }
    }
  }

  private AuthUser mapUser(ResultSet rs) throws SQLException {
    AuthUser user = new AuthUser();
    user.setUserId(rs.getInt("user_id"));
    user.setUsername(rs.getString("username"));
    user.setEmail(rs.getString("email"));
    user.setPasswordHash(rs.getString("password"));

    String adminValue = rs.getString("admin");
    user.setAdmin(adminValue != null && !adminValue.isEmpty() ? adminValue.charAt(0) : '\0');

    user.setNome(rs.getString("nome"));
    return user;
  }
}