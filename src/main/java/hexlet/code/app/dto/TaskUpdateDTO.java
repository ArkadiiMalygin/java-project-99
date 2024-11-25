package hexlet.code.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

import java.util.Set;

@Getter
@Setter
@Schema(description = "TasksInfo for update existing one")
public class TaskUpdateDTO {

    @NotBlank
    @Size(min = 1)
    @Schema(description = "TasksTitle")
    private JsonNullable<String> title;
    @Schema(description = "TasksIndex")
    private JsonNullable<Long> index;
    @Schema(description = "TasksContent")
    private JsonNullable<String> content;

    @NotBlank
    @Schema(description = "TasksStatus")
    private JsonNullable<String> status;
    @Schema(description = "TasksAssigneeId")
    private JsonNullable<Long> assigneeId;
    @Schema(description = "All TasksLabels")
    private JsonNullable<Set<Long>> taskLabelIds;


}
