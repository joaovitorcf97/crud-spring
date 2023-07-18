package com.joao.crudspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joao.crudspring.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
