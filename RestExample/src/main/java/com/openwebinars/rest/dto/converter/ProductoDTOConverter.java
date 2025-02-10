package com.openwebinars.rest.dto.converter;

import com.openwebinars.rest.dto.ProductoDTO;
import com.openwebinars.rest.modelo.Producto;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductoDTOConverter {
    private final ModelMapper modelMapper;

    public ProductoDTO convertToDto(Producto producto) {
        return modelMapper.map(producto, ProductoDTO.class);
    }
}
