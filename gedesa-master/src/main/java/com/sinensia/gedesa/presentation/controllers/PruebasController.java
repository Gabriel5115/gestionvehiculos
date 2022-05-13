package com.sinensia.gedesa.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinensia.gedesa.business.model.Pedido;
import com.sinensia.gedesa.business.model.Producto;
import com.sinensia.gedesa.integration.repositories.ClienteRepository;
import com.sinensia.gedesa.integration.repositories.PedidoRepository;
import com.sinensia.gedesa.integration.repositories.ProductoRepository;

@RestController
public class PruebasController {

	@Autowired
	private ClienteRepository clienteRepository;
	
	
	//TODO AQUI INYECTAMOS LOS REPOSITORIOS PARA PODERLOS PROBAR DIRECTAMENTE
	// NOS OLVIDAMOS DE LOS SERVICIOS E IMPLEMENTACIONES DE BUSINESS!
	
	
	@Autowired
	private ProductoRepository productoRepository;
	//SOLO PARA HACER PRUEBAS (Luego lo tiraremos a la basura)
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	
	@GetMapping("/repositorio/productos")
	public List<Producto> getAllProductos(){
		return productoRepository.findAll();
	}
	
	@GetMapping("/repositorio/pedidos")
	public List<Pedido> getAllPedidos(){
		return pedidoRepository.findAll();
	}
	
	
	
	
	
}
