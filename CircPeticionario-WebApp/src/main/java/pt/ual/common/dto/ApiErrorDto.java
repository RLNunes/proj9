package pt.ual.common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiErrorDto {
    private String code;
    private String message;

    public ApiErrorDto() {
    }

    public ApiErrorDto(String code, String message) {
        this.code = code;
        this.message = message;
    }
}