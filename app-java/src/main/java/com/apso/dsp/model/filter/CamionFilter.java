package com.apso.dsp.model.filter;

import java.time.LocalDate;

public class CamionFilter extends LogTransporteFilter<CamionFilter, Integer> {

	private static final long serialVersionUID = -2190328376063633377L;

	private int ccamion;
	
	private String placaveh;
	
	public CamionFilter() {
	}
	
	public CamionFilter(Integer ccamion, String placaveh, Integer calmacenamiento, String valmacenamiento, 
			Integer ctipoproducto, String vtipoproducto, 
			Integer ccliente, String vnombre, String vapellido, 
			LocalDate fregistro, String numguia) {
		super(calmacenamiento, valmacenamiento, ctipoproducto, vtipoproducto, ccliente, vnombre, vapellido, fregistro, numguia);
		
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
