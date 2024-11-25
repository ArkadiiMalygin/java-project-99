package hexlet.code.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "User credentials")
public class AuthRequest {
    @NotBlank
    @Schema(description = "email")
    private String username;
    @NotBlank
    @Size(min = 3)
    @Schema(description = "password")
    private String password;
}
