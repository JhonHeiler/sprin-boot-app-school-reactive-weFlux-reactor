package com.bolsadeideas.springboot.webflux.app.models.services;

import com.bolsadeideas.springboot.webflux.app.models.dao.StudentDao;
import com.bolsadeideas.springboot.webflux.app.models.documents.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao dao;

    @Override
    public Flux<Student> findAll() {
        return dao.findAll();
    }
    @Override
    public Mono<Student> save(Student student) {
        return dao.save(student);
    }

    @Override
    public Mono<Student> findById(String id) {
        return dao.findById(id);
    }

    @Override
    public Mono<Void> delete(Student student) {
        return dao.delete(student);
    }
}
