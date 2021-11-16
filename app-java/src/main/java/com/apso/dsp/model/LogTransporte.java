package com.apso.dsp.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@MappedSuperclass
public abstract class LogTransporte<T extends LogTransporte<T, ID>, ID> extends BaseModel<T, ID> {

	private static final long serialVersionUID = -1511691505852713637L;

	@Column(name = "cantidad")
	private int cantidad;
	
	@Column(name = "fregistro")
	private LocalDateTime fregistro;
	
	@Column(name = "fentrega")
	private LocalDateTime fentrega;
	
	@Column(name = "precioenvio")
	private BigDecimal precioenvio;
	
	@Column(name = "numguia", columnDefinition = "nvarchar(10)")
	private String numguia;

	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name = "ctipoproducto", referencedColumnName = "ctipoproducto", insertable = false, updatable = false)
	private TipoProducto tproducto;
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name = "ccliente", referencedColumnName = "ccliente", insertable = false, updatable = false)
	private Cliente cliente;
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name = "almacenamiento", referencedColumnName = "calmacenamiento", insertable = false, updatable = false)
	private Almacenamiento almacenamiento;
	
	@Column(name = "ctipoproducto")
	private Integer ctipoproducto;
	
	@Column(name = "ccliente")
	private Integer ccliente;
	
	@Column(name = "calmacenamiento")
	private Integer calmacenamiento;
	
	public LogTransporte() {
	}
	
	public LogTransporte(int cantidad, LocalDateTime fregistro, LocalDateTime fentrega, 
			BigDecimal precioenvio, String numguia, 
			Integer ctipoproducto, Integer ccliente, Integer calmacenamiento) 
	{
		this.cantidad = cantidad;
		this.fregistro = fregistro;
		this.fentrega = fentrega;
		this.precioenvio = precioenvio;
		this.numguia = numguia;
		this.ctipoproducto = ctipoproducto;
		this.ccliente = ccliente;
		this.calmacenamiento = calmacenamiento;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public LocalDateTime getFregistro() {
		return fregistro;
	}

	public void setFregistro(LocalDateTime fregistro) {
		this.fregistro = fregistro;
	}

	public LocalDateTime getFentrega() {
		return fentrega;
	}

	public void setFentrega(LocalDateTime fentrega) {
		this.fentrega = fentrega;
	}

	public BigDecimal getPrecioenvio() {
		return precioenvio;
	}

	public void setPrecioenvio(BigDecimal precioenvio) {
		this.precioenvio = precioenvio;
	}

	public String getNumguia() {
		return numguia;
	}

	public void setNumguia(String numguia) {
		this.numguia = numguia;
	}

	public TipoProducto getTproducto() {
		return tproducto;
	}

	public void setTproducto(TipoProducto tproducto) {
		this.tproducto = tproducto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Almacenamiento getAlmacenamiento() {
		return almacenamiento;
	}

	public void setAlmacenamiento(Almacenamiento almacenamiento) {
		this.almacenamiento = almacenamiento;
	}

	public Integer getCtipoproducto() {
		return ctipoproducto;
	}

	public void setCtipoproducto(Integer ctipoproducto) {
		this.ctipoproducto = ctipoproducto;
	}

	public Integer getCcliente() {
		return ccliente;
	}

	public void setCcliente(Integer ccliente) {
		this.ccliente = ccliente;
	}

	public Integer getCalmacenamiento() {
		return calmacenamiento;
	}

	public void setCalmacenamiento(Integer calmacenamiento) {
		this.calmacenamiento = calmacenamiento;
	}
	
}
