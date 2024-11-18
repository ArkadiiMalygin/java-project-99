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
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
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
    Page<TaskDTO> index(@RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "") String titleCont,
                        @RequestParam(defaultValue = "0") Long assigneeId,
                        @RequestParam(defaultValue = "") String status,
                        @RequestParam(defaultValue = "0") Long labelId) {

        var taskParams = new TaskParamsDTO();
        if (!titleCont.equals("")) { taskParams.setTitleCont(titleCont);}
        if (assigneeId != 0) { taskParams.setAssigneeId(assigneeId);}
        if (!titleCont.equals("")) { taskParams.setStatus(status);}
        if (labelId != 0) { taskParams.setLabelId(labelId);}

        var spec = specBuilder.build(taskParams);

        var tasks = taskRepository.findAll(spec, PageRequest.of(page - 1, 10));

        var res = tasks.map(taskMapper::map);

        return res;
    }

    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    TaskDTO create(@Valid @RequestBody TaskCreateDTO taskData) {
        var taskDto = taskService.create(taskData);
        return taskDto;
    }

    @GetMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    TaskDTO show(@PathVariable Long id) {
        var taskDto = taskService.findById(id);
        return taskDto;
    }

    @PutMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    TaskDTO update(@RequestBody @Valid TaskUpdateDTO taskUpdateDTO, @PathVariable Long id) {
        var taskDto = taskService.update(taskUpdateDTO, id);
        return taskDto;
    }

    @DeleteMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void destroy(@PathVariable Long id) {
        taskService.delete(id);
    }


}