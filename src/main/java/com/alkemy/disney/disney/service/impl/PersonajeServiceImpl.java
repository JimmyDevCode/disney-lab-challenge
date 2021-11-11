package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.PersonajeBasicDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.mapper.PersonajeMapper;
import com.alkemy.disney.disney.repository.PersonajeRepository;
import com.alkemy.disney.disney.repository.specification.PersonajeSpecification;
import com.alkemy.disney.disney.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonajeServiceImpl implements PersonajeService {

    @Autowired
    PersonajeMapper personajeMapper;
    @Autowired
    private PersonajeRepository personajeRepository;
    @Autowired
    PersonajeSpecification personajeSpecification;


    public List<PersonajeBasicDTO> getAll() {

        List<PersonajeEntity> entities = this.personajeRepository.findAll();
        List<PersonajeBasicDTO> personajeBasicDTOS = this.personajeMapper.personajeEntitySetBasic2DTOList(entities);

        return personajeBasicDTOS;
    }

    public PersonajeDTO save(PersonajeDTO personajeDTO) {

        PersonajeEntity entity = personajeMapper.personajeDTO2Entity(personajeDTO);
        PersonajeEntity entitySaved = personajeRepository.save(entity);
        PersonajeDTO result = personajeMapper.personajeEntity2DTO(entitySaved, true);
        return result;
    }

    public PersonajeDTO update(Long id, PersonajeDTO personajeDTO) {
        Optional<PersonajeEntity> entity = this.personajeRepository.findById(id);
        if (!entity.isPresent()) {
            throw new ParamNotFound("Id personaje no válido");
        }
        this.personajeMapper.personajeEntityRefreshValues(entity.get(), personajeDTO);
        PersonajeEntity entitySaved = this.personajeRepository.save(entity.get());
        PersonajeDTO result = this.personajeMapper.personajeEntity2DTO(entitySaved, false);

        return result;
    }

    public void delete(Long id) {

        this.personajeRepository.deleteById(id);

    }

    public PersonajeDTO getDetailsById(Long id) {
        Optional<PersonajeEntity> entity = this.personajeRepository.findById(id);
        if (!entity.isPresent()) {
            throw new ParamNotFound("Id personaje no valido");
        }
        PersonajeDTO personajeDTO = this.personajeMapper.personajeEntity2DTO(entity.get(), true);
        return personajeDTO;
    }

    public PersonajeEntity getEntityById(Long id) {
        return this.personajeRepository.getById(id);
    }

    public List<PersonajeDTO> getByFilters(String name, String age, String movies) {
        return null;
    }
}
