package com.apso.dsp.service;

import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.apso.dsp.exception.EntityNotFoundException;
import com.apso.dsp.exception.FieldInvalidException;
import com.apso.dsp.model.LogTransporte;

public abstract class LogTransporteService<T extends LogTransporte<T, ID>, ID> extends BaseService<T, ID> {

	protected abstract CommonDbService getServDb();
	
	@Override
	protected T doValUpdate(T entity, Consumer<T> consumerDB) {
		Consumer<T> superDB;
		
		superDB = (db -> {
			db.setCantidad(entity.getCantidad());
			db.setCcliente(entity.getCcliente());
			db.setCtipoproducto(entity.getCtipoproducto());
			db.setFentrega(entity.getFentrega());
			db.setFregistro(entity.getFregistro());
			db.setNumguia(entity.getNumguia());
			db.setPrecioenvio(entity.getPrecioenvio());
			
			consumerDB.accept(db);
		});
		return super.doValUpdate(entity, superDB);
	}

	@Override
	protected void doCommonsValidate(T entity) {
		Integer ctipoprod;
		Integer ccliente;
		
		ctipoprod = entity.getCtipoproducto();
		ccliente = entity.getCcliente();
		
		if (!getServDb().existsCTipoProducto(ctipoprod))
			throw new EntityNotFoundException("No se encontro el tipo de producto.");
		if (!getServDb().existsCCliente(ccliente))
			throw new EntityNotFoundException("No se encontro el cliente.");
		
		validateGuia(entity.getNumguia());
		
		if (entity.getCantidad() < 1)
			throw new FieldInvalidException("La cantidad debe ser mayor a 0.");
	}
	
	protected void validateGuia(String numguia) {
		validateRegex("^\\w{10}$", numguia, "Guia invalida.");
	}
	
	protected void validateRegex(String regex, String value, String errMsg) {
		Pattern p;
		Matcher m;
		
		if (value == null)
			value = "";
		
		p = Pattern.compile(regex);
		m = p.matcher(value);
		
		if (!m.matches())
			throw new FieldInvalidException(errMsg);
	}

}
