package com.joao.crudspring.dto.mapper;

import org.springframework.stereotype.Component;

import com.joao.crudspring.dto.CourseDTO;
import com.joao.crudspring.enums.Category;
import com.joao.crudspring.model.Course;

@Component
public class CourserMapper {

    public CourseDTO toDTO(Course course) {
        if (course == null) {
            return null;
        }
        return new CourseDTO(
                course.getId(),
                course.getName(),
                course.getCategory().getValue(),
                course.getLessons());
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
