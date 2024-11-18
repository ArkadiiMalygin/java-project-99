package hexlet.code.app.controller.api;

import hexlet.code.app.dto.TaskStatusCreateDTO;
import hexlet.code.app.dto.TaskStatusDTO;
import hexlet.code.app.dto.TaskStatusUpdateDTO;
import hexlet.code.app.mapper.TaskStatusMapper;
import hexlet.code.app.repository.TaskStatusRepository;
import hexlet.code.app.service.TaskStatusService;
import hexlet.code.app.util.UserUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskStatusesController {

    @Autowired
    private TaskStatusRepository taskStatusRepository;

    @Autowired
    private TaskStatusMapper taskStatusMapper;

    @Autowired
    private TaskStatusService taskStatusService;

    @Autowired
    private UserUtils userUtils;

    @GetMapping("/task_statuses")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<TaskStatusDTO>> index() {
        var taskStatuses = taskStatusService.getAll();

        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(taskStatuses.size()))
                .body(taskStatuses);
    }

    @PostMapping("/task_statuses")
    @ResponseStatus(HttpStatus.CREATED)
    TaskStatusDTO create(@Valid @RequestBody TaskStatusCreateDTO taskStatusData) {
        var taskStatusDto = taskStatusService.create(taskStatusData);
        return taskStatusDto;
    }

    @GetMapping("/task_statuses/{id}")
    @ResponseStatus(HttpStatus.OK)
    TaskStatusDTO show(@PathVariable Long id) {
        var taskStatusDto = taskStatusService.findById(id);
        return taskStatusDto;
    }

    @PutMapping("/task_statuses/{id}")
    @ResponseStatus(HttpStatus.OK)
    TaskStatusDTO update(@RequestBody @Valid TaskStatusUpdateDTO taskStatusUpdateDTO, @PathVariable Long id) {
        var taskStatusDto = taskStatusService.update(taskStatusUpdateDTO, id);
        return taskStatusDto;
    }

    @DeleteMapping("/task_statuses/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void destroy(@PathVariable Long id) {
        taskStatusService.delete(id);
    }


}
