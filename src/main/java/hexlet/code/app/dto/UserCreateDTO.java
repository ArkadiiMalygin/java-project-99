package hexlet.code.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Schema(description = "UsersInfo for creation")
public class UserCreateDTO {
    @Email
    @Schema(description = "Users email")
    private String email;

    @NotBlank
    @Schema(description = "Users name")
    private String firstName;

    @NotBlank
    @Schema(description = "Users surname")
    private String lastName;

    @Size(min = 3)
    @Schema(description = "Users password")
    private String password;
}
