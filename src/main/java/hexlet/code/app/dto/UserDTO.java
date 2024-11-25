package hexlet.code.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;


@Getter
@Setter
@Schema(description = "UsersInfo")
public class UserDTO {
    @Schema(description = "UsersId")
    private Long id;
    @Schema(description = "Users email")
    private String email;
    @Schema(description = "Users name")
    private String firstName;
    @Schema(description = "Users surname")
    private String lastName;
    @Schema(description = "UsersCreation instance")
    private Instant createdAt;
}
