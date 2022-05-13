package com.sinensia.gedesa.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sinensia.gedesa.business.model.Cliente;
import com.sinensia.gedesa.business.model.Direccion;
import com.sinensia.gedesa.business.services.ClienteServices;
import com.sinensia.gedesa.business.services.impl.ClienteServicesDummyImpl;

class ClienteServicesTest {

	private ClienteServices clienteServices;
	
	@BeforeEach
	void setUp() throws Exception {
		clienteServices = new ClienteServicesDummyImpl();
	}
	
	@Test
	void testCreate() {
		
		Direccion direccion = new Direccion();
		direccion.setDireccion("Direccion Create");
		direccion.setPoblacion("Población Create");
		direccion.setCodigoPostal("00000");
		direccion.setProvincia("Provincia Create");
		direccion.setPais("Pais Create");
		
		Cliente cliente = new Cliente();
		cliente.setIdentificadorFiscal("222222222N");
		cliente.setNombre("Nombre Prueba Create");
		cliente.setApellido1("Apellido1 Create");
		cliente.setApellido2("Apellido2 Create");
		cliente.setNombreComercial("Nombre Comercial Create");
		cliente.setDireccion(direccion);
		
		clienteServices.create(cliente);
		
		Cliente createdCliente = clienteServices.read("222222222N");
		int numeroClientes = clienteServices.getAll().size();
		
		assertEquals(4, numeroClientes);
		assertNotNull(createdCliente);
		assertEquals("Nombre Prueba Create", createdCliente.getNombre());
		assertEquals("Nombre Comercial Create", createdCliente.getNombreComercial());
		
		assertNotNull(createdCliente.getDireccion());
		
		assertEquals("Direccion Create", createdCliente.getDireccion().getDireccion());
		assertEquals("00000", createdCliente.getDireccion().getCodigoPostal());

		Cliente clienteExistente = new Cliente();
		clienteExistente.setIdentificadorFiscal("20098557K");
		
		Exception exception = assertThrows(IllegalStateException.class, () -> {
			clienteServices.create(clienteExistente);
	    });
		
		String mensajeError = exception.getMessage();
		
		assertEquals(mensajeError, "Ya existe un cliente con identificadorFiscal 20098557K");
		
	}
	
	@Test
	void testRead() {
		
		Cliente cliente = clienteServices.read("48872213F");
		
		assertNotNull(cliente);
		assertEquals("48872213F", cliente.getIdentificadorFiscal());
		assertEquals("Gálvez", cliente.getApellido1());
		assertNotNull(cliente.getDireccion());
		assertEquals("España", cliente.getDireccion().getPais());
		assertEquals("Santa Perpetua de Mogoda", cliente.getDireccion().getPoblacion());
		assertEquals("Barcelona", cliente.getDireccion().getProvincia());
	}
	
	@Test
	void testUpdate() {
		fail("No implemented yet...");
	}
	
	@Test
	void testGetAll() {
		
		List<Cliente> clientes = clienteServices.getAll();
		
		assertEquals(3, clientes.size());
		assertNotNull(clientes.get(0));
		
		Cliente cliente = new Cliente();
		cliente.setIdentificadorFiscal("48872213F");
		
		assertTrue(clientes.contains(cliente));
		
	}
	
	@Test
	void testGetByNombreLike() {
		
		List<Cliente> clientes = clienteServices.getByNombreLike("in");
		
		assertEquals(1, clientes.size());
		assertNotNull(clientes.get(0));
		assertEquals("Pepín", clientes.get(0).getNombre());
		
		clientes = clienteServices.getByNombreLike("n");
		
		assertEquals(2, clientes.size());
		
		Cliente cliente1 = new Cliente();
		Cliente cliente2 = new Cliente();
		
		cliente1.setIdentificadorFiscal("48872213F");
		cliente2.setIdentificadorFiscal("20098557K");
		
		assertTrue(clientes.contains(cliente1));
		assertTrue(clientes.contains(cliente2));
		
	}
	
}
