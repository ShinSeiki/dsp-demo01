package com.apso.dsp.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.apso.dsp.model.Almacenamiento;
import com.apso.dsp.model.Cliente;
import com.apso.dsp.model.TipoAlmacenamiento;
import com.apso.dsp.model.TipoProducto;
import com.apso.dsp.model.TipoRegion;
import com.apso.dsp.model.filter.CamionFilter;
import com.apso.dsp.model.filter.FlotaFilter;
import com.apso.dsp.util.BasicConstants;
import com.apso.dsp.util.BasicUtils;
import com.apso.dsp.util.StmProperty;

@Service
public class CommonDbService {

	@PersistenceContext
	private EntityManager em;
	
	private DefaultJpaController jpaDefault;
	
	@PostConstruct
	protected void doInit() {
		jpaDefault = new DefaultJpaController();
	}
	
	public List<Cliente> getClientes(Integer ccliente, String filter) {
		StmProperty prop;
		List<Cliente> clientes;
		
		ccliente = ccliente == null ? 0 : ccliente;
		if (StringUtils.isEmpty(StringUtils.trim(filter)))
			filter = "";
		
		filter = jpaDefault.prepareSearchStringEmpty(filter);
		
		prop = new StmProperty();
		prop.put("ccliente", ccliente);
		prop.put("filter", filter);
		
		clientes = jpaDefault.execNativeQuery(em, BasicConstants.SQL_CLIENTE_FILTER, BasicConstants.MAP_CLIENTE_FILTER, Cliente.class, prop);
		
		return clientes;
	}
	
	public List<TipoProducto> getTipoProductos() {
		final String JPQL = "select tp from TipoProducto tp order by tp.vtipoproducto";
		List<TipoProducto> tprods;
		
		tprods = jpaDefault.execQuery(em, JPQL, new StmProperty(), TipoProducto.class);
		return tprods;
	}

	public boolean existsCTipoProducto(Integer ctipoprod) {
		final String JQPL = "select count(*) from TipoProducto tp where tp.ctipoproducto = :ctipoproducto";
		StmProperty prop;
		long count;
		
		ctipoprod = ctipoprod != null ? ctipoprod : 0;
		
		prop = new StmProperty();
		prop.put("ctipoproducto", ctipoprod);
		
		count = jpaDefault.queryCount(em, JQPL, prop);
		return count > 0;
	}
	
	public boolean existsCCliente(Integer ccliente) {
		final String JPQL = "select count(*) from Cliente c where c.ccliente = :ccliente";
		StmProperty prop;
		long count;
		
		ccliente = ccliente != null ? ccliente : 0;
		
		prop = new StmProperty();
		prop.put("ccliente", ccliente);
		
		count = jpaDefault.queryCount(em, JPQL, prop);
		return count > 0;
	}
	
	public boolean existsCAlmacenamiento(Integer calmacenamiento, TipoAlmacenamiento talmacenamiento) {
		final String JPQL = "select count(a) from Almacenamiento a where a.calmacenamiento = :calmacenamiento and a.talmacenamiento = :talmacenamiento";
		StmProperty prop;
		long count;
		
		calmacenamiento = calmacenamiento != null ? calmacenamiento : 0;
		
		prop = new StmProperty();
		prop.put("calmacenamiento", calmacenamiento);
		prop.put("talmacenamiento", talmacenamiento);
		
		count = jpaDefault.queryCount(em, JPQL, prop);
		return count > 0;
	}
	
	public List<CamionFilter> getCamiones(Integer ccamion, String filterCliente, 
			String filterPlaca, String filterNumGuia) {
		StmProperty prop;
		List<CamionFilter> rows;
		String jpql;
		
		ccamion = ccamion != null ? ccamion : 0;
		filterCliente = StringUtils.trim(filterCliente);
		filterPlaca = StringUtils.trim(filterPlaca);
		filterNumGuia = StringUtils.trim(filterNumGuia);
		
		filterCliente = jpaDefault.prepareSearchStringEmpty(filterCliente);
		filterPlaca = jpaDefault.prepareSearchStringEmpty(filterPlaca);
		filterNumGuia = jpaDefault.prepareSearchStringEmpty(filterNumGuia);
		
		prop = new StmProperty();
		prop.put("ccamion", ccamion);
		prop.put("filterCliente", filterCliente);
		prop.put("filterPlaca", filterPlaca);
		prop.put("filterNumguia", filterNumGuia);
		
		jpql = BasicUtils.getSqlFromNamedQuery(em, BasicConstants.MAP_CAMION_FILTER);
		rows = jpaDefault.execNativeQuery(em, jpql, 
				BasicConstants.MAP_CAMION_FILTER, CamionFilter.class, prop);
		return rows;
	}
	
	public List<FlotaFilter> getFlotas(Integer cflota, String filterCliente, 
			String filterFlota, String filterNumGuia) {
		StmProperty prop;
		List<FlotaFilter> rows;
		String jpql;
		
		cflota = cflota != null ? cflota : 0;
		filterCliente = StringUtils.trim(filterCliente);
		filterFlota = StringUtils.trim(filterFlota);
		filterNumGuia = StringUtils.trim(filterNumGuia);
		
		filterCliente = jpaDefault.prepareSearchStringEmpty(filterCliente);
		filterFlota = jpaDefault.prepareSearchStringEmpty(filterFlota);
		filterNumGuia = jpaDefault.prepareSearchStringEmpty(filterNumGuia);
		
		prop = new StmProperty();
		prop.put("cflota", cflota);
		prop.put("filterCliente", filterCliente);
		prop.put("filterFlota", filterFlota);
		prop.put("filterNumguia", filterNumGuia);
		
		jpql = BasicUtils.getSqlFromNamedQuery(em, BasicConstants.MAP_FLOTA_FILTER);
		rows = jpaDefault.execNativeQuery(em, jpql, 
				BasicConstants.MAP_FLOTA_FILTER, FlotaFilter.class, prop);
		return rows;
	}
	
	public boolean existsAlmacenamiento(Integer calmacenamientoExcl, TipoAlmacenamiento talmacenamiento, TipoRegion tregion, String valmacenamiento) {
		final String JPQL = "select count(*) from Almacenamiento a where a.calmacenamiento != :calmacenamiento and a.talmacenamiento = :talmacenamiento and a.tregion = :tregion and a.valmacenamiento = :valmacenamiento";
		StmProperty prop;
		long count;
		
		prop = new StmProperty();
		prop.put("calmacenamiento", calmacenamientoExcl);
		prop.put("talmacenamiento", talmacenamiento);
		prop.put("tregion", tregion);
		prop.put("valmacenamiento", valmacenamiento);
		
		count = jpaDefault.queryCount(em, JPQL, prop);
		return count > 0;
	}
	
	public List<Almacenamiento> getAlmacenamientos(Integer calmacenamiento, String filter, TipoAlmacenamiento talmacenamiento, TipoRegion tregion) {
		List<Almacenamiento> rows;
		StmProperty prop;
		String jpql;
		
		calmacenamiento = calmacenamiento != null ? calmacenamiento : 0;
		filter = StringUtils.trim(filter);
		filter = jpaDefault.prepareSearchStringEmpty(filter);
		
		prop = new StmProperty();
		prop.put("calmacenamiento", calmacenamiento);
		prop.put("talmacenamiento", talmacenamiento);
		prop.put("tregion", tregion);
		prop.put("filter", filter);
		
		jpql = BasicUtils.getSqlFromNamedQuery(em, BasicConstants.MAP_ALMACENAMIENTO_FILTER);
		rows = jpaDefault.execQuery(em, jpql, prop, Almacenamiento.class);
		
		return rows;
	}
	
}
