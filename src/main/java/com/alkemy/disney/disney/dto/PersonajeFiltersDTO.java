package com.alkemy.disney.disney.dto;


import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class PersonajeFiltersDTO {

        private String name;
        private String age;
        private Set<Long> movies;

    public PersonajeFiltersDTO(String name, String age, Set<Long> movies) {
        this.name = name;
        this.age = age;
        this.movies = movies;
    }
}
