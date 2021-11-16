package com.apso.dsp.service;

import java.util.List;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import com.apso.dsp.util.StmProperty;

public interface ITransactionService {

	default <U> List<U> execQuery(EntityManager em, String query, StmProperty params, 
			Class<U> cls) 
	{
		return execQuery(em, query, params, cls, -1, -1);
	}
	
	default <U> List<U> execQuery(EntityManager em, String query, StmProperty params, 
			Class<U> cls, int start, int limit) 
	{
		TypedQuery<U> q;
		List<U> list = null;
		
		q = em.createQuery(query, cls);
		
		if (params != null) {
			for (Entry<String, Object> entry: params.entrySet()) {
				q = q.setParameter(entry.getKey(), entry.getValue());
			}
		}

		if (start >= 0) {
			q = q.setFirstResult(start).setMaxResults(limit);
		}
		list = q.getResultList();

		return list;
	}
	
	default <U> U execSingleQuery(EntityManager em, String query, 
			StmProperty params, Class<U> cls) {
		TypedQuery<U> q;
		List<U> values;
		U value = null;
		
		q = em.createQuery(query, cls);
		
		if (params != null) {
			for (Entry<String, Object> entry: params.entrySet()) {
				q = q.setParameter(entry.getKey(), entry.getValue());
			}
		}

		q = q.setMaxResults(1);
		
		values = q.getResultList();
		for (U val: values) {
			value = val;
		}

		return value;
	}
	
	default long queryCount(EntityManager em, final String JPQL, StmProperty prop) {
		Long count;
		
		count = execSingleQuery(em, JPQL, prop, Long.class);
		count = count != null ? count : 0l;
		
		return count;
	}
	
	default <T> List<T> execNativeQuery(EntityManager em, String sql, String rsMapping, Class<T> cls, StmProperty params) {
		return execNativeQuery(em, sql, rsMapping, cls, params, -1, -1);
	}
	
	@SuppressWarnings("unchecked")
	default <T> List<T> execNativeQuery(EntityManager em, String sql, String rsMapping, Class<T> cls, StmProperty params, int start, int limit) {
		Query q;
		List<T> list;
		
		if (StringUtils.isEmpty(rsMapping))
			q = em.createNativeQuery(sql);
		else
			q = em.createNativeQuery(sql, rsMapping);
		if (params != null) {
			for (Entry<String, Object> entry: params.entrySet()) {
				q = q.setParameter(entry.getKey(), entry.getValue());
			}
		}
		if (start >= 0) {
			q = q.setFirstResult(start).setMaxResults(limit);
		}
		list = q.getResultList();
		return list;
	}
	
	default String prepareSearchString(String field) {
		if (StringUtils.isEmpty(field))
			return null;
		if (field.startsWith("\"") && field.length() > 1) {
			field = field.substring(1);
		} else 
			field = String.format("%%%s", field);
			
		if (field.endsWith("\"") && field.length() > 1) {
			field = field.substring(0, field.length() - 1);
		} else
			field = String.format("%s%%", field);
	
		return field;
	}
	
	default String prepareSearchStringEmpty(String field) {
		field = prepareSearchString(field);
		if (field == null)
			field = "";
		
		return field;
	}
	
}
