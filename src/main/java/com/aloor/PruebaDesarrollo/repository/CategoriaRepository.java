package com.aloor.PruebaDesarrollo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aloor.PruebaDesarrollo.entity.Categoria;

@Repository

public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

	Optional<Categoria> findByNombreIgnoreCase(String categoria);
}
