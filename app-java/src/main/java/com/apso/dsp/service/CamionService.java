package com.apso.dsp.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.apso.dsp.model.Camion;

@Service
public class CamionService extends LogTransporteService<Camion, Integer> {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private CommonDbService servDb;
	
	@Override
	protected String getEntityNotFoundMsg() {
		return "No se encontro el camion seleccionado.";
	}

	@Override
	protected EntityManager getEM() {
		return em;
	}

	@Override
	protected CommonDbService getServDb() {
		return servDb;
	}

	@Override
	public Class<Camion> getEntityCls() {
		return Camion.class;
	}

	@Override
	public Integer getEmptyID() {
		return 0;
	}

	@Override
	public Camion doNew() {
		Camion camion;
		
		camion = new Camion();
		camion.setCantidad(1);
		camion.setFregistro(LocalDateTime.now());
		camion.setFentrega(camion.getFregistro().plusDays(1));
		camion.setPrecioenvio(BigDecimal.ZERO);

		return camion;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public Camion doSave(Camion entity) {
		return super.doSave(entity);
	}

	@Override
	protected Camion doValUpdate(Camion entity) {
		Consumer<Camion> consumerDB;
		
		consumerDB = (db -> {
			db.setPlacaveh(entity.getPlacaveh());
		});
		return doValUpdate(entity, consumerDB);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public Camion doDelete(Integer centity) {
		return super.doDelete(centity);
	}

	@Override
	protected void doCommonsValidate(Camion entity) {
		super.doCommonsValidate(entity);
		
		validatePlaca(entity.getPlacaveh());
	}

	private void validatePlaca(String placaveh) {
		validateRegex("^[a-zA-Z]{3}\\d{3}$", placaveh, "Placa invalida.");
	}
	
}
