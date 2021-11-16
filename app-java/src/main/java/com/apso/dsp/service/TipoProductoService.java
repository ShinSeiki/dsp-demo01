package com.apso.dsp.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.apso.dsp.exception.Demo01Exception;
import com.apso.dsp.model.TipoProducto;
import com.apso.dsp.util.StmProperty;

@Service
public class TipoProductoService extends BaseService<TipoProducto, Integer> {

	@SuppressWarnings({ "serial" })
	private static final Map<String, String> MAP_DELETE = Collections.unmodifiableMap(new HashMap<String, String>() {{
		put("Flota", "select count(*) from Flota f where f.ctipoproducto = :codigo");
		put("Camion", "select count(*) from Camion c where c.ctipoproducto = :codigo");
	}});
	
	@PersistenceContext
	private EntityManager em;

	@Override
	protected EntityManager getEM() {
		return em;
	}

	@Override
	protected String getEntityNotFoundMsg() {
		return "No se encontro el tipo de producto a modificar.";
	}

	@Override
	public Class<TipoProducto> getEntityCls() {
		return TipoProducto.class;
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
	public TipoProducto doNew() {
		TipoProducto tproducto;
		
		tproducto = new TipoProducto();
		tproducto.setCtipoproducto(0);
		tproducto.setVtipoproducto("");

		return tproducto;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public TipoProducto doSave(TipoProducto entity) {
		return super.doSave(entity);
	}

	@Override
	TipoProducto doValUpdate(TipoProducto entity) {
		TipoProducto db;
		
		db = em.find(TipoProducto.class, entity.getCtipoproducto());
		if (db == null)
			throw new Demo01Exception("No se encontro el tipo de producto a modificar.");
		
		doCommonsValidate(entity);
		
		db.setVtipoproducto(entity.getVtipoproducto());
		
		em.merge(db);
		return db;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public TipoProducto doDelete(Integer centity) {
		return super.doDelete(centity);
	}

	@Override
	void doCommonsValidate(TipoProducto entity) {
		final String JPQL = "select count(*) from TipoProducto tp where tp.vtipoproducto = :vtipoproducto and tp.ctipoproducto != :ctipoproducto";
		String vtipoproducto;
		long count;
		StmProperty prop;
		
		vtipoproducto = StringUtils.trim(entity.getVtipoproducto());
		if (StringUtils.isEmpty(vtipoproducto))
			throw new Demo01Exception("No se definio un nombre del tipo de producto.");
		
		prop = new StmProperty();
		prop.put("vtipoproducto", vtipoproducto);
		prop.put("ctipoproducto", entity.getCtipoproducto());
		
		count = jpaDefault.queryCount(em, JPQL, prop);
		if (count > 0)
			throw new Demo01Exception("El nombre del tipo producto esta duplicado.");
		
		entity.setVtipoproducto(vtipoproducto);
	}

}
