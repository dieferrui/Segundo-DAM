package com.openwebinars.rest.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductoNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 12299344402L;
    public ProductoNotFoundException(Long id) {
        super("No se ha encontrado el producto con id: " + id);
    }
}
