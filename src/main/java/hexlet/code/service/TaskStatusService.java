package hexlet.code.service;

import hexlet.code.dto.TaskStatusCreateDTO;
import hexlet.code.dto.TaskStatusDTO;
import hexlet.code.dto.TaskStatusUpdateDTO;
import hexlet.code.exception.EntityIsConnectedToOthers;
import hexlet.code.exception.ResourceNotFoundException;
import hexlet.code.mapper.TaskStatusMapper;
import hexlet.code.repository.TaskStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskStatusService {

    @Autowired
    private TaskStatusRepository taskStatusRepository;

    @Autowired
    private TaskStatusMapper taskStatusMapper;

    public List<TaskStatusDTO> getAll() {
        var taskStatuses = taskStatusRepository.findAll();
        var res = taskStatuses.stream()
                .map(taskStatusMapper::map)
                .toList();
        return res;
    }

    public TaskStatusDTO create(TaskStatusCreateDTO taskStatusData) {
        var taskStatus = taskStatusMapper.map(taskStatusData);
        taskStatusRepository.save(taskStatus);
        var taskStatusDTO = taskStatusMapper.map(taskStatus);
        return taskStatusDTO;
    }

    public TaskStatusDTO findById(Long id) {
        var taskStatus = taskStatusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found: " + id));
        var taskStatusDTO = taskStatusMapper.map(taskStatus);
        return taskStatusDTO;
    }

    public TaskStatusDTO update(TaskStatusUpdateDTO task, Long id) {
        var taskStatus = taskStatusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found: " + id));

        taskStatusMapper.update(task, taskStatus);
        taskStatusRepository.save(taskStatus);
        var taskStatusDTO = taskStatusMapper.map(taskStatus);
        return taskStatusDTO;
    }

    public void delete(Long id) {
        var taskStatus = taskStatusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found: " + id));
        if (taskStatus.getTasks().isEmpty()) {
            taskStatusRepository.deleteById(id);
        } else {
            throw new EntityIsConnectedToOthers("Status is used for these tasks: " + taskStatus.getTasks());
        }
    }
}
