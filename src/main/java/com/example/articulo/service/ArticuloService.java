package com.example.articulo.service;

import com.example.articulo.model.Articulo;
import java.util.List;
import java.util.Optional;

public interface ArticuloService {

    List<Articulo> listarActivos();

    List<Articulo> listarInactivos();

    List<Articulo> listarTodos();

    Optional<Articulo> obtenerArticuloID(Long id);

    Articulo guardarArticulo(Articulo articulo);

    Articulo actualizarArticulo(Long id, Articulo articulo);

    void eliminarArticulo(Long id);
}
