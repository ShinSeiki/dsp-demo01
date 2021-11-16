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
@Table(name = "dem_camion")
public class Camion extends LogTransporte<Camion, Integer> {

	private static final long serialVersionUID = -8069941164987395194L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ccamion;
	
	@Column(name = "placaveh", columnDefinition = "nvarchar(6)")
	private String placaveh;
	
	public Camion() {
	}

	public Camion(int ccamion, String placaveh, int cantidad, LocalDateTime fregistro, 
			LocalDateTime fentrega, BigDecimal precioenvio, String numguia, 
			Integer ctipoproducto, Integer ccliente, Integer calmacenamiento
	) {
		super(cantidad, fregistro, fentrega, precioenvio, numguia, ctipoproducto, ccliente, calmacenamiento);

		this.ccamion = ccamion;
		this.placaveh = placaveh;
	}

	public int getCcamion() {
		return ccamion;
	}

	public void setCcamion(int ccamion) {
		this.ccamion = ccamion;
	}

	public String getPlacaveh() {
		return placaveh;
	}

	public void setPlacaveh(String placaveh) {
		this.placaveh = placaveh;
	}

	@Override
	public Integer getID() {
		return ccamion;
	}
	
}
