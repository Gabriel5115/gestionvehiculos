package com.sinensia.gedesa.business.services.impl;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.sinensia.gedesa.business.model.Cliente;
import com.sinensia.gedesa.business.model.Direccion;
import com.sinensia.gedesa.business.services.ClienteServices;

public class ClienteServicesDummyImpl implements ClienteServices {

	private final Map<String, Cliente> CLIENTES_DB = new HashMap<>();
	
	public ClienteServicesDummyImpl() {
		init();
	}
	
	@Override
	public void create(Cliente cliente) {
		
		String identificadorFiscal = cliente.getIdentificadorFiscal();
		
		if(identificadorFiscal == null) {
			throw new IllegalStateException("No se puede crear un cliente con identificadorFiscal null");
		}
		
		if(CLIENTES_DB.containsKey(identificadorFiscal)) {
			throw new IllegalStateException("Ya existe un cliente con identificadorFiscal " + identificadorFiscal);
		}
		
		CLIENTES_DB.put(identificadorFiscal, cliente);
		
	}

	@Override
	public Cliente read(String identificadorFiscal) {
		return CLIENTES_DB.get(identificadorFiscal);
	}

	@Override
	public boolean update(Cliente cliente) {
		
		String identificadorFiscal = cliente.getIdentificadorFiscal();
		
		if (identificadorFiscal == null) {
			throw new IllegalStateException("No se puede actualizar un cliente con identificadorFiscal null");	
		}
		
		Cliente clienteAnterior = CLIENTES_DB.replace(identificadorFiscal, cliente);
	
		return clienteAnterior != null;
	}

	@Override
	public List<Cliente> getAll() {
		return new ArrayList<Cliente>(CLIENTES_DB.values());
	}

	@Override
	public List<Cliente> getByNombreLike(String nombre) {
		
		return CLIENTES_DB.values().stream()
				
				.filter(x -> {
					
					String nombreA = unaccent(x.getNombre().toUpperCase());
					String nombreB = unaccent(nombre.toUpperCase());
					
					return nombreA.contains(nombreB);
				})
				.collect(Collectors.toList());
	}
	
	// *****************************************************************
	//
	// PRIVATE METHODS
	//
	// *****************************************************************
	
	private void init() {
		
		Cliente cliente1 = new Cliente();
		Cliente cliente2 = new Cliente();
		Cliente cliente3 = new Cliente();
		
		Direccion direccion1 = new Direccion();
		Direccion direccion2 = new Direccion();
		Direccion direccion3 = new Direccion();
		
		direccion1.setDireccion("Avenida de la Industira, 234 (POLIGONO INDUSTRIAL URBINSA)");
		direccion1.setPoblacion("Santa Perpetua de Mogoda");
		direccion1.setCodigoPostal("08014");
		direccion1.setProvincia("Barcelona");
		direccion1.setPais("España");
		
		direccion2.setDireccion("c/Claudio Coello, 67 (Barrio Salamanca)");
		direccion2.setPoblacion("Madrid");
		direccion2.setCodigoPostal("28001");
		direccion2.setProvincia("Madrid");
		direccion2.setPais("España");
		
		direccion3.setDireccion("c/Asturias, 234 principal 2");
		direccion3.setPoblacion("Mataró");
		direccion3.setCodigoPostal("08044");
		direccion3.setProvincia("Barcelona");
		direccion3.setPais("España");
		
		cliente1.setIdentificadorFiscal("48872213F");
		cliente1.setNombre("Pepín");
		cliente1.setApellido1("Gálvez");
		cliente1.setApellido2("Ridruejo");
		cliente1.setNombreComercial("HERMANOS GALVEZ, S.L.");
		cliente1.setDireccion(direccion1);
		
		cliente2.setIdentificadorFiscal("B22489723");
		cliente2.setNombre("Carlota");
		cliente2.setApellido1("Cifuentes");
		cliente2.setApellido2("Merino");
		cliente2.setNombreComercial("SlowFood Madrid S.A.");
		cliente2.setDireccion(direccion2);
		
		cliente3.setIdentificadorFiscal("20098557K");
		cliente3.setNombre("Honorio");
		cliente3.setApellido1("Martín");
		cliente3.setApellido2("Salvador");
		cliente3.setNombreComercial(null);
		cliente3.setDireccion(direccion3);
		
		CLIENTES_DB.put(cliente1.getIdentificadorFiscal(), cliente1);
		CLIENTES_DB.put(cliente2.getIdentificadorFiscal(), cliente2);
		CLIENTES_DB.put(cliente3.getIdentificadorFiscal(), cliente3);
		
	}
	
	private String unaccent(String src) {
		return Normalizer
				.normalize(src, Normalizer.Form.NFD)
				.replaceAll("[^\\p{ASCII}]", "");
	}

}
