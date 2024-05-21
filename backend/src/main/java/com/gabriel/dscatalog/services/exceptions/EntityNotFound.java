package com.gabriel.dscatalog.services.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class EntityNotFound extends RuntimeException{
    public EntityNotFound(String msg){
        super(msg);
    }
}
