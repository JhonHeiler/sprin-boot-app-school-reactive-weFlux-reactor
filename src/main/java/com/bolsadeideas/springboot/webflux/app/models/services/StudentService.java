package com.bolsadeideas.springboot.webflux.app.models.services;

import com.bolsadeideas.springboot.webflux.app.models.documents.Course;
import com.bolsadeideas.springboot.webflux.app.models.documents.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService {
    Flux<Student> findAll();
    Mono<Student> save(Student student);
    Mono<Student> findById(String id);
    Mono<Void> delete(Student student);
}
