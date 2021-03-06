package com.sinensia.gedesa.integration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sinensia.gedesa.business.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}
