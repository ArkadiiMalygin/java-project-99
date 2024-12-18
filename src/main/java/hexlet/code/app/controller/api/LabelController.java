package hexlet.code.app.controller.api;

import hexlet.code.app.dto.LabelCreateDTO;
import hexlet.code.app.dto.LabelDTO;
import hexlet.code.app.dto.LabelUpdateDTO;

import hexlet.code.app.mapper.LabelMapper;

import hexlet.code.app.repository.LabelRepository;

import hexlet.code.app.service.LabelService;

import hexlet.code.app.util.UserUtils;
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
@Tag(name = "Labels", description = "Methods that can be used for labels")
public class LabelController {

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private LabelMapper labelMapper;

    @Autowired
    private LabelService labelService;

    @Autowired
    private UserUtils userUtils;

    @GetMapping("/labels")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "All labelsInfo")
    ResponseEntity<List<LabelDTO>> index() {
        var labels = labelService.getAll();

        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(labels.size()))
                .body(labels);
    }

    @PostMapping("/labels")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create label requires data")
    LabelDTO create(@Valid @RequestBody LabelCreateDTO labelData) {
        var labelDto = labelService.create(labelData);
        return labelDto;
    }

    @GetMapping("/labels/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "LabelsInfo by itsID")
    LabelDTO show(@PathVariable Long id) {
        var labelDto = labelService.findById(id);
        return labelDto;
    }

    @PutMapping("/labels/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "update LabelsInfo by itsID requires some data to update")
    LabelDTO update(@RequestBody @Valid LabelUpdateDTO labelUpdateDTO, @PathVariable Long id) {
        var labelDto = labelService.update(labelUpdateDTO, id);
        return labelDto;
    }

    @DeleteMapping("/labels/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete label by itsID")
    void destroy(@PathVariable Long id) {
        labelService.delete(id);
    }


}
