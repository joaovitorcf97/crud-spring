package com.joao.crudspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.joao.crudspring.enums.Category;
import com.joao.crudspring.model.Course;
import com.joao.crudspring.model.Lesson;
import com.joao.crudspring.repository.CourseRepository;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CourseRepository courseRepository) {
		return args -> {
			courseRepository.deleteAll();

			Course course = new Course();
			course.setName("Angular");
			course.setCategory(Category.FRONT_END);

			Lesson lesson = new Lesson();
			lesson.setName("Introdução");
			lesson.setYoutubeURL("Nb4uxLxdvxo");
			lesson.setCourse(course);
			course.getLessons().add(lesson);

			Lesson lesson2 = new Lesson();
			lesson2.setName("Aula 02");
			lesson2.setYoutubeURL("Nb4uxLxdvxo");
			lesson2.setCourse(course);
			course.getLessons().add(lesson2);

			courseRepository.save(course);
		};
	}
}
