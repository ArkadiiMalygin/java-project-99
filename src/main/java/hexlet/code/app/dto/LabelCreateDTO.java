package hexlet.code.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "LabelsInfo for creation")
public class LabelCreateDTO {

    @NotBlank
    @Size(min = 3)
    @Schema(description = "Requires LabelsName")
    private String name;

}
