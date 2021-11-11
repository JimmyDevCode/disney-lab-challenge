package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.PersonajeBasicDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("characters")
public class PersonajeController {

    @Autowired
    private PersonajeService personajeService;

    @GetMapping
    public ResponseEntity<List<PersonajeBasicDTO>> getAll() {

        List<PersonajeBasicDTO> personajes = personajeService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(personajes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonajeDTO> getDetailsById(@PathVariable Long id) {
        PersonajeDTO personaje = personajeService.getDetailsById(id);
        return ResponseEntity.status(HttpStatus.OK).body(personaje);
    }

    @PostMapping
    public ResponseEntity<PersonajeDTO> save(@RequestBody PersonajeDTO personaje) {
        PersonajeDTO personajeSaved = personajeService.save(personaje);
        return ResponseEntity.status(HttpStatus.CREATED).body(personajeSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonajeDTO> update(@PathVariable Long id, @RequestBody PersonajeDTO personaje) {
        PersonajeDTO personajeUpdate = personajeService.update(id, personaje);
        return ResponseEntity.status(HttpStatus.OK).body(personajeUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.personajeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
