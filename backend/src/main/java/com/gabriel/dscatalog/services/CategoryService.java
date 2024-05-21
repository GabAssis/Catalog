package com.gabriel.dscatalog.services;

import com.gabriel.dscatalog.dto.CategoryDTO;
import com.gabriel.dscatalog.models.Category;
import com.gabriel.dscatalog.repositories.CategoryRepository;
import com.gabriel.dscatalog.services.exceptions.EntityNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll(){
        List<Category> list = repository.findAll();
        return list.stream().map(x -> new CategoryDTO(x)).toList();


    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        Optional<Category> obj = repository.findById(id);
        Category entity = obj.orElseThrow(() -> new EntityNotFound("Entity not found"));
        return new CategoryDTO(entity);
    }

    @Transactional
    public CategoryDTO insert(CategoryDTO dto) {
        Category entity = new Category();
        entity.setName(dto.getName());
        entity = repository.save(entity);
        return new CategoryDTO(entity);
    }
    @Transactional
    public CategoryDTO update(Long id,CategoryDTO dto) {
            Optional<Category> obj = repository.findById(id);
            Category entity = obj.orElseThrow(() -> new EntityNotFound("Entity not found"));
            entity.setName(dto.getName());
            entity = repository.save(entity);
            return new CategoryDTO(entity);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EntityNotFound e) {
            throw new EntityNotFound("Entity not found");
        }
    }
}



