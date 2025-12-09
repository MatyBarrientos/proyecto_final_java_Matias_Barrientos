package com.example.articulo.repository;

import com.example.articulo.model.Articulo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticuloRepository extends JpaRepository <Articulo, Long> {
}
