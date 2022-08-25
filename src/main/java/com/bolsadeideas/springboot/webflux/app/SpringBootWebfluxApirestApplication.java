package com.bolsadeideas.springboot.webflux.app;

import java.util.Date;

import com.bolsadeideas.springboot.webflux.app.models.documents.Course;
import com.bolsadeideas.springboot.webflux.app.models.documents.Student;
import com.bolsadeideas.springboot.webflux.app.models.services.CourseService;
import com.bolsadeideas.springboot.webflux.app.models.services.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;


import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringBootWebfluxApirestApplication implements CommandLineRunner{

	@Autowired
	private CourseService service;
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private ReactiveMongoTemplate mongoTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(SpringBootWebfluxApirestApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebfluxApirestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		mongoTemplate.dropCollection("estudiantes").subscribe();
		mongoTemplate.dropCollection("asignaturas").subscribe();

		Student student1 = new Student("Heiler","heylerty7@gmail.com",true);
		Student student2 = new Student("Jhon","heylerty23@gmail.com",false);
		Student student3 = new Student("Ceci","heylerty7@gmail.com",true);
		Student student4 = new Student("Jeily","heylerty104@gmail.com",false);

		
		Flux.just(student1, student2, student3, student4)
		.flatMap(studentService::save)
		.doOnNext(s ->{
			log.info("Student create: " + s.getNombre() + ", Email: " + s.getEmail() + ", status: " + s.isStatus());
		}).thenMany(
				Flux.just(new Course("Calculo",student1),
								new Course("Programacion",student2),
								new Course("Vectores",student3),
								new Course("Ingles",student4))).flatMap(service::save).filter(a -> a.getStudent().isStatus())
		.subscribe(course -> log.info("Insert: " + course.getId() + " " + course.getNombre() + " " +course.getStudent().getNombre()));
		
	}

}
