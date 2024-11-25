package hexlet.code.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;


@Getter
@Setter
@Schema(description = "UsersInfo to update existing one")
public class UserUpdateDTO {

    @NotNull
    @Schema(description = "Users email to update existing one")
    private JsonNullable<String> email;

    @NotNull
    @Schema(description = "Users name to update existing one")
    private JsonNullable<String> firstName;

    @NotNull
    @Schema(description = "Users surname to update existing one")
    private JsonNullable<String> lastName;

    @NotNull
    @Size(min = 3)
    @Schema(description = "Users password to update existing one")
    private JsonNullable<String> password;
}
