package com.sinensia.gedesa.services;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sinensia.gedesa.business.model.Familia;
import com.sinensia.gedesa.business.model.Producto;
import com.sinensia.gedesa.business.services.ProductoServices;
import com.sinensia.gedesa.business.services.impl.ProductoServicesDummyImpl;

class ProductoServicesTest {

	private ProductoServices productoServices;

	@BeforeEach
	void setUp() throws Exception {
		productoServices = new ProductoServicesDummyImpl();
	}

	@Test
	void testCreate() {
		
		Producto producto = new Producto(null, "Producto Pruebas", new Date(0), 100.0, Familia.SOFTWARE);
		
		Producto createdProducto = productoServices.create(producto);
		
		int numeroProductos = productoServices.getNumeroTotalProductos();
		
		assertNotNull(createdProducto);
		assertEquals(105, createdProducto.getCodigo());
		assertEquals(6, numeroProductos);
		
		Producto productoExistente = new Producto();
		productoExistente.setCodigo(100);
		
		Exception exception = assertThrows(IllegalStateException.class, () -> {
			productoServices.create(productoExistente);
	    });
		
		String mensajeError = exception.getMessage();
		
		assertEquals(mensajeError, "El producto ya tiene c�digo! (100)");
		
	}

	@Test
	void testRead() {
		
		Producto producto = productoServices.read(100);
		
		assertNotNull(producto);
		assertEquals(100, producto.getCodigo());
		assertEquals("Ordenador Epson D500", producto.getNombre());
		assertEquals(Familia.HARDWARE, producto.getFamilia());
		
	}

	@Test
	void testUpdate() {

		Producto producto = new Producto(100, "PRODUCTO ACTUALIZADO", new Date(1000), 10000.0, Familia.SOFTWARE);
		
		boolean actualizado =  productoServices.update(producto);
		
		assertTrue(actualizado);
		
		Producto productoActualizado = productoServices.read(100);
		
		assertNotNull(productoActualizado);
		assertEquals(100, productoActualizado.getCodigo());
		assertEquals("PRODUCTO ACTUALIZADO", productoActualizado.getNombre());
		assertEquals(new Date(1000), productoActualizado.getFechaAlta());
		assertEquals(10000.0, productoActualizado.getPrecio());
		assertEquals(Familia.SOFTWARE, productoActualizado.getFamilia());
		
	}

	@Test
	void testDelete() {
		
		boolean resultado = productoServices.delete(100);
		
		assertTrue(resultado);
		
		Producto productoEliminado = productoServices.read(100);
		
		assertNull(productoEliminado);
		
		resultado = productoServices.delete(100);
		
		assertFalse(resultado);
		
	}

	@Test
	void testGetAll() {
		
		List<Producto> productos = productoServices.getAll();
		
		assertEquals(5, productos.size());
		assertNotNull(productos.get(0));
		
		Producto producto = new Producto();
		producto.setCodigo(100);
		
		assertTrue(productos.contains(producto));
		
	}

	@Test
	void testGetBetweenPriceRange() {
		
		List<Producto> productos = productoServices.getBetweenPriceRange(15.0, 860.0);
		
		assertEquals(3, productos.size());
		
		Producto p1 = new Producto();
		Producto p2 = new Producto();
		Producto p3 = new Producto();
		
		p1.setCodigo(100);
		p2.setCodigo(102);
		p3.setCodigo(103);
		
		assertTrue(productos.containsAll(Arrays.asList(p1, p2, p3)));
		
	}

	@Test
	void testGetBetweenDates() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Date desde = null;
		Date hasta = null;
		
		try {
			desde = sdf.parse("02/10/2020");
			hasta = sdf.parse("03/10/2020");
		} catch(Exception e) {
			
		}
		
		List<Producto> productos = productoServices.getBetweenDates(desde, hasta);
		
		assertEquals(2, productos.size());
		
		Producto p1 = new Producto();
		Producto p2 = new Producto();
		
		p1.setCodigo(102);
		p2.setCodigo(103);
	
		assertTrue(productos.containsAll(Arrays.asList(p1, p2)));
		
	}

	@Test
	void testGetByFamilia() {
		
		List<Producto> productos = productoServices.getByFamilia(Familia.HARDWARE);
		
		assertEquals(2, productos.size());
		
		Producto p1 = new Producto();
		Producto p2 = new Producto();
		
		p1.setCodigo(100);
		p2.setCodigo(104);
		
		assertTrue(productos.containsAll(Arrays.asList(p1, p2)));
		
	}

	@Test
	void testGetNumeroTotalProductos() {
		
		int numeroProductos = productoServices.getNumeroTotalProductos();
		
		assertEquals(5, numeroProductos);
	}

	@Test
	void testGetNumeroTotalProductosByFamilia() {
		
		int totalHardware = productoServices.getNumeroTotalProductosByFamilia(Familia.HARDWARE);
		int totalSoftware = productoServices.getNumeroTotalProductosByFamilia(Familia.SOFTWARE);
		int totalConsumibles = productoServices.getNumeroTotalProductosByFamilia(Familia.CONSUMIBLES);
		
		assertEquals(2, totalHardware);
		assertEquals(1, totalSoftware);
		assertEquals(2, totalConsumibles);
	}

	@Test
	void testGetNumeroProductosAgrupados() {
		
		Map<Familia, Integer> resultado = productoServices.getNumeroProductosAgrupados();
		
		assertEquals(3, resultado.size());
		
		assertEquals(2, resultado.get(Familia.HARDWARE));
		assertEquals(1, resultado.get(Familia.SOFTWARE));
		assertEquals(2, resultado.get(Familia.CONSUMIBLES));
		
	}

	@Test
	void testGetPreciosMediosAgrupados() {
		
		Map<Familia, Double> resultado = productoServices.getPreciosMediosAgrupados();
		
		assertEquals(3, resultado.size());
		
		assertEquals(1030.0, resultado.get(Familia.HARDWARE), 0.00001);
		assertEquals(70.0, resultado.get(Familia.SOFTWARE), 0.00001);
		assertEquals(9.5, resultado.get(Familia.CONSUMIBLES), 0.00001);
	}

}
