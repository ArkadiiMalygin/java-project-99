package hexlet.code.service;

import hexlet.code.dto.LabelCreateDTO;
import hexlet.code.dto.LabelDTO;
import hexlet.code.dto.LabelUpdateDTO;

import hexlet.code.exception.ResourceNotFoundException;
import hexlet.code.mapper.LabelMapper;

import hexlet.code.repository.LabelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelService {

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private LabelMapper labelMapper;

    public List<LabelDTO> getAll() {
        var labels = labelRepository.findAll();
        var res = labels.stream()
                .map(labelMapper::map)
                .toList();
        return res;
    }

    public LabelDTO create(LabelCreateDTO labelData) {
        var label = labelMapper.map(labelData);
        labelRepository.save(label);
        var labelDTO = labelMapper.map(label);
        return labelDTO;
    }

    public LabelDTO findById(Long id) {
        var label = labelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found: " + id));
        var taskStatusDTO = labelMapper.map(label);
        return taskStatusDTO;
    }

    public LabelDTO update(LabelUpdateDTO labelData, Long id) {
        var label = labelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found: " + id));

        labelMapper.update(labelData, label);
        labelRepository.save(label);
        var labelDTO = labelMapper.map(label);
        return labelDTO;
    }

    public void delete(Long id) {
        var label = labelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found: " + id));
//        if (label.getTasks().isEmpty()) {
            labelRepository.deleteById(id);
//        } else {
//            throw new EntityIsConnectedToOthers("label is used for these tasks: " + label.getTasks());
//        }
    }
}
