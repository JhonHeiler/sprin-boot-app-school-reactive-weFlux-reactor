package com.bolsadeideas.springboot.webflux.app.models.services;
import com.bolsadeideas.springboot.webflux.app.models.dao.CourseDao;
import com.bolsadeideas.springboot.webflux.app.models.dao.StudentDao;
import com.bolsadeideas.springboot.webflux.app.models.documents.Course;
import com.bolsadeideas.springboot.webflux.app.models.documents.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CourseDao dao;


    @Override
    public Flux<Course> findAll() {
        return dao.findAll();
    }

    @Override
    public Mono<Course> save(Course course) {
        return dao.save(course);
    }
    @Override
    public Mono<Course> findById(String id) {
        return dao.findById(id);
    }

    @Override
    public Mono<Void> delete(Course course) {
        return dao.delete(course);
    }


}
