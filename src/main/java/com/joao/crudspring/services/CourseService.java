package com.joao.crudspring.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.joao.crudspring.exception.RecordNotFoundExcenption;
import com.joao.crudspring.model.Course;
import com.joao.crudspring.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> list() {
        return courseRepository.findAll();
    }

    public Course findById(@NotNull @Positive Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundExcenption(id));
    }

    public Course create(@Valid Course course) {
        return courseRepository.save(course);
    }

    public Course update(@NotNull @Positive Long id, @Valid Course course) {
        return courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundExcenption(id));
    }

    public void delete(@NotNull @Positive Long id) {
        courseRepository.delete(courseRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundExcenption(id)));

    }
}
