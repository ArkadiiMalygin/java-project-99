package hexlet.code.app.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;


@Getter
@Setter
public class UserUpdateDTO {

    @NotNull
    private JsonNullable<String> email;

    @NotNull
    private JsonNullable<String> firstName;

    @NotNull
    private JsonNullable<String> lastName;

    @NotNull
    @Size(min = 3)
    private JsonNullable<String> password;
}
