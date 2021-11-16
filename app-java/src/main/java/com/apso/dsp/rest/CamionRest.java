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

import com.apso.dsp.model.Camion;
import com.apso.dsp.model.filter.CamionFilter;
import com.apso.dsp.service.BaseService;
import com.apso.dsp.service.CamionService;
import com.apso.dsp.service.CommonDbService;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/camion")
public class CamionRest extends BaseRest<Camion, Integer> {

	@Autowired
	private CommonDbService servDb;
	
	@Autowired
	private CamionService servCamion;
	
	@GetMapping("/{ccamion}")
	@Override
	public ResponseEntity<Camion> getEntityOrNew(@PathVariable Integer ccamion) {
		return super.getEntityOrNew(ccamion);
	}
	
	@GetMapping
	public ResponseEntity<List<CamionFilter>> getAllCamiones(
			@RequestParam(name = "ccamion", required = false) Integer ccamion,
			@RequestParam(name = "cliente", required = false) String filterCliente,
			@RequestParam(name = "placa", required = false) String filterPlaca,
			@RequestParam(name = "numguia", required = false) String filterNumGuia
	) {
		List<CamionFilter> camiones;
		
		camiones = servDb.getCamiones(ccamion, filterCliente, 
				filterPlaca, filterNumGuia);
		return new ResponseEntity<>(camiones, HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Camion> doSave(@RequestBody Camion camion) {
		Camion respCamion;
		
		respCamion = servCamion.doSave(camion);
		return new ResponseEntity<>(respCamion, HttpStatus.OK);
	}

	@Override
	protected BaseService<Camion, Integer> getEntityService() {
		return servCamion;
	}

	@Override
	@DeleteMapping("/{centity}")
	public ResponseEntity<Camion> doDelete(@PathVariable Integer centity) {
		return super.doDelete(centity);
	}

}
