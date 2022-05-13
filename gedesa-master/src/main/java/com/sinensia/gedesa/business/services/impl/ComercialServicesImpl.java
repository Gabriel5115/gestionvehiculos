package com.sinensia.gedesa.business.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinensia.gedesa.business.model.Comercial;
import com.sinensia.gedesa.business.services.ComercialServices;
import com.sinensia.gedesa.integration.repositories.ComercialRepository;

@Service
public class ComercialServicesImpl implements ComercialServices{

	@Autowired
	private ComercialRepository comercialRepository;
	
	@Override
	public Comercial create(Comercial comercial) {
		return comercialRepository.save(comercial);
	}

	@Override
	public Comercial read(int codigo) {
		
		Optional<Comercial> optional = comercialRepository.findById(codigo);

		return optional.isPresent() ? optional.get() : null;
	}

	@Override
	public List<Comercial> getAll() {
		return comercialRepository.findAll();
	}

}
