package dev.dani.eCommerce.model.dto.request;

import dev.dani.eCommerce.exception.ResourceAlreadyExistException;

public interface Validatable <T extends CreateRequest>{

    String EMPTY_STRING = "";

    void checkExistenceForCreation(T request) throws ResourceAlreadyExistException;
}
