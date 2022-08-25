package com.bolsadeideas.springboot.webflux.app.models.services;
import com.bolsadeideas.springboot.webflux.app.models.documents.Course;
import com.bolsadeideas.springboot.webflux.app.models.documents.Student;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CourseService {
    Flux<Course> findAll();
    Mono<Course> save(Course course);
    Mono<Course> findById(String id);
    Mono<Void> delete(Course course);

}
