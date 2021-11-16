package com.apso.dsp.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dem_flota")
public class Flota extends LogTransporte<Flota, Integer> {

	private static final long serialVersionUID = -1879614102972374580L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cflota;
	
	@Column(name = "numflota", columnDefinition = "nvarchar(8)")
	private String numflota;
	
	public Flota() {
	}

	public Flota(int cflota, String numflota, int cantidad, LocalDateTime fregistro, LocalDateTime fentrega, BigDecimal precioenvio, String numguia,
			Integer ctipoproducto, Integer ccliente, Integer calmacenamiento) {
		super(cantidad, fregistro, fentrega, precioenvio, numguia, ctipoproducto, ccliente, calmacenamiento);

		this.cflota = cflota;
		this.numflota = numflota;
	}

	public int getCflota() {
		return cflota;
	}

	public void setCflota(int cflota) {
		this.cflota = cflota;
	}

	public String getNumflota() {
		return numflota;
	}

	public void setNumflota(String numflota) {
		this.numflota = numflota;
	}

	@Override
	public Integer getID() {
		return cflota;
	}
	
}
