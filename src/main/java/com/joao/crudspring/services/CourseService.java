package com.joao.crudspring.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import com.joao.crudspring.dto.CourseDTO;
import com.joao.crudspring.dto.mapper.CourserMapper;
import com.joao.crudspring.exception.RecordNotFoundExcenption;
import com.joao.crudspring.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourserMapper courserMapper;

    public CourseService(CourseRepository courseRepository, CourserMapper courserMapper) {
        this.courserMapper = courserMapper;
        this.courseRepository = courseRepository;
    }

    public List<CourseDTO> list() {
        return courseRepository.findAll()
                .stream()
                .map(courserMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CourseDTO findById(@NotNull @Positive Long id) {
        return courseRepository.findById(id)
                .map(courserMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundExcenption(id));
    }

    public CourseDTO create(@Valid @NotNull CourseDTO course) {
        return courserMapper.toDTO(courseRepository.save(courserMapper.toEntity(course)));
    }

    public CourseDTO update(@NotNull @Positive Long id, @Valid @NotNull CourseDTO course) {
        return courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(course.name());
                    recordFound.setCategory(course.category());
                    return courserMapper.toDTO(courseRepository.save(recordFound));
                })
                .orElseThrow(() -> new RecordNotFoundExcenption(id));
    }

    public void delete(@NotNull @Positive Long id) {
        courseRepository.delete(courseRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundExcenption(id)));

    }
}
