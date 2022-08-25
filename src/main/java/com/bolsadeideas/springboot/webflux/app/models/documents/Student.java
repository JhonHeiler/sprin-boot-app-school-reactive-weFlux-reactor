package com.bolsadeideas.springboot.webflux.app.models.documents;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Document(collection = "estudiantes")
@Data
public class Student {
    @Id
    @NotEmpty
    private String id;

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String email;

    @NotNull
    private boolean status;

    public Student(String nombre, String email, boolean status) {
        this.nombre = nombre;
        this.email = email;
        this.status = status;
    }
}
