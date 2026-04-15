package pt.ual.auth.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthLoginRequestDto {

  @NotBlank
  @Size(max = 255)
  private String username;

  @NotBlank
  private String password;
}