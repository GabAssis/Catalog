package com.gabriel.dscatalog.services;

import com.gabriel.dscatalog.dto.CategoryDTO;
import com.gabriel.dscatalog.models.Category;
import com.gabriel.dscatalog.repositories.CategoryRepository;
import com.gabriel.dscatalog.services.exceptions.DatabaseException;
import com.gabriel.dscatalog.services.exceptions.EntityNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public Page<CategoryDTO> findAllPaged(PageRequest pageRequest){
        Page<Category> list = repository.findAll(pageRequest);
        return list.map(x -> new CategoryDTO(x));


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
            Category entity = obj.orElseThrow(() -> new EntityNotFound("Id not found" + id));
            entity.setName(dto.getName());
            entity = repository.save(entity);
            return new CategoryDTO(entity);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)){
            throw new EntityNotFound("Id not found " + id);
        }try{
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Integrity violation");
        }
    }
}



