package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.PeliculaBasicDTO;
import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.dto.PersonajeBasicDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Component
public class PeliculaMapper {

    @Autowired
    GeneroMapper generoMapper;
    @Autowired
    PersonajeMapper personajeMapper;

    public PeliculaEntity peliculaDTO2Entity(PeliculaDTO dto){
        PeliculaEntity entity = new PeliculaEntity();
        entity.setId(dto.getId());
        entity.setImagen(dto.getImagen());
        entity.setTitulo(dto.getTitulo());
        entity.setFechaCreacion(this.string2LocalDate(dto.getFechaCreacion()));
        entity.setCalificacion(dto.getCalificacion());
        entity.setGeneroId(dto.getGeneroId());
        Set<PersonajeEntity> personajes = this.personajeMapper.personajeDTO2List2Entity(dto.getPersonajes());
        entity.setPersonajes(personajes);

        return entity;
    }

    //convierto las entidades a dtos para ser mostradas
    public PeliculaDTO peliculaEntity2DTO(PeliculaEntity entity, boolean loadPersonajes){
        PeliculaDTO dto = new PeliculaDTO();
        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setTitulo(entity.getTitulo());
        dto.setFechaCreacion(entity.getFechaCreacion().toString());
        dto.setCalificacion(entity.getCalificacion());
        dto.setGeneroId(entity.getGeneroId());
        if(loadPersonajes){
            List<PersonajeDTO> personajeDTOS = this.personajeMapper.personajeEntitySet2DTOList(entity.getPersonajes(), false);
            dto.setPersonajes(personajeDTOS);
        }
        return dto;
    }

    public List<PeliculaDTO> peliculaEntityList2DTOList(List<PeliculaEntity> entities, boolean loadPersonaje){
        List<PeliculaDTO> dtos = new ArrayList<>();
        for(PeliculaEntity entity: entities){
            dtos.add(this.peliculaEntity2DTO(entity, loadPersonaje));
        }
        return dtos;
    }

    public List<PeliculaBasicDTO> peliculaEntitySetBasic2DTOList(Collection<PeliculaEntity> entities) {
        List<PeliculaBasicDTO> dtos = new ArrayList<>();
        PeliculaBasicDTO basicDto;
        for (PeliculaEntity entity : entities) {
            basicDto = new PeliculaBasicDTO();
            basicDto.setId(entity.getId());
            basicDto.setImagen(entity.getImagen());
            basicDto.setTitulo(entity.getTitulo());
            basicDto.setFechaCreacion(entity.getFechaCreacion().toString());
            dtos.add(basicDto);
        }
        return dtos;
    }


    public void peliculaEntityRefreshValues(PeliculaEntity entity, PeliculaDTO dto){
        entity.setImagen(dto.getImagen());
        entity.setFechaCreacion(this.string2LocalDate(dto.getFechaCreacion()));
        entity.setCalificacion(dto.getCalificacion());
        entity.setGeneroId(dto.getGeneroId());
    }

    private LocalDate string2LocalDate(String stringDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(stringDate, formatter);
        return  date;
    }
}
