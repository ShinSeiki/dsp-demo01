package com.apso.dsp.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.apso.dsp.exception.FieldInvalidException;
import com.apso.dsp.model.Almacenamiento;
import com.apso.dsp.model.TipoRegion;

@Service
public class AlmacenamientoService extends BaseService<Almacenamiento, Integer> {

	@SuppressWarnings({ "serial" })
	private static final Map<String, String> MAP_DELETE = Collections.unmodifiableMap(new HashMap<String, String>() {{
		put("Flota", "select count(*) from Flota f where f.calmacenamiento = :codigo");
		put("Camion", "select count(*) from Camion c where c.calmacenamiento = :codigo");
	}});
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private CommonDbService servDb;
	
	@Override
	protected EntityManager getEM() {
		return em;
	}

	@Override
	protected String getEntityNotFoundMsg() {
		return "No se encontro el almacenamiento seleccionado.";
	}

	@Override
	public Class<Almacenamiento> getEntityCls() {
		return Almacenamiento.class;
	}

	@Override
	public Integer getEmptyID() {
		return 0;
	}

	@Override
	protected Map<String, String> getQueryExistsDel() {
		return MAP_DELETE;
	}

	@Override
	public Almacenamiento doNew() {
		Almacenamiento alm;
		
		alm = new Almacenamiento();
		alm.setTregion(TipoRegion.Nacional);

		return alm;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public Almacenamiento doSave(Almacenamiento entity) {
		return super.doSave(entity);
	}

	@Override
	Almacenamiento doValUpdate(Almacenamiento entity) {
		Consumer<Almacenamiento> consumerDB;
		
		
		consumerDB = (db -> {
			db.setTalmacenamiento(entity.getTalmacenamiento());
			db.setTregion(entity.getTregion());
			db.setValmacenamiento(entity.getValmacenamiento());
			db.setVdireccion(entity.getVdireccion());
		});
		return doValUpdate(entity, consumerDB);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public Almacenamiento doDelete(Integer centity) {
		return super.doDelete(centity);
	}

	@Override
	void doCommonsValidate(Almacenamiento almacenamiento) {
		String valmacenamiento;
		String vdireccion;
		
		valmacenamiento = StringUtils.trim(almacenamiento.getValmacenamiento());
		vdireccion = StringUtils.trim(almacenamiento.getVdireccion());
		
		if (StringUtils.isEmpty(valmacenamiento))
			throw new FieldInvalidException("Nombre invalido");
		if (StringUtils.isEmpty(vdireccion))
			throw new FieldInvalidException("Direccion invalido");
		if (almacenamiento.getTalmacenamiento() == null)
			throw new FieldInvalidException("No se definio el tipo de almacenamiento.");
		if (almacenamiento.getTregion() == null)
			throw new FieldInvalidException("No se definio la region del almacenamiento.");
		
		if (servDb.existsAlmacenamiento(almacenamiento.getCalmacenamiento(), almacenamiento.getTalmacenamiento(), almacenamiento.getTregion(), almacenamiento.getValmacenamiento()))
			throw new FieldInvalidException("Nombre duplicado.");

		almacenamiento.setValmacenamiento(valmacenamiento);
		almacenamiento.setVdireccion(vdireccion);
	}

}
