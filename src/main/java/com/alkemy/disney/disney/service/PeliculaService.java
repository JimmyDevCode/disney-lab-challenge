package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.PeliculaBasicDTO;
import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import com.alkemy.disney.disney.entity.PersonajeEntity;

import java.util.List;

public interface PeliculaService {

    List<PeliculaBasicDTO> getAll();

    PeliculaDTO save(PeliculaDTO dto);

    PeliculaDTO update(Long id, PeliculaDTO dto );

    void delete(Long id);

    PeliculaDTO getDetailsById(Long id);

    List<PeliculaDTO> getByFilters(String name, String genero, String order );

    void addPersonaje(Long id, Long idPersonaje);

    void removePersonaje(Long id, Long idPersonaje);
}
