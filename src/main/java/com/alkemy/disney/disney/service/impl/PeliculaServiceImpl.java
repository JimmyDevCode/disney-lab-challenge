package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.PeliculaBasicDTO;
import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.dto.PeliculaFilterDTO;
import com.alkemy.disney.disney.dto.PersonajeBasicDTO;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.mapper.PeliculaMapper;
import com.alkemy.disney.disney.repository.PeliculaRepository;
import com.alkemy.disney.disney.repository.specification.PeliculaSpecification;
import com.alkemy.disney.disney.service.PeliculaService;
import com.alkemy.disney.disney.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaServiceImpl implements PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;
    @Autowired
    private PeliculaMapper peliculaMapper;
    @Autowired
    private PeliculaSpecification peliculaSpecification;
    @Autowired
    private PersonajeService personajeService;

    public List<PeliculaBasicDTO> getAll() {
        List<PeliculaEntity> entities = this.peliculaRepository.findAll();
        List<PeliculaBasicDTO> peliculaBasicDTOS = this.peliculaMapper.peliculaEntitySetBasic2DTOList(entities);
        return peliculaBasicDTOS;
    }

    public PeliculaDTO save(PeliculaDTO dto) {
        PeliculaEntity entity = peliculaMapper.peliculaDTO2Entity(dto);
        PeliculaEntity entitySaved = peliculaRepository.save(entity);
        PeliculaDTO result = peliculaMapper.peliculaEntity2DTO(entitySaved, true);
        return  result;
    }

    public PeliculaDTO update(Long id, PeliculaDTO peliculaDTO) {
        Optional<PeliculaEntity> entity = this.peliculaRepository.findById(id);
        if(!entity.isPresent()){
            throw new ParamNotFound("Id película no valido");
        }
        this.peliculaMapper.peliculaEntityRefreshValues(entity.get(), peliculaDTO);
        PeliculaEntity entitySaved = this.peliculaRepository.save(entity.get());
        PeliculaDTO result = this.peliculaMapper.peliculaEntity2DTO(entitySaved, false);

        return result;
    }


    public void delete(Long id) {
        this.peliculaRepository.deleteById(id);
    }

    public PeliculaDTO getDetailsById(Long id) {
        Optional<PeliculaEntity> entity = this.peliculaRepository.findById(id);
        if(!entity.isPresent()){
            throw new ParamNotFound("Id pelicula no válido");
        }
        PeliculaDTO peliculaDTO = this.peliculaMapper.peliculaEntity2DTO(entity.get(), true);
        return peliculaDTO;
    }


    public List<PeliculaDTO> getByFilters(String name, String genre, String order) {
        PeliculaFilterDTO filtersDto = new PeliculaFilterDTO(name, genre, order);
        List<PeliculaEntity> entities = this.peliculaRepository.findAll(this.peliculaSpecification.getByFilters(filtersDto));
        List<PeliculaDTO> dtos = this.peliculaMapper.peliculaEntityList2DTOList(entities, true);
        return dtos;
    }


    public void addPersonaje(Long id, Long idPersonaje) {
        PeliculaEntity entity = this.peliculaRepository.getById(id);
        entity.getPersonajes().size();
        PersonajeEntity personajeEntity = this.personajeService.getEntityById(idPersonaje);
        entity.addPersonaje(personajeEntity);
        this.peliculaRepository.save(entity);
    }

    public void removePersonaje(Long id, Long idPersonaje) {
        PeliculaEntity entity = this.peliculaRepository.getById(id);
        entity.getPersonajes().size();
        PersonajeEntity personajeEntity = this.personajeService.getEntityById(idPersonaje);
        entity.removePersonaje(personajeEntity);
        this.peliculaRepository.save(entity);

    }
}
