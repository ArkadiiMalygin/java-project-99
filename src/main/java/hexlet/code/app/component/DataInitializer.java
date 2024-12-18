package hexlet.code.app.component;

import hexlet.code.app.model.User;
import hexlet.code.app.dto.LabelCreateDTO;
import hexlet.code.app.dto.TaskStatusCreateDTO;
import hexlet.code.app.repository.LabelRepository;
import hexlet.code.app.repository.TaskStatusRepository;
import hexlet.code.app.repository.UserRepository;
import hexlet.code.app.service.CustomUserDetailsService;
import hexlet.code.app.service.LabelService;
import hexlet.code.app.service.TaskStatusService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DataInitializer implements ApplicationRunner {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final CustomUserDetailsService userService;

    @Autowired
    private final TaskStatusRepository taskStatusRepository;

    @Autowired
    private final TaskStatusService taskStatusService;

    @Autowired
    private final LabelRepository labelRepository;

    @Autowired
    private final LabelService labelService;

    @Override
    public void run(ApplicationArguments args) {
        var email = "hexlet@example.com";
        var userData = new User();
        userData.setEmail(email);
        userData.setPasswordDigest("qwerty");
        userService.createUser(userData);

        var statusSlug = List.of("draft", "to_review", "to_be_fixed", "to_publish", "published");

        statusSlug.forEach(s -> {
            var taskStatus = new TaskStatusCreateDTO();
            taskStatus.setName(s);
            taskStatus.setSlug(s);

            taskStatusService.create(taskStatus);
        });

        var labelsName = List.of("feature", "bug", "fun", "not_fun", "life");

        labelsName.forEach(l -> {
            var label = new LabelCreateDTO();
            label.setName(l);

            labelService.create(label);
        });
    }
}
