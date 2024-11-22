package hexlet.code.service;

import hexlet.code.dto.TaskCreateDTO;
import hexlet.code.dto.TaskDTO;

import hexlet.code.dto.TaskParamsDTO;
import hexlet.code.dto.TaskUpdateDTO;
import hexlet.code.exception.ResourceNotFoundException;
import hexlet.code.mapper.TaskMapper;

import hexlet.code.model.Task;
import hexlet.code.repository.TaskRepository;
import hexlet.code.repository.TaskStatusRepository;
import hexlet.code.repository.UserRepository;
import hexlet.code.specification.TaskSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskStatusRepository taskStatusRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskSpecification taskSpecification;


    public List<TaskDTO> findAll(TaskParamsDTO taskParams) {
        Specification<Task> specification = taskSpecification.build(taskParams);
        return taskRepository.findAll(specification).stream().map(taskMapper::map).toList();
    }

    public List<TaskDTO> getAll() {
        var tasks = taskRepository.findAll();
        var res = tasks.stream()
                .map(taskMapper::map)
                .toList();
        return res;
    }

    public TaskDTO create(TaskCreateDTO taskData) {
        var task = taskMapper.map(taskData);
        taskRepository.save(task);

        var taskDTO = taskMapper.map(task);
        return taskDTO;
    }

    public TaskDTO findById(Long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found: " + id));
        var taskDTO = taskMapper.map(task);
        return taskDTO;
    }

    public TaskDTO update(TaskUpdateDTO taskData, Long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found: " + id));

        taskMapper.update(taskData, task);
        taskRepository.save(task);
        var taskDTO = taskMapper.map(task);
        return taskDTO;
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
