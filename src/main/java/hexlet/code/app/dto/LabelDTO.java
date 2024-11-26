package hexlet.code.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


import java.time.Instant;

@Getter
@Setter
@Schema(description = "LabelsInfo")
public class LabelDTO {
    @Schema(description = "LabelsId")
    private Long id;
    @Schema(description = "LabelsName")
    private String name;
    @Schema(description = "LabelsCreationInstant")
    private Instant createdAt;
}
