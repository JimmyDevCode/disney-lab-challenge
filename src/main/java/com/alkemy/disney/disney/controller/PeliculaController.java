package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.PeliculaBasicDTO;
import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
public class PeliculaController {

    @Autowired
    PeliculaService peliculaService;

    @GetMapping
    public ResponseEntity<List<PeliculaBasicDTO>> getAll(){

        List<PeliculaBasicDTO> paises = peliculaService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(paises);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeliculaDTO> getDetailsById(@PathVariable Long id){
        PeliculaDTO pelicula =  peliculaService.getDetailsById(id);
        return ResponseEntity.status(HttpStatus.OK).body(pelicula);
    }

    @GetMapping("/filters")
    public ResponseEntity<List<PeliculaDTO>> getDetailsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ){
        List<PeliculaDTO> peliculas = this.peliculaService.getByFilters(name, genre, order);
        return ResponseEntity.status(HttpStatus.OK).body(peliculas);
    }

    @PostMapping
    public ResponseEntity <PeliculaDTO> save(@RequestBody PeliculaDTO pelicula){
        PeliculaDTO peliculaSaved = peliculaService.save(pelicula);
        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaSaved);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<PeliculaDTO> update(@PathVariable Long id, @RequestBody PeliculaDTO pelicula){
        PeliculaDTO peliculaUpdate = peliculaService.update(id, pelicula);
        return  ResponseEntity.status(HttpStatus.OK).body(peliculaUpdate);
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delete(@PathVariable Long id){
        this.peliculaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{id}/characters/{idPersonaje}")
    public ResponseEntity<Void> addIcon (@PathVariable Long id, @PathVariable Long idPersonaje){
        this.peliculaService.addPersonaje(id, idPersonaje);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @DeleteMapping("/{id}/characters/{idPersonaje}")
    public ResponseEntity<Void> removeIcon (@PathVariable Long id, @PathVariable Long idPersonaje){
        this.peliculaService.removePersonaje(id, idPersonaje);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
