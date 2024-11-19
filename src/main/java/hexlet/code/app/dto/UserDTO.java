package hexlet.code.app.dto;

import hexlet.code.app.model.Task;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class UserDTO {
    private Long id;

    //TODO
    private String email;

    private String firstName;

    private String lastName;

    //TODO
    //private List<String> tasksTitle;

    private Instant createdAt;
}
