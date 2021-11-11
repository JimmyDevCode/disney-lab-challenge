package com.alkemy.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PeliculaDTO {

    private Long id;
    private String imagen;
    private String titulo;
    private String fechaCreacion;
    private Integer calificacion;
    private Long generoId;
    private List<PersonajeDTO> personajes;
}
