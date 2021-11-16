package com.apso.dsp.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;

import com.apso.dsp.exception.Demo01Exception;
import com.apso.dsp.exception.EntityNotFoundException;
import com.apso.dsp.exception.EntityUsedException;
import com.apso.dsp.model.BaseModel;
import com.apso.dsp.util.StmProperty;

public abstract class BaseService<T extends BaseModel<T, ID>, ID> {

	public static final Map<String, String> EMPTY_DEL = Collections.unmodifiableMap(new HashMap<String, String>());
	
	protected DefaultJpaController jpaDefault = new DefaultJpaController();

	protected abstract EntityManager getEM();
	
	protected abstract String getEntityNotFoundMsg();
	
	protected T findEntityWithException(ID centity) {
		T entity;
		
		entity = getEM().find(getEntityCls(), centity);
		if (entity == null)
			throw new EntityNotFoundException(getEntityNotFoundMsg());
		
		return entity;
	}
	
	public abstract Class<T> getEntityCls();
	
	public abstract ID getEmptyID();
	
	public abstract T doNew();
	
	public T doLoad(ID centity) {
		T entity;
		
		entity = getEM().find(getEntityCls(), centity);
		if (entity == null)
			throw new EntityNotFoundException("No se encontro la entidad seleccionada.");
		
		return entity;
	}
	
	public T doSave(T entity) {
		ID centity;
		T entityNew;
		
		if (entity == null)
			throw new Demo01Exception("No definio la entidad a modificar.");
		
		centity = entity.getID();
		if (centity == null || getEmptyID().equals(centity))
			entityNew = doValSave(entity);
		else
			entityNew = doValUpdate(entity);
		
		return entityNew;
	}
	
	protected T doValSave(T entity) {
		doCommonsValidate(entity);
		
		getEM().persist(entity);
		return entity;
	}
	
	abstract T doValUpdate(T entity);
	
	protected T doValUpdate(T entity, Consumer<T> consumerDB) {
		T db;
		
		db = findEntityWithException(entity.getID());
		doCommonsValidate(entity);
		
		consumerDB.accept(db);
		
		getEM().merge(db);
		return db;
	}
	
	public T doDelete(ID centity) {
		T entity;
		String msgExists;
		
		entity = doLoad(centity);
		
		msgExists = checkUsed(centity);
		if (!StringUtils.isEmpty(msgExists)) {
			throw new EntityUsedException(String.format("El registro actualmente esta siendo utilizado por: %s.", msgExists));
		}
		
		getEM().remove(entity);
		return entity;
	}
	
	protected String checkUsed(ID centity) {
		StmProperty prop;
		Map<String, String> queryDel;
		long count;
		
		prop = new StmProperty();
		
		queryDel = getQueryExistsDel();
		
		for (Entry<String, String> entry: queryDel.entrySet()) {
			prop.clear();
			
			prop.put("codigo", centity);
			
			count = jpaDefault.queryCount(getEM(), entry.getValue(), prop);
			if (count > 0)
				return entry.getKey();
		}
		
		return null;
	}
	
	protected Map<String, String> getQueryExistsDel() {
		return EMPTY_DEL;
	}
	
	abstract void doCommonsValidate(T entity);
	
}
