package com.example.articulo.controller;

import com.example.articulo.model.Articulo;
import com.example.articulo.service.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@CrossOrigin(origins = "*") // importante
@RestController
@RequestMapping("/api/articulos") // definir la ruta base para los endpoints - añadí el api

public class ArticuloController {
    private final ArticuloService articuloService;

    @Autowired
    public ArticuloController(ArticuloService articuloService) {
        this.articuloService = articuloService;
    }

    @GetMapping
    public List<Articulo> listarActivos() {
        return articuloService.listarActivos();
    }

    @GetMapping("/inactivos")
    public List<Articulo> listarInactivos() {
        return articuloService.listarInactivos();
    }

    @GetMapping("/todos")
    public List<Articulo> listartodos() {
        return articuloService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Articulo> obtenerPorId(@PathVariable Long id) {
        return articuloService.obtenerArticuloID(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // @PostMapping
    // public Articulo crear(@RequestBody Articulo articulo) {
    // return articuloService.guardarArticulo(articulo);
    // }

    // @PutMapping("/{id}")
    // public ResponseEntity<Articulo> actualizar(@PathVariable Long id,
    // @RequestBody Articulo articulo) {
    // if (articuloService.obtenerArticuloID(id).isEmpty()) {
    // return ResponseEntity.notFound().build();
    // }
    // return ResponseEntity.ok(articuloService.actualizarArticulo(id, articulo));
    // }

    @PostMapping
    public ResponseEntity<Articulo> crear(@Valid @RequestBody Articulo articulo) {
        Articulo creado = articuloService.guardarArticulo(articulo);
        // 201 Creado -> Preguntar a GI...
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // --- ACTUALIZAR ---

    @PutMapping("/{id}")
    public ResponseEntity<Articulo> actualizar(@PathVariable Long id,
            @Valid @RequestBody Articulo articulo) {
        Articulo actualizado = articuloService.actualizarArticulo(id, articulo);
        // 200 OK
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (articuloService.obtenerArticuloID(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        articuloService.eliminarArticulo(id);
        return ResponseEntity.noContent().build();
    }
}
