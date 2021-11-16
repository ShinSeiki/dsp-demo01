package com.apso.dsp.util;

public class BasicConstants {

	public static final String RES_CLIENTE_FILTER = "/com/apso/dsp/sql/cliente-filter.sql";
	
	public static final String SQL_CLIENTE_FILTER = BasicUtils.toStringResource(RES_CLIENTE_FILTER);
	
	public static final String MAP_CLIENTE_FILTER = "ClienteMapping";
	
	public static final String MAP_CAMION_FILTER = "CamionMapping";
	
	public static final String MAP_FLOTA_FILTER = "FlotaMapping";
	
	public static final String MAP_ALMACENAMIENTO_FILTER = "AlmacenamientoMapping";
	
	private BasicConstants() {
	}

}
