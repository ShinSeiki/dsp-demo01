package com.apso.dsp.util;

import java.io.IOException;
import java.net.URL;

import javax.persistence.EntityManager;

import org.apache.commons.io.IOUtils;
import org.hibernate.query.Query;

public class BasicUtils {

	public static final String DEF_CHARSET = "UTF-8";
	
	public BasicUtils() {
		// TODO Auto-generated constructor stub
	}

	public static String toStringResource(URL resource) {
		String data = null;
		
		try {
			data = IOUtils.toString(resource, DEF_CHARSET);
		} catch (IOException e) {
		}
		return data;
	}
	
	public static String toStringResource(String resource) {
		String data = null;
		URL url;
		
		url = BasicUtils.class.getResource(resource);
		data = toStringResource(url);

		return data;
	}
	
	public static String getSqlFromNamedQuery(EntityManager em, String namedQuery) {
		String sql;
		
		sql = em.createNamedQuery(namedQuery).unwrap(Query.class).getQueryString();
		return sql;
	}
	
}
