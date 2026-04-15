package pt.ual.auth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class AuthUserDto {
  private int userId;
  private String username;
  private String email;
  private String nome;
  private char admin;
}