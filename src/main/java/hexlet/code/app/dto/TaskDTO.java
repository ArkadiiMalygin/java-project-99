package hexlet.code.app.dto;

import hexlet.code.app.model.Label;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class TaskDTO {

    private Long id;

    private String title;

    private Long index;

    private String content;

    private String status;

    private Long assigneeId;

    private List<String> labels;

    private LocalDate createdAt;


}
