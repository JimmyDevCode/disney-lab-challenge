package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.GeneroDTO;
import com.alkemy.disney.disney.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("generos")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @GetMapping
    public ResponseEntity<List<GeneroDTO>> getAll(){
        List<GeneroDTO> generos = generoService.getAllGeneros();
        return ResponseEntity.status(HttpStatus.OK).body(generos);
    }

    @PostMapping
    public ResponseEntity<GeneroDTO> save(@RequestBody GeneroDTO continente) {
        //save continente
        GeneroDTO generoSave = generoService.save(continente);
        return ResponseEntity.status(HttpStatus.CREATED).body(generoSave);
    }
}
