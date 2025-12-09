package com.example.articulo.repository;

import com.example.articulo.model.Articulo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, Long> {

    // Mirando las ultimas clases creí necesario agregar estos dos filtros
    List<Articulo> findByActivoTrue();

    List<Articulo> findByActivoFalse();

}
