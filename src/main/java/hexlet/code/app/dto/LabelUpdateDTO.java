package hexlet.code.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

@Getter
@Setter
@Schema(description = "LabelsInfo for update")
public class LabelUpdateDTO {
    @NotNull
    @Size(min = 1)
    @Schema(description = "New LabelsName")
    private JsonNullable<String> name;

}
