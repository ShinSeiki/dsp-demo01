package com.apso.dsp.model.filter;

import java.time.LocalDate;

import com.apso.dsp.model.BaseModel;

public abstract class LogTransporteFilter<T extends LogTransporteFilter<T, ID>, ID> extends BaseModel<T, ID> {

	private static final long serialVersionUID = 4853374708019893542L;

	private Integer calmacenamiento;
	
	private String valmacenamiento;
	
	private Integer ctipoproducto;
	
	private String vtipoproducto;
	
	private Integer ccliente;
	
	private String vnombre;
	
	private String vapellido;
	
	private LocalDate fregistro;
	
	private String numguia;

	public LogTransporteFilter() {
	}
	
	public LogTransporteFilter(Integer calmacenamiento, String valmacenamiento, 
			Integer ctipoproducto, String vtipoproducto, Integer ccliente, 
			String vnombre, String vapellido, LocalDate fregistro, String numguia
	) {
		this.calmacenamiento = calmacenamiento;
		this.valmacenamiento = valmacenamiento;
		this.ctipoproducto = ctipoproducto;
		this.vtipoproducto = vtipoproducto;
		this.ccliente = ccliente;
		this.vnombre = vnombre;
		this.vapellido = vapellido;
		this.fregistro = fregistro;
		this.numguia = numguia;
	}

	public Integer getCalmacenamiento() {
		return calmacenamiento;
	}

	public void setCalmacenamiento(Integer calmacenamiento) {
		this.calmacenamiento = calmacenamiento;
	}

	public String getValmacenamiento() {
		return valmacenamiento;
	}

	public void setValmacenamiento(String valmacenamiento) {
		this.valmacenamiento = valmacenamiento;
	}

	public Integer getCtipoproducto() {
		return ctipoproducto;
	}

	public void setCtipoproducto(Integer ctipoproducto) {
		this.ctipoproducto = ctipoproducto;
	}

	public String getVtipoproducto() {
		return vtipoproducto;
	}

	public void setVtipoproducto(String vtipoproducto) {
		this.vtipoproducto = vtipoproducto;
	}

	public Integer getCcliente() {
		return ccliente;
	}

	public void setCcliente(Integer ccliente) {
		this.ccliente = ccliente;
	}

	public String getVnombre() {
		return vnombre;
	}

	public void setVnombre(String vnombre) {
		this.vnombre = vnombre;
	}

	public String getVapellido() {
		return vapellido;
	}

	public void setVapellido(String vapellido) {
		this.vapellido = vapellido;
	}

	public LocalDate getFregistro() {
		return fregistro;
	}

	public void setFregistro(LocalDate fregistro) {
		this.fregistro = fregistro;
	}

	public String getNumguia() {
		return numguia;
	}

	public void setNumguia(String numguia) {
		this.numguia = numguia;
	}
	
}
