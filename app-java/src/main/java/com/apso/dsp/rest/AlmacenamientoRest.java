package com.apso.dsp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apso.dsp.model.Almacenamiento;
import com.apso.dsp.model.TipoAlmacenamiento;
import com.apso.dsp.model.TipoRegion;
import com.apso.dsp.service.AlmacenamientoService;
import com.apso.dsp.service.BaseService;
import com.apso.dsp.service.CommonDbService;

@RestController
@RequestMapping("/almacenamiento")
public class AlmacenamientoRest extends BaseRest<Almacenamiento, Integer> {

	@Autowired
	private AlmacenamientoService servAlmacenamiento;
	@Autowired
	private CommonDbService servDb;
	
	@Override
	protected BaseService<Almacenamiento, Integer> getEntityService() {
		return servAlmacenamiento;
	}

	@GetMapping("/{calmacenamiento}")
	public ResponseEntity<Almacenamiento> getEntityOrNew(
			@PathVariable Integer calmacenamiento,
			@RequestParam(name = "tipo", required = false) TipoAlmacenamiento talmacenamiento
	) {
		Almacenamiento alm;
		ResponseEntity<Almacenamiento> resp;
		
		resp = getEntityOrNew(calmacenamiento);
		alm = resp.getBody();
		if (alm.getCalmacenamiento() == 0)
			alm.setTalmacenamiento(talmacenamiento);
		return resp;
	}
	
	@GetMapping
	public ResponseEntity<List<Almacenamiento>> getAlmacenamientos(
			@RequestParam(name = "calmacenamiento") Integer calmacenamiento, 
			@RequestParam(name = "tipo") TipoAlmacenamiento talmacenamiento,
			@RequestParam(name = "region", required = false) TipoRegion tregion,
			@RequestParam(name = "filter", required = false) String filter
	) {
		List<Almacenamiento> alms;
		
		alms = servDb.getAlmacenamientos(calmacenamiento, filter, talmacenamiento, tregion);
		return new ResponseEntity<>(alms, HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Almacenamiento> doSave(@RequestBody Almacenamiento alm) {
		Almacenamiento result;
		
		result = servAlmacenamiento.doSave(alm);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Override
	@DeleteMapping("/{centity}")
	public ResponseEntity<Almacenamiento> doDelete(@PathVariable Integer centity) {
		return super.doDelete(centity);
	}
	
}
