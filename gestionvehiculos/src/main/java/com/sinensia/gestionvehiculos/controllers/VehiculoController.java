package com.sinensia.gestionvehiculos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinensia.gestionvehiculos.model.Vehiculo;
import com.sinensia.gestionvehiculos.services.VehiculoServices;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {
	
	@Autowired
	private VehiculoServices vehiculoServices;
	
	@GetMapping
	public List<Vehiculo> getVehiculos(){
		return vehiculoServices.getAll();
	}
	
	@GetMapping("/{matricula}")
	public Vehiculo getByMatricula(@PathVariable String matricula) {
		return vehiculoServices.getByMatricula(matricula);
		
	}
	
	@PostMapping
	public Vehiculo create(@RequestBody Vehiculo vehiculo) {
		
		Vehiculo vehiculoCreado = vehiculoServices.create(vehiculo);
		
		if(vehiculoCreado.equals(null)) {
			throw new IllegalStateException("No se ha podio crear");
		}
		
		
		return vehiculoCreado;
	}

}
