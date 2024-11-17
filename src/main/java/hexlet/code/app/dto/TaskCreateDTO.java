package hexlet.code.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskCreateDTO {

    @NotBlank
    @Size(min = 1)
    private String title;

    private Long index;

    private String content;

    @NotBlank
    private String status;

    private Long assignee_id;


}