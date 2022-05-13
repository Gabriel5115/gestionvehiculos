package com.sinensia.gedesa.business.services.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.sinensia.gedesa.business.model.Familia;
import com.sinensia.gedesa.business.model.Producto;
import com.sinensia.gedesa.business.services.ProductoServices;

public class ProductoServicesDummyImpl implements ProductoServices{

private final TreeMap<Integer, Producto> PRODUCTOS_DB = new TreeMap<>();
	
	public ProductoServicesDummyImpl() {
		init();
	}
	
	@Override
	public Producto create(Producto producto) {
		
		if(producto.getCodigo() != null) {
			throw new IllegalStateException("El producto ya tiene c�digo! (" + producto.getCodigo() + ")");
		}
		
		producto.setCodigo(PRODUCTOS_DB.lastKey() + 1);
		
		PRODUCTOS_DB.put(producto.getCodigo(), producto);
		
		return producto;
	}

	@Override
	public Producto read(int codigo) {
	
		return PRODUCTOS_DB.get(codigo);
	}

	@Override
	public boolean update(Producto producto) {
		
		if(PRODUCTOS_DB.containsValue(producto)) {
			PRODUCTOS_DB.replace(producto.getCodigo(), producto);
			return true;
		}
		
		return false;
	}

	@Override
	public boolean delete(int codigo) {
		
		Producto productoEliminado = PRODUCTOS_DB.remove(codigo);
		
		return productoEliminado != null;
	}

	@Override
	public List<Producto> getAll() {

		return new ArrayList<>(PRODUCTOS_DB.values());
	}

	@Override
	public List<Producto> getBetweenPriceRange(double min, double max) {
		
		List<Producto> productos = new ArrayList<>();
		
		for(Producto producto: PRODUCTOS_DB.values()) {
			if(producto.getPrecio() >= min && producto.getPrecio() <= max) {
				productos.add(producto);
			}
		}
		
		return productos;	
	}

	@Override
	public List<Producto> getBetweenDates(Date desde, Date hasta) {
		
		return PRODUCTOS_DB.values().stream()
				.filter(x -> x.getFechaAlta().compareTo(desde) >= 0 && x.getFechaAlta().compareTo(hasta) <= 0)
				.collect(Collectors.toList());
	}

	@Override
	public List<Producto> getByFamilia(Familia familia) {
		
		return PRODUCTOS_DB.values().stream()
				.filter(x -> x.getFamilia() == familia)
				.collect(Collectors.toList());
	}

	@Override
	public int getNumeroTotalProductos() {
		return PRODUCTOS_DB.size();
	}

	@Override
	public int getNumeroTotalProductosByFamilia(Familia familia) {
		
		return (int) PRODUCTOS_DB.values().stream()
				.filter(x -> x.getFamilia() == familia)
				.count();
	}

	@Override
	public Map<Familia, Integer> getNumeroProductosAgrupados() {
		
		Map<Familia, Integer> resultado = new HashMap<>();
		
		int totalHardware = 0;
		int totalSoftware = 0;
		int totalConsumibles = 0;
		
		for(Producto producto: PRODUCTOS_DB.values()) {
			switch (producto.getFamilia()) {
			case HARDWARE: totalHardware++; break;
			case SOFTWARE: totalSoftware++; break;
			case CONSUMIBLES: totalConsumibles++; break;
			}
		}
		
		resultado.put(Familia.HARDWARE, totalHardware);
		resultado.put(Familia.SOFTWARE, totalSoftware);
		resultado.put(Familia.CONSUMIBLES, totalConsumibles);
		
		return resultado;
	}

	@Override
	public Map<Familia, Double> getPreciosMediosAgrupados() {
		
		Map<Familia, Double> resultado = new HashMap<>();
		
		int totalHardware = 0;
		int totalSoftware = 0;
		int totalConsumibles = 0;
		
		Double acumuladoHardware = 0.0;
		Double acumuladoSoftware = 0.0;
		Double acumuladoConsumibles = 0.0;
		
		for(Producto producto: PRODUCTOS_DB.values()) {
			
			switch (producto.getFamilia()) {
			
				case HARDWARE: 		totalHardware++;
									acumuladoHardware += producto.getPrecio();
									break;
				case SOFTWARE: 		totalSoftware++;
									acumuladoSoftware += producto.getPrecio();
									break;
				case CONSUMIBLES: 	totalConsumibles++;
									acumuladoConsumibles += producto.getPrecio();
									break;
			}
		}
		
		resultado.put(Familia.HARDWARE, totalHardware == 0 ? null : acumuladoHardware / totalHardware);
		resultado.put(Familia.SOFTWARE, totalSoftware == 0 ? null : acumuladoSoftware / totalSoftware);
		resultado.put(Familia.CONSUMIBLES, totalConsumibles == 0 ? null : acumuladoConsumibles / totalConsumibles);
		
		return resultado;
	}
	
	// *****************************************************************
	//
	// PRIVATE METHODS
	//
	// *****************************************************************
	
	private void init() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Date fecha1 = null;
		Date fecha2 = null;
		Date fecha3 = null;
		Date fecha4 = null;
		Date fecha5 = null;
		
		try {
			fecha1 = sdf.parse("01/10/2020");
			fecha2 = sdf.parse("01/10/2020");
			fecha3 = sdf.parse("02/10/2020");
			fecha4 = sdf.parse("03/10/2020");
			fecha5 = sdf.parse("04/10/2020");
		} catch(Exception e) {
			
		}
		
		Producto p1 = new Producto(100, "Ordenador Epson D500", fecha1, 860.0, Familia.HARDWARE);
		Producto p2 = new Producto(101, "Alfombrilla Mouse Real Madrid", fecha2, 4.0, Familia.CONSUMIBLES);
		Producto p3 = new Producto(102, "ViruScan Deluxe", fecha3, 70.0, Familia.SOFTWARE);
		Producto p4 = new Producto(103, "Alfombrilla Mouse F.C.Barcelona", fecha4, 15.0, Familia.CONSUMIBLES);
		Producto p5 = new Producto(104, "Impresora HP 2p-plus", fecha5, 1200.0, Familia.HARDWARE);
		
		PRODUCTOS_DB.put(p1.getCodigo(), p1);
		PRODUCTOS_DB.put(p2.getCodigo(), p2);
		PRODUCTOS_DB.put(p3.getCodigo(), p3);
		PRODUCTOS_DB.put(p4.getCodigo(), p4);
		PRODUCTOS_DB.put(p5.getCodigo(), p5);
		
		p2.setDescatalogado(true);
		
	}
}
