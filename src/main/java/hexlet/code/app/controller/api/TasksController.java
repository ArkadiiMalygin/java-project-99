package hexlet.code.app.controller.api;

import hexlet.code.app.dto.TaskCreateDTO;
import hexlet.code.app.dto.TaskDTO;
import hexlet.code.app.dto.TaskParamsDTO;

import hexlet.code.app.dto.TaskUpdateDTO;
import hexlet.code.app.mapper.TaskMapper;

import hexlet.code.app.repository.TaskRepository;

import hexlet.code.app.service.TaskService;

import hexlet.code.app.specification.TaskSpecification;
import hexlet.code.app.util.UserUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
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
@Tag(name = "Tasks", description = "Methods that can be used for tasks")
public class TasksController {

    @Autowired
    private TaskSpecification specBuilder;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserUtils userUtils;

    @GetMapping("/tasks")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "All tasksInfo")
    ResponseEntity<List<TaskDTO>> index(TaskParamsDTO taskParams) {

        List<TaskDTO> tasks = taskService.findAll(taskParams);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(tasks.size()));

        return new ResponseEntity<>(tasks, headers, HttpStatus.OK);
    }

    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create task requires data")
    TaskDTO create(@Valid @RequestBody TaskCreateDTO taskData) {
        var taskDto = taskService.create(taskData);
        return taskDto;
    }

    @GetMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "TasksInfo by itsID")
    TaskDTO show(@PathVariable Long id) {
        var taskDto = taskService.findById(id);
        return taskDto;
    }

    @PutMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "update TasksInfo by itsID requires some data to update")
    TaskDTO update(@RequestBody @Valid TaskUpdateDTO taskUpdateDTO, @PathVariable Long id) {
        var taskDto = taskService.update(taskUpdateDTO, id);
        return taskDto;
    }

    @DeleteMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete task by itsID")
    void destroy(@PathVariable Long id) {
        taskService.delete(id);
    }


}
