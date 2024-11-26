package hexlet.code.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Schema(description = "TasksInfo")
public class TaskDTO {
    @Schema(description = "TasksId")
    private Long id;
    @Schema(description = "TasksTitle")
    private String title;
    @Schema(description = "TasksIndex")
    private Long index;
    @Schema(description = "TasksContent")
    private String content;
    @Schema(description = "TasksStatus")
    private String status;
    @Schema(description = "TasksAssigneeId")
    private Long assigneeId;
    @Schema(description = "All TasksLabels")
    private List<Long> taskLabelIds;
    @Schema(description = "TasksCreationInstant")
    private LocalDate createdAt;


}
