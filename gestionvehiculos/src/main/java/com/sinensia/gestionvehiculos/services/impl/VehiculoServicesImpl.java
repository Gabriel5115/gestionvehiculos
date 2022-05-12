package com.sinensia.gestionvehiculos.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sinensia.gestionvehiculos.model.Vehiculo;
import com.sinensia.gestionvehiculos.services.VehiculoServices;

@Service
public class VehiculoServicesImpl implements VehiculoServices{

	private final Map<String, Vehiculo> VEHICULOS_DB = new HashMap<>();
	
	public VehiculoServicesImpl() {
		init();
	}
	
	@Override
	public List<Vehiculo> getAll() {
		
		return new ArrayList<Vehiculo>(VEHICULOS_DB.values());
	}

	@Override
	public Vehiculo create(Vehiculo vehiculo) {
		
		String matricula = vehiculo.getMatricula();
		
		if(matricula == null) {
			throw new IllegalStateException("No s epuede crear el vehiculo por que la matricula es null");
		}
		
		if(VEHICULOS_DB.containsKey(matricula)){
			throw new IllegalStateException("Ya existe un vehiculo con esa matricula " + matricula);
		}
		
		VEHICULOS_DB.put(matricula, vehiculo);
		
		return vehiculo;
	}

	@Override
	public Vehiculo getByMatricula(String matricula) {
		return VEHICULOS_DB.get(matricula);
	}
	
	private void init() {
		
		Vehiculo v1 = new Vehiculo("4190DLK" ,"Ibiza", "Seat", 3, true);
		Vehiculo v2 = new Vehiculo("7841MLK" ,"Golf", "Volkswagen", 5, true);
		Vehiculo v3 = new Vehiculo("5910JLK" ,"Q7", "Audi", 3, false);
		Vehiculo v4 = new Vehiculo("5698BLK" ,"Q2", "Audi", 3, true);
		Vehiculo v5 = new Vehiculo("5528GLK" ,"A3", "Audi", 5, false);
		
		VEHICULOS_DB.put(v1.getMatricula(), v1);
		VEHICULOS_DB.put(v2.getMatricula(), v2);
		VEHICULOS_DB.put(v3.getMatricula(), v3);
		VEHICULOS_DB.put(v4.getMatricula(), v4);
		VEHICULOS_DB.put(v5.getMatricula(), v5);
		
	}
}
