package com.example.articulo.service;

import com.example.articulo.model.Articulo;
import com.example.articulo.repository.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticuloServiceImp implements ArticuloService {

    private final ArticuloRepository articuloRepository;

    @Autowired
    public ArticuloServiceImp(ArticuloRepository articuloRepository) {
        this.articuloRepository = articuloRepository;
    }

    // public List<Articulo> listarArticulos() {
    // return articuloRepository.findAll();
    // }

    @Override
    public List<Articulo> listarActivos() {
        return articuloRepository.findByActivoTrue();
    }

    @Override
    public List<Articulo> listarInactivos() {
        return articuloRepository.findByActivoFalse();
    }

    @Override
    public List<Articulo> listarTodos() {
        return articuloRepository.findAll();
    }

    public Optional<Articulo> obtenerArticuloID(Long id) {
        return articuloRepository.findById(id);
    }

    public Articulo guardarArticulo(Articulo articulo) {
        return articuloRepository.save(articulo);
    }

    public Articulo actualizarArticulo(Long id, Articulo articulo) {
        articulo.setId(id);
        return articuloRepository.save(articulo);
    }

    // public void eliminarArticulo(Long id) {
    // articuloRepository.deleteById(id);
    // }

    @Override
    public void eliminarArticulo(Long id) {
        Articulo articulo = articuloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artículo no encontrado con id: " + id));

        articulo.setActivo(false);
        articuloRepository.save(articulo);
    }

}
