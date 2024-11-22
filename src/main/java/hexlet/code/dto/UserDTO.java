package hexlet.code.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


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

    private LocalDate createdAt;
}
