package com.alkemy.disney.disney.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.constraints.Size;
@Getter
@Setter
@Entity
@Table(name = "pelicula")
public class PeliculaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imagen;

    private String titulo;

    @Column(name = "fecha_creacion")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaCreacion;

    @Size(min = 1, max = 5)
    private Integer calificacion;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "genero_id", insertable = false, updatable = false)
    private GeneroEntity generoEntity;

    @Column(name = "genero_id", nullable = false)
    private Long generoId;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE

            })
    @JoinTable(
            name = "personaje_pelicula",
            joinColumns = @JoinColumn(name = "pelicula_id"),
            inverseJoinColumns = @JoinColumn(name = "personaje_id"))
    private Set<PersonajeEntity> personajes = new HashSet<>();

    private boolean deleted = Boolean.FALSE;

    public void addPersonaje(PersonajeEntity personaje){
        this.personajes.add(personaje);
    }
    public void removePersonaje(PersonajeEntity personaje){
        this.personajes.remove(personaje);
    }
}
