package com.apso.dsp.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.apso.dsp.model.BaseModel;
import com.apso.dsp.service.BaseService;

public abstract class BaseRest<T extends BaseModel<T, ID>, ID> {

	protected abstract BaseService<T, ID> getEntityService();
	
	public ResponseEntity<T> getEntityOrNew(ID centity) {
		T entity;
		
		if (centity == null)
			centity = getEntityService().getEmptyID();
		if (centity.equals(getEntityService().getEmptyID()))
			entity = getEntityService().doNew();
		else
			entity = getEntityService().doLoad(centity);
		
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	public ResponseEntity<T> doDelete(ID centity) {
		T entity;
		
		entity = getEntityService().doDelete(centity);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}

}
