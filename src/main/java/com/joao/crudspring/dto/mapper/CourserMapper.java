package com.joao.crudspring.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.joao.crudspring.dto.CourseDTO;
import com.joao.crudspring.dto.LessonDTO;
import com.joao.crudspring.enums.Category;
import com.joao.crudspring.model.Course;

@Component
public class CourserMapper {

    public CourseDTO toDTO(Course course) {
        if (course == null) {
            return null;
        }

        List<LessonDTO> lessons = course.getLessons()
                .stream()
                .map(lesson -> new LessonDTO(lesson.getId(), lesson.getName(), lesson.getYoutubeURL()))
                .collect(Collectors.toList());

        return new CourseDTO(
                course.getId(),
                course.getName(),
                course.getCategory().getValue(),
                lessons);
    }

    public Course toEntity(CourseDTO courseDTO) {
        if (courseDTO == null) {
            return null;
        }

        Course course = new Course();

        if (courseDTO.id() != null) {
            course.setId(courseDTO.id());
        }

        course.setName(courseDTO.name());
        course.setCategory(converterCategoryValue(courseDTO.category()));

        return course;
    }

    public Category converterCategoryValue(String value) {
        if (value == null) {
            return null;
        }

        return switch (value) {
            case "Front-end" -> Category.FRONT_END;
            case "Back-end" -> Category.BACK_END;
            case "Mobile" -> Category.MOBILE;
            default -> throw new IllegalArgumentException("Categoria invalida: " + value);
        };
    }
}
