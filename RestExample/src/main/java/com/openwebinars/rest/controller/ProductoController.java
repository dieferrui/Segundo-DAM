package com.openwebinars.rest.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.openwebinars.rest.dto.CrearProductoDTO;
import com.openwebinars.rest.dto.ProductoDTO;
import com.openwebinars.rest.dto.converter.ProductoDTOConverter;

import com.openwebinars.rest.error.ApiError;
import com.openwebinars.rest.error.ProductoNotFoundException;
import com.openwebinars.rest.modelo.Categoria;
import com.openwebinars.rest.modelo.CategoriaRepositorio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.openwebinars.rest.modelo.Producto;
import com.openwebinars.rest.modelo.ProductoRepositorio;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductoController {

	private final ProductoRepositorio productoRepositorio;
	private final ProductoDTOConverter productoDTOConverter;
	private final CategoriaRepositorio categoriaRepositorio;

	/**
	 * Obtenemos todos los productos
	 * 
	 * @return
	 */
	@GetMapping("/producto")
	public ResponseEntity<?> obtenerTodos() {
		List<Producto> results = productoRepositorio.findAll();

		if (results.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			// return ResponseEntity.ok(results);
			List<ProductoDTO> dtoList = results.stream().map(productoDTOConverter::convertToDto).collect(Collectors.toList());
			return ResponseEntity.ok(dtoList);
		}
	}

	/**
	 * Obtenemos un producto en base a su ID
	 * 
	 * @param id
	 * @return Null si no encuentra el producto
	 */
	@GetMapping("/producto/{id}")
	public ResponseEntity<?> obtenerUno(@PathVariable Long id) {
		/*Producto result = productoRepositorio.findById(id).orElse(null);

		if (result == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(result);
		}
		*/

		return productoRepositorio.findById(id)
				.map(p -> ResponseEntity.ok(productoDTOConverter.convertToDto(p)))
				.orElseThrow(() -> new ProductoNotFoundException(id));
	}

	/**
	 * Insertamos un nuevo producto
	 * 
	 * @param nuevo
	 * @return producto insertado
	 */
	@PostMapping("/producto")
	public ResponseEntity<Producto> nuevoProducto(@RequestBody CrearProductoDTO nuevo) {
		// Producto saved = productoRepositorio.save(nuevo);
		// return ResponseEntity.status(HttpStatus.CREATED).body(saved);

		Producto nuevoProducto = new Producto();
		nuevoProducto.setNombre(nuevo.getNombre());
		nuevoProducto.setPrecio(nuevo.getPrecio());
		Categoria categoria = categoriaRepositorio.findById(nuevo.getCategoriaId()).orElse(null);
		nuevoProducto.setCategoria(categoria);

		return ResponseEntity.status(HttpStatus.CREATED).body(productoRepositorio.save(nuevoProducto));
	}

	/**
	 * 
	 * @param editar
	 * @param id
	 * @return
	 */
	@PutMapping("/producto/{id}")
	public ResponseEntity<?> editarProducto(@RequestBody Producto editar, @PathVariable Long id) {

		return productoRepositorio.findById(id).map(p -> {
			p.setNombre(editar.getNombre());
			p.setPrecio(editar.getPrecio());

			return ResponseEntity.ok(productoRepositorio.save(p));
		}).orElseThrow(() -> new ProductoNotFoundException(id));
	}

	/**
	 * Borra un producto del catálogo en base a su id
	 * @param id
	 * @return
	 */
	@DeleteMapping("/producto/{id}")
	public ResponseEntity<?> borrarProducto(@PathVariable Long id) {
		// productoRepositorio.deleteById(id);
		Producto producto = productoRepositorio.findById(id).orElseThrow(() -> new ProductoNotFoundException(id));

		productoRepositorio.delete(producto);
		return ResponseEntity.noContent().build();
	}
}
