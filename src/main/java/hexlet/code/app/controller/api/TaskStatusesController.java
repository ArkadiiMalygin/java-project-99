package hexlet.code.app.controller.api;

import hexlet.code.app.util.UserUtils;
import hexlet.code.app.dto.TaskStatusCreateDTO;
import hexlet.code.app.dto.TaskStatusDTO;
import hexlet.code.app.dto.TaskStatusUpdateDTO;
import hexlet.code.app.mapper.TaskStatusMapper;
import hexlet.code.app.repository.TaskStatusRepository;
import hexlet.code.app.service.TaskStatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Statuses", description = "Methods that can be used for statuses")
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
    @Operation(summary = "All statusesInfo")
    ResponseEntity<List<TaskStatusDTO>> index() {
        var taskStatuses = taskStatusService.getAll();

        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(taskStatuses.size()))
                .body(taskStatuses);
    }

    @PostMapping("/task_statuses")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create status requires data")
    TaskStatusDTO create(@Valid @RequestBody TaskStatusCreateDTO taskStatusData) {
        var taskStatusDto = taskStatusService.create(taskStatusData);
        return taskStatusDto;
    }

    @GetMapping("/task_statuses/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "StatusInfo by itsID")
    TaskStatusDTO show(@PathVariable Long id) {
        var taskStatusDto = taskStatusService.findById(id);
        return taskStatusDto;
    }

    @PutMapping("/task_statuses/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "update StatusInfo by itsID requires some data to update")
    TaskStatusDTO update(@RequestBody @Valid TaskStatusUpdateDTO taskStatusUpdateDTO, @PathVariable Long id) {
        var taskStatusDto = taskStatusService.update(taskStatusUpdateDTO, id);
        return taskStatusDto;
    }

    @DeleteMapping("/task_statuses/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete status by itsID")
    void destroy(@PathVariable Long id) {
        taskStatusService.delete(id);
    }


}
