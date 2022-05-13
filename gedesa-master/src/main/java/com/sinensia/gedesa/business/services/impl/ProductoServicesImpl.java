package com.sinensia.gedesa.business.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sinensia.gedesa.business.model.Familia;
import com.sinensia.gedesa.business.model.Producto;
import com.sinensia.gedesa.business.services.ProductoServices;

@Service
public class ProductoServicesImpl implements ProductoServices{
	
	
	
	@Override
	public Producto create(Producto producto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Producto read(int codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Producto producto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int codigo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Producto> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> getBetweenPriceRange(double min, double max) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> getBetweenDates(Date desde, Date hasta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> getByFamilia(Familia familia) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumeroTotalProductos() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumeroTotalProductosByFamilia(Familia familia) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<Familia, Integer> getNumeroProductosAgrupados() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Familia, Double> getPreciosMediosAgrupados() {
		// TODO Auto-generated method stub
		return null;
	}

}
