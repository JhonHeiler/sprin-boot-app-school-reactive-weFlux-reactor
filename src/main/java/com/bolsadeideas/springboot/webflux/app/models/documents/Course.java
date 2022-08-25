package com.bolsadeideas.springboot.webflux.app.models.documents;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
@Document(collection = "asignaturas")
@Data
public class Course {
    @Id
    @NotEmpty
    private String id;

    @NotEmpty
    private String nombre;

    @Valid
    private Student student;

    public Course(String nombre, Student student) {
        this.nombre = nombre;
        this.student = student;
    }
}
