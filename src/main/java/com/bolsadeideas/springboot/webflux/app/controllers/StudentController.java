package com.bolsadeideas.springboot.webflux.app.controllers;
import com.bolsadeideas.springboot.webflux.app.models.documents.Student;
import com.bolsadeideas.springboot.webflux.app.models.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/api/estudiantes")
public class StudentController {
    @Autowired
    private StudentService service;

    @GetMapping
    public Mono<ResponseEntity<Flux<Student>>> lista(){
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(service.findAll())
        );
    }


    @PostMapping
    public Mono<ResponseEntity<Student>> create(@RequestBody Student student){
        return service.save(student).map(s-> ResponseEntity
                .created(URI.create("/api/estudiantes".concat(s.getId())))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(s)
        );
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Student>> update(@RequestBody Student student, @PathVariable String id){
        return service.findById(id).flatMap(s -> {
                    s.setNombre(student.getNombre());
                    s.setEmail(student.getEmail());
                    s.setStatus(student.isStatus());
                    return service.save(s);
                }).map(s->ResponseEntity.created(URI.create("/api/productos/".concat(s.getId())))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(s))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id){
        return service.findById(id).filter(s->!s.isStatus()).flatMap(s -> service.delete(s)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
