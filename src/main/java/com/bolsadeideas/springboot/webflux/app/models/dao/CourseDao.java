package com.bolsadeideas.springboot.webflux.app.models.dao;

import com.bolsadeideas.springboot.webflux.app.models.documents.Course;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


public interface CourseDao extends ReactiveMongoRepository<Course, String> {
}
