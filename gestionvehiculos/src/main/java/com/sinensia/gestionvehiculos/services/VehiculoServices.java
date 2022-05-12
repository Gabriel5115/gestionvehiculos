package com.sinensia.gestionvehiculos.services;

import java.util.List;

import com.sinensia.gestionvehiculos.model.Vehiculo;

public interface VehiculoServices {

	/**
	 * 
	 * 
	 */
	
	List<Vehiculo> getAll();
	
	Vehiculo create(Vehiculo vehiculo);
	
	Vehiculo getByMatricula(String matricula); 
	
	
}
