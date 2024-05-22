package com.gabriel.dscatalog.services;


import com.gabriel.dscatalog.dto.ProductDTO;
import com.gabriel.dscatalog.models.Product;
import com.gabriel.dscatalog.repositories.ProductRepository;
import com.gabriel.dscatalog.services.exceptions.DatabaseException;
import com.gabriel.dscatalog.services.exceptions.EntityNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(PageRequest pageRequest){
        Page<Product> list = repository.findAll(pageRequest);
        return list.map(x -> new ProductDTO(x));


    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> obj = repository.findById(id);
        Product entity = obj.orElseThrow(() -> new EntityNotFound("Entity not found"));
        return new ProductDTO(entity, entity.getCategories());
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
        entity = repository.save(entity);
        return new ProductDTO(entity);
    }
    @Transactional
    public ProductDTO update(Long id,ProductDTO dto) {
            Optional<Product> obj = repository.findById(id);
            Product entity = obj.orElseThrow(() -> new EntityNotFound("Id not found" + id));
            entity.setName(dto.getName());
            entity.setDescription(dto.getDescription());
            entity.setPrice(dto.getPrice());
            entity.setImgUrl(dto.getImgUrl());
            entity = repository.save(entity);
            entity = repository.save(entity);
            return new ProductDTO(entity);
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



