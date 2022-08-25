package com.bolsadeideas.springboot.webflux.app.models.dao;

import com.bolsadeideas.springboot.webflux.app.models.documents.Student;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface StudentDao extends ReactiveMongoRepository<Student, String> {
}
