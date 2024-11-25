package hexlet.code.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "TaskStatussInfo for creation")
public class TaskStatusCreateDTO {

    @NotBlank
    @Size(min = 1)
    @Schema(description = "TaskStatussName")
    private String name;

    @NotBlank
    @Size(min = 1)
    @Schema(description = "TaskStatussSlug")
    private String slug;

}
