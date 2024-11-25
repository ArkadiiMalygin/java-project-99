package hexlet.code.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Schema(description = "TaskStatussInfo for creation")
public class TaskStatusDTO {
    @Schema(description = "TaskStatussId")
    private Long id;
    @Schema(description = "TaskStatussName")
    private String name;
    @Schema(description = "TaskStatussSlug")
    private String slug;
    @Schema(description = "TaskStatuss creation instance")
    private Instant createdAt;
}
