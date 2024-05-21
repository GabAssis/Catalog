package com.gabriel.dscatalog.controllers;

import com.gabriel.dscatalog.dto.CategoryDTO;
import com.gabriel.dscatalog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll(){
        List<CategoryDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> findbyId(@PathVariable Long id){
        CategoryDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }
    @PostMapping
    public ResponseEntity<CategoryDTO> insert(@RequestBody CategoryDTO dto){
        dto = service.insert(dto);
        return ResponseEntity.status(201).body(dto);
    }


}
