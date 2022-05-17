package com.javaspring.curso.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @Getter @Setter
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Getter @Setter
    @Column(name="nombre")
    private String nombre;
    @Getter @Setter
    @Column(name="email")
    private String email;
    @Getter @Setter
    @Column(name="telefono")
    private String telefono;
    @Getter @Setter
    @Column(name="password")
    private String password;


}
