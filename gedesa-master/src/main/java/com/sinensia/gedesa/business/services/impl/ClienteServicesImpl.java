package com.sinensia.gedesa.business.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinensia.gedesa.business.model.Cliente;
import com.sinensia.gedesa.business.services.ClienteServices;
import com.sinensia.gedesa.integration.repositories.ClienteRepository;

@Service
public class ClienteServicesImpl implements ClienteServices{

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void create(Cliente cliente) {
		clienteRepository.save(cliente);
	}

	@Override
	public Cliente read(String identificadorFiscal) {
		Optional<Cliente> optional = clienteRepository.findById(identificadorFiscal);
		return optional.isPresent()? optional.get() : null;
	}

	@Override
	public boolean update(Cliente cliente) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Cliente> getAll() {
		return clienteRepository.findAll();
	}

	@Override
	public List<Cliente> getByNombreLike(String nombre) {
		
		return clienteRepository.findAll()
				.stream()
				.filter(x -> x.getNombre() != null)
				.filter(x -> x.getNombre().contains(nombre))
				.collect(Collectors.toList());
	}

}
