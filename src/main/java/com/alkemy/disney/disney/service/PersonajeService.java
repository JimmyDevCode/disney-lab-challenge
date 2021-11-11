package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.PersonajeBasicDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

public interface PersonajeService {

    List<PersonajeBasicDTO> getAll();

    PersonajeDTO save(PersonajeDTO personajeDTO);

    PersonajeDTO update(Long id, PersonajeDTO personajeDTO );

    void delete(Long id);

    PersonajeDTO getDetailsById(Long id);

    PersonajeEntity getEntityById(Long id);

    List<PersonajeDTO> getByFilters(String name, String age, Set<Long> movies);
}
