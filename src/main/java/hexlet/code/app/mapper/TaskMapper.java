package hexlet.code.app.mapper;

import hexlet.code.app.dto.TaskCreateDTO;
import hexlet.code.app.dto.TaskDTO;
import hexlet.code.app.dto.TaskUpdateDTO;
import hexlet.code.app.exception.ResourceNotFoundException;
import hexlet.code.app.model.Label;
import hexlet.code.app.model.Task;
import hexlet.code.app.model.TaskStatus;
import hexlet.code.app.model.User;
import hexlet.code.app.repository.LabelRepository;
import hexlet.code.app.repository.TaskStatusRepository;
import hexlet.code.app.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Mapper(
        uses = {JsonNullableMapper.class, ReferenceMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class TaskMapper {
    @Autowired
    private TaskStatusRepository taskStatusRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LabelRepository labelRepository;

    @Mapping(target = "name", source = "title")
    @Mapping(target = "description", source = "content")
    @Mapping(target = "assignee",qualifiedByName = "getAssigneeFromAssigneeId", source = "assignee_id")
    @Mapping(target = "taskStatus",qualifiedByName = "getTaskStatusFromStatus", source = "status")
    @Mapping(target = "labels",qualifiedByName = "getLabelsFromListOfLabelNames", source = "labels")
    public abstract Task map(TaskCreateDTO model);

    @Mapping(target = "title", source = "name")
    @Mapping(target = "content", source = "description")
    @Mapping(target = "assigneeId", source = "assignee.id")
    @Mapping(target = "status", source = "taskStatus.slug")
    @Mapping(target = "labels",qualifiedByName = "getLabelsNamesFromListOfLabels", source = "labels")
    public abstract TaskDTO map(Task model);


    @Mapping(target = "name", source = "title")
    @Mapping(target = "description", source = "content")
    @Mapping(target = "assignee", source = "assigneeId")
    @Mapping(target = "taskStatus", source = "status")
    @Mapping(target = "labels",qualifiedByName = "getLabelsFromListOfLabelNamesAndAddThem", source = "labels")
    public abstract void update(TaskUpdateDTO update, @MappingTarget Task destination);

    @Named("getTaskStatusFromStatus")
    TaskStatus getTaskStatusFromStatus(String status) {
        return taskStatusRepository.findBySlug(status)
                .orElseThrow(() -> new ResourceNotFoundException("no status with this name yet: " + status));
    }
    @Named("getAssigneeFromAssigneeId")
    User getAssigneeFromAssigneeId(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("no users with this id yet: " + id));
    }

    @Named("getLabelsFromListOfLabelNames")
    List<Label> getLabelsFromListOfLabelNames(List<String> labels) {
        var resLabels = new ArrayList<Label>();
        for (String labelName : labels) {
            var label = labelRepository.findByName(labelName)
                    .orElseThrow(() -> new ResourceNotFoundException("no label with this name yet: " + labelName));
            resLabels.add(label);
        }
        return resLabels;
    }

    @Named("getLabelsNamesFromListOfLabels")
    List<String> getLabelsNamesFromListOfLabels(List<Label> labels) {
        var resLabelNames = new ArrayList<String>();
        for (Label label : labels) {
            resLabelNames.add(label.getName());
        }
        return resLabelNames;
    }

    @Named("getLabelsFromListOfLabelNamesAndAddThem")
    List<Label> getLabelsFromListOfLabelNamesAndAddThem(List<String> labels) {
        var resLabels = new ArrayList<Label>();
        for (String labelName : labels) {
            resLabels.add(labelRepository.findByName(labelName)
                    .orElseThrow(() -> new ResourceNotFoundException("no label with this name yet: " + labelName)));
        }
        return resLabels;
    }
}
