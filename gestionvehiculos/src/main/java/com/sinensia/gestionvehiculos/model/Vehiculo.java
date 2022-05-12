package com.sinensia.gestionvehiculos.model;

import java.io.Serializable;
import java.util.Objects;

public class Vehiculo implements Serializable{
	private static final long serialVersionUID = 1L;

	
	private String matricula;
	private String modelo;
	private String marca;
	private int numeroPuertas;
	private boolean siniestrado;
	
	public Vehiculo() {
		
	}

	public Vehiculo(String matricula, String modelo, String marca, int numeroPuertas, boolean siniestrado) {
		super();
		this.matricula = matricula;
		this.modelo = modelo;
		this.marca = marca;
		this.numeroPuertas = numeroPuertas;
		this.siniestrado = siniestrado;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getNumeroPuertas() {
		return numeroPuertas;
	}

	public void setNumeroPuertas(int numeroPuertas) {
		this.numeroPuertas = numeroPuertas;
	}

	public boolean isSiniestrado() {
		return siniestrado;
	}

	public void setSiniestrado(boolean siniestrado) {
		this.siniestrado = siniestrado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(marca);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehiculo other = (Vehiculo) obj;
		return Objects.equals(marca, other.marca);
	}

	@Override
	public String toString() {
		return "Vehiculo [matricula=" + matricula + ", modelo=" + modelo + ", marca=" + marca + ", numeroPuertas="
				+ numeroPuertas + ", siniestrado=" + siniestrado + "]";
	}
	
	
	
}
