package hexlet.code.app.mapper;

import hexlet.code.app.dto.UserUpdateDTO;
import hexlet.code.app.model.Task;
import hexlet.code.app.model.User;
import hexlet.code.app.repository.TaskRepository;
import hexlet.code.app.dto.UserCreateDTO;
import hexlet.code.app.dto.UserDTO;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Mapper(
        uses = {JsonNullableMapper.class, ReferenceMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class UserMapper {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Mapping(target = "passwordDigest", source = "password")
    public abstract User map(UserCreateDTO model);

    //TODO
    //@Mapping(target = "username", source = "email")
    //@Mapping(target = "password", ignore = true)
    //TODO
    //@Mapping(target = "tasksTitle", qualifiedByName = "getTitles", source = "tasks")
    //@Mapping(target = "tasksTitle", qualifiedByName = "getTitles", source = "tasks")
    public abstract UserDTO map(User model);

    //TODO
    //@Mapping(target = "email", source = "username")
    public abstract User map(UserDTO model);

    @Mapping(target = "passwordDigest", source = "password")
    public abstract void update(UserUpdateDTO update, @MappingTarget User destination);

    @BeforeMapping
    public void encryptPassword(UserCreateDTO data) {
        var password = data.getPassword();
        data.setPassword(encoder.encode(password));
    }

    @Named("getTitles")
    List<String> getTitles(List<Task> tasks) {
        var tasksNames = new ArrayList<String>();
        for (Task t : tasks) {
            tasksNames.add(t.getName());
        }
        return tasksNames;
    }
}
