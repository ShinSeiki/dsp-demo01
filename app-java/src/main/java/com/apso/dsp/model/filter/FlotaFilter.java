package com.apso.dsp.model.filter;

import java.time.LocalDate;

public class FlotaFilter extends LogTransporteFilter<FlotaFilter, Integer> {

	private static final long serialVersionUID = 2652032015851522471L;

	private Integer cflota;
	
	private String numflota;
	
	public FlotaFilter() {
	}
	
	public FlotaFilter(Integer cflota, String numflota, Integer calmacenamiento, String valmacenamiento, Integer ctipoproducto, String vtipoproducto,
			Integer ccliente, String vnombre, String vapellido, LocalDate fregistro, String numguia) {
		super(calmacenamiento, valmacenamiento, ctipoproducto, vtipoproducto, ccliente, vnombre, vapellido, fregistro, numguia);
		
		this.cflota = cflota;
		this.numflota = numflota;
	}

	public Integer getCflota() {
		return cflota;
	}

	public void setCflota(Integer cflota) {
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
