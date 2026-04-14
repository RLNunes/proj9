package pt.ual.auth.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthUser {
  private int userId;
  private String username;
  private String email;
  private String nome;
  private char admin;
  private String passwordHash;
}