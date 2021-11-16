package com.apso.dsp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;

@Entity
@Table(name = "dem_almacenamiento")
public class Almacenamiento extends BaseModel<Almacenamiento, Integer> {

	private static final long serialVersionUID = -55347756835345763L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int calmacenamiento;
	
	@Column(name = "valmacenamiento", columnDefinition = "text")
	private String valmacenamiento;
	
	@Column(name = "vdireccion", columnDefinition = "text")
	private String vdireccion;
	
	@NotFound
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "talmacenamiento")
	private TipoAlmacenamiento talmacenamiento;
	
	@NotFound
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "tregion")
	private TipoRegion tregion;
	
	public int getCalmacenamiento() {
		return calmacenamiento;
	}

	public void setCalmacenamiento(int calmacenamiento) {
		this.calmacenamiento = calmacenamiento;
	}

	public String getValmacenamiento() {
		return valmacenamiento;
	}

	public void setValmacenamiento(String valmacenamiento) {
		this.valmacenamiento = valmacenamiento;
	}

	public String getVdireccion() {
		return vdireccion;
	}

	public void setVdireccion(String vdireccion) {
		this.vdireccion = vdireccion;
	}

	public TipoAlmacenamiento getTalmacenamiento() {
		return talmacenamiento;
	}

	public void setTalmacenamiento(TipoAlmacenamiento talmacenamiento) {
		this.talmacenamiento = talmacenamiento;
	}

	public TipoRegion getTregion() {
		return tregion;
	}

	public void setTregion(TipoRegion tregion) {
		this.tregion = tregion;
	}

	@Override
	public Integer getID() {
		return calmacenamiento;
	}

}
