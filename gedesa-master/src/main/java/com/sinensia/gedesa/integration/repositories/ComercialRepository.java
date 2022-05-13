package com.sinensia.gedesa.integration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sinensia.gedesa.business.model.Comercial;

@Repository
public interface ComercialRepository extends JpaRepository<Comercial, Integer>{

}
