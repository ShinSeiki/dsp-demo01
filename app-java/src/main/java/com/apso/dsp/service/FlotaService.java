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

import com.apso.dsp.model.Flota;

@Service
public class FlotaService extends LogTransporteService<Flota, Integer> {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private CommonDbService servDb;

	@Override
	protected CommonDbService getServDb() {
		return servDb;
	}

	@Override
	protected EntityManager getEM() {
		return em;
	}

	@Override
	protected String getEntityNotFoundMsg() {
		return "No se encontro la flota seleccionada.";
	}

	@Override
	public Class<Flota> getEntityCls() {
		return Flota.class;
	}

	@Override
	public Integer getEmptyID() {
		return 0;
	}

	@Override
	public Flota doNew() {
		Flota flota;
		
		flota = new Flota();
		flota.setCantidad(1);
		flota.setFregistro(LocalDateTime.now());
		flota.setFentrega(flota.getFregistro().plusDays(1));
		flota.setPrecioenvio(BigDecimal.ZERO);

		return flota;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public Flota doSave(Flota entity) {
		return super.doSave(entity);
	}

	@Override
	Flota doValUpdate(Flota entity) {
		Consumer<Flota> consumerDB;
		
		consumerDB = (db -> {
			db.setNumflota(entity.getNumflota());
		});
		return doValUpdate(entity, consumerDB);
	}

	@Override
	protected void doCommonsValidate(Flota entity) {
		super.doCommonsValidate(entity);
		
		validateNumflota(entity.getNumflota());
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public Flota doDelete(Integer centity) {
		return super.doDelete(centity);
	}

	private void validateNumflota(String numflota) {
		validateRegex("^[a-zA-Z]{3}\\d{4}[a-zA-Z]{1}$", numflota, "Placa invalida.");
	}
}
