package hexlet.code.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Schema(description = "TasksInfo for creation")
public class TaskCreateDTO {

    @NotBlank
    @Size(min = 1)
    @Schema(description = "TasksTitle")
    private String title;
    @Schema(description = "TasksIndex")
    private Long index;
    @Schema(description = "TasksContent")
    private String content;

    @NotBlank
    @Schema(description = "TasksStatus")
    private String status;
    @Schema(description = "TasksAssigneeId")
    private Long assigneeId;
    @Schema(description = "All TasksLabels")
    private Set<Long> taskLabelIds;
}
