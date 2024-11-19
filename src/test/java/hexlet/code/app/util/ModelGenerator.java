package hexlet.code.app.util;

import hexlet.code.app.model.Label;
import hexlet.code.app.model.Task;
import hexlet.code.app.model.TaskStatus;
import hexlet.code.app.model.User;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import net.datafaker.Faker;
import org.instancio.Instancio;
import org.instancio.Model;
import org.instancio.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
public class ModelGenerator {
    private Model<Task> taskModel;
    private Model<User> userModel;
    private Model<Label> labelModel;
    private  Model<TaskStatus> taskStatusModel;

    public ModelGenerator() {
        var faker = new Faker();

        userModel = Instancio.of(User.class)
                .ignore(Select.field(User::getId))
                .supply(Select.field(User::getFirstName), () -> faker.name().firstName())
                .supply(Select.field(User::getEmail), () -> faker.internet().emailAddress())
                .toModel();

        taskModel = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getName), () -> faker.lorem().sentence(3))
                .supply(Select.field(Task::getDescription), () -> faker.gameOfThrones().quote())
                .toModel();

        labelModel = Instancio.of(Label.class)
                .ignore(Select.field(Label::getId))
                .ignore(Select.field(Label::getCreatedAt))
                .toModel();

        taskStatusModel = Instancio.of(TaskStatus.class)
                .ignore((Select.field(TaskStatus::getId)))
                .ignore(Select.field(TaskStatus::getCreatedAt))
                .toModel();
    }
}