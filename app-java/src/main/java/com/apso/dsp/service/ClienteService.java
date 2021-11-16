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
import com.apso.dsp.model.Cliente;
import com.apso.dsp.util.StmProperty;

@Service
public class ClienteService extends BaseService<Cliente, Integer> {

	@SuppressWarnings({ "serial" })
	private static final Map<String, String> MAP_DELETE = Collections.unmodifiableMap(new HashMap<String, String>() {{
		put("Flota", "select count(*) from Flota f where f.ccliente = :codigo");
		put("Camion", "select count(*) from Camion c where c.ccliente = :codigo");
	}});
	
	@PersistenceContext
	private EntityManager em;

	@Override
	protected String getEntityNotFoundMsg() {
		return "No se encontro el cliente a modificar.";
	}

	@Override
	protected EntityManager getEM() {
		return em;
	}

	@Override
	public Class<Cliente> getEntityCls() {
		return Cliente.class;
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
	public Cliente doNew() {
		Cliente cliente;
		
		cliente = new Cliente();
		cliente.setVapellido("");
		cliente.setVnombre("");

		return cliente;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public Cliente doSave(Cliente entity) {
		return super.doSave(entity);
	}

	@Override
	protected Cliente doValUpdate(Cliente entity) {
		Cliente db;
		
		db = em.find(Cliente.class, entity.getCcliente());
		if (db == null)
			throw new Demo01Exception("No se encontro el cliente a modificar.");
		
		doCommonsValidate(entity);
		
		db.setVapellido(entity.getVapellido());
		db.setVnombre(entity.getVnombre());
		
		em.merge(db);
		return db;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public Cliente doDelete(Integer centity) {
		return super.doDelete(centity);
	}

	@Override
	void doCommonsValidate(Cliente entity) {
		final String JPQL = "select count(c) from Cliente c where c.vapellido = :vapellido and c.vnombre = :vnombre and c.ccliente != :ccliente";
		String vapellido;
		String vnombre;
		StmProperty prop;
		long count;
		
		vapellido = entity.getVapellido();
		vnombre = entity.getVnombre();
		
		vnombre = StringUtils.trim(vnombre);
		vapellido = StringUtils.trim(vapellido);

		if (StringUtils.isEmpty(vnombre))
			throw new Demo01Exception("El nombre del cliente esta vacio.");
		if (StringUtils.isEmpty(vapellido))
			throw new Demo01Exception("El apellido del cliente esta vacio.");
		
		prop = new StmProperty();
		prop.put("vapellido", vapellido);
		prop.put("vnombre", vnombre);
		prop.put("ccliente", entity.getCcliente());
		
		count = jpaDefault.queryCount(em, JPQL, prop);
		if (count > 0)
			throw new Demo01Exception("Nombre y Apellido Duplicado.");
		
		entity.setVapellido(vapellido);
		entity.setVnombre(vnombre);
	}
	
}
