package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.dto.PersonajeBasicDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PersonajeMapper {

    @Autowired
    PeliculaMapper peliculaMapper;

    public PersonajeEntity personajeDTO2Entity(PersonajeDTO dto) {
        PersonajeEntity entity = new PersonajeEntity();
        entity.setId(dto.getId());
        entity.setImagen(dto.getImagen());
        entity.setNombre(dto.getNombre());
        entity.setEdad(dto.getEdad());
        entity.setPeso(dto.getPeso());
        entity.setHistoria(dto.getHistoria());
        entity.setHistoria(dto.getHistoria());

        return entity;
    }

    public PersonajeDTO personajeEntity2DTO(PersonajeEntity entity, boolean loadPeliculas) {
        PersonajeDTO dto = new PersonajeDTO();
        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setNombre(entity.getNombre());
        dto.setEdad(entity.getEdad());
        dto.setPeso(entity.getPeso());
        dto.setHistoria(entity.getHistoria());
        if (loadPeliculas) {
            List<PeliculaDTO> dtos = peliculaMapper.peliculaEntityList2DTOList(entity.getPeliculas(), false);
            dto.setPeliculas(dtos);
        }
        return dto;
    }

    public Set<PersonajeEntity> personajeDTO2List2Entity(List<PersonajeDTO> dtos) {
        Set<PersonajeEntity> entities = new HashSet<>();
        for (PersonajeDTO dto : dtos) {
            entities.add(this.personajeDTO2Entity(dto));
        }
        return entities;
    }

    public List<PersonajeDTO> personajeEntitySet2DTOList(Collection<PersonajeEntity> entities, boolean loadPeliculas) {
        List<PersonajeDTO> dtos = new ArrayList<>();
        for (PersonajeEntity entity : entities) {
            dtos.add(this.personajeEntity2DTO(entity, loadPeliculas));
        }
        return dtos;
    }

    public List<PersonajeBasicDTO> personajeEntitySetBasic2DTOList(Collection<PersonajeEntity> entities) {
        List<PersonajeBasicDTO> dtos = new ArrayList<>();
        PersonajeBasicDTO basicDto;
        for (PersonajeEntity entity : entities) {
            basicDto = new PersonajeBasicDTO();
            basicDto.setId(entity.getId());
            basicDto.setImagen(entity.getImagen());
            basicDto.setNombre(entity.getNombre());
            dtos.add(basicDto);
        }
        return dtos;
    }

    public void personajeEntityRefreshValues(PersonajeEntity entity, PersonajeDTO dto) {
        entity.setId(dto.getId());
        entity.setImagen(dto.getImagen());
        entity.setNombre(dto.getNombre());
        entity.setEdad(dto.getEdad());
        entity.setPeso(dto.getPeso());
        entity.setHistoria(dto.getHistoria());
    }

}
