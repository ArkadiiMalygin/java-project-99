package hexlet.code.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

@Getter
@Setter
@Schema(description = "TaskStatussInfo to update")
public class TaskStatusUpdateDTO {

    @NotNull
    @Size(min = 1)
    @Schema(description = "TaskStatussName")
    private JsonNullable<String> name;

    @NotNull
    @Size(min = 1)
    @Schema(description = "TaskStatussSlug")
    private JsonNullable<String> slug;
}
