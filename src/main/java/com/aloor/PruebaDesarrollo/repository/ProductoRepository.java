package com.aloor.PruebaDesarrollo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aloor.PruebaDesarrollo.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}
