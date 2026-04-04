package pt.ual.auth.dto;

public class AuthUserDto {
  private int userId;
  private String username;
  private String email;
  private String nome;
  private char admin;

  public int getUserId() {
    return this.userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public char getAdmin() {
    return this.admin;
  }

  public void setAdmin(char admin) {
    this.admin = admin;
  }
}

