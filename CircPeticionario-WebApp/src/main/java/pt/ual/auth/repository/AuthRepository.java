package pt.ual.auth.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import pt.ual.auth.model.AuthUser;
import pt.ual.utils.Utils;

public class AuthRepository {
  public AuthUser findByUsername(String username) throws Exception {
    Connection c = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    AuthUser user = null;
    try {
      c = Utils.getConnectionStock();
      String q = "select user_id, username, email, password, admin, nome from app_hist.utilizadores where lower(username) = lower(?)";
      ps = c.prepareStatement(q);
      ps.setString(1, username);
      rs = ps.executeQuery();
      if (rs.next()) {
        user = new AuthUser();
        user.setUserId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setPasswordHash(rs.getString("password"));
        String adminValue = rs.getString("admin");
        user.setAdmin(adminValue != null && !adminValue.isEmpty() ? adminValue.charAt(0) : '\0');
        user.setNome(rs.getString("nome"));
      }
    } finally {
      if (rs != null) {
        rs.close();
      }
      if (ps != null) {
        ps.close();
      }
      if (c != null) {
        c.close();
      }
    }
    return user;
  }

  public AuthUser findById(int userId) throws Exception {
    Connection c = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    AuthUser user = null;
    try {
      c = Utils.getConnectionStock();
      String q = "select user_id, username, email, password, admin, nome from app_hist.utilizadores where user_id = ?";
      ps = c.prepareStatement(q);
      ps.setInt(1, userId);
      rs = ps.executeQuery();
      if (rs.next()) {
        user = new AuthUser();
        user.setUserId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setPasswordHash(rs.getString("password"));
        String adminValue = rs.getString("admin");
        user.setAdmin(adminValue != null && !adminValue.isEmpty() ? adminValue.charAt(0) : '\0');
        user.setNome(rs.getString("nome"));
      }
    } finally {
      if (rs != null) {
        rs.close();
      }
      if (ps != null) {
        ps.close();
      }
      if (c != null) {
        c.close();
      }
    }
    return user;
  }
}


