package hexlet.code.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "What can u filter tasks by")
public class TaskParamsDTO {
    @Schema(description = "Part of TasksTitle")
    private String titleCont;
    @Schema(description = "User that the tasks assign to")
    private Long assigneeId;
    @Schema(description = "Status that the tasks goes by")
    private String status;
    @Schema(description = "Label that the tasks goes by")
    private Long labelId;
}
