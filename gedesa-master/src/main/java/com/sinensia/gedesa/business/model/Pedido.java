package com.sinensia.gedesa.business.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="PEDIDOS")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@TableGenerator(name = "GENERADOR",
				    table = "SECUENCIAS",
				    pkColumnName = "IDS",
				    pkColumnValue = "LINEA_SECUENCIA",
				    valueColumnName = "VALOR",
				    allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE,
					generator = "GENERADOR")
	private Long codigo;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_HORA")
	private Date fechaHora;
	
	@ManyToOne
	@JoinColumn(name = "ID_COMERCIAL")
	private Comercial comercial;
	
	@ManyToOne
	@JoinColumn(name = "ID_CLIENTE")
	private Cliente cliente;
	private String observaciones;
	
	@ElementCollection
	@JoinTable(name = "LINEAS_PEDIDOS", joinColumns = @JoinColumn(name = "CODIGO_PEDIDO"))
	private List<LineaPedido> lineas;
	
	public Pedido() {
		
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public Comercial getComercial() {
		return comercial;
	}

	public void setComercial(Comercial comercial) {
		this.comercial = comercial;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public List<LineaPedido> getLineas() {
		return lineas;
	}

	public void setLineas(List<LineaPedido> lineas) {
		this.lineas = lineas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Pedido other = (Pedido) obj;
		return Objects.equals(codigo, other.codigo);
	}

	@Override
	public String toString() {
		return "Pedido [codigo=" + codigo + ", fechaHora=" + fechaHora + ", comercial=" + comercial + ", cliente="
				+ cliente + ", observaciones=" + observaciones + ", lineas=" + lineas + "]";
	}
	
}
