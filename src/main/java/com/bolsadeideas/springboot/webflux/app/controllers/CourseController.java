package com.bolsadeideas.springboot.webflux.app.controllers;
import com.bolsadeideas.springboot.webflux.app.models.documents.Course;
import com.bolsadeideas.springboot.webflux.app.models.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/api/asignaturas")
public class CourseController {
    @Autowired
    private CourseService service;

    @GetMapping
    public Mono<ResponseEntity<Flux<Course>>> lista(){
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body( service.findAll()));
    }

    @PostMapping
    public Mono<ResponseEntity<Course>> create(@RequestBody Course course){
        return service.save(course).map(c-> ResponseEntity
                .created(URI.create("/api/asignaturas".concat(c.getId())))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(c)
        );

    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Course>> update(@RequestBody Course course, @PathVariable String id){
        return service.findById(id).flatMap(c -> {
                    c.setNombre(course.getNombre());
                    c.setStudent(course.getStudent());
                    return service.save(c);
                }).map(c->ResponseEntity.created(URI.create("/api/asignaturas".concat(c.getId())))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(c))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id){
        return service.findById(id).flatMap(c -> service.delete(c)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
