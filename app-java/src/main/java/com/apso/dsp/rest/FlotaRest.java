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

import com.apso.dsp.model.Flota;
import com.apso.dsp.model.filter.FlotaFilter;
import com.apso.dsp.service.BaseService;
import com.apso.dsp.service.CommonDbService;
import com.apso.dsp.service.FlotaService;

@RestController
@RequestMapping("/flota")
public class FlotaRest extends BaseRest<Flota, Integer> {

	@Autowired
	private CommonDbService servDb;
	
	@Autowired
	private FlotaService servFlota;
	
	@Override
	protected BaseService<Flota, Integer> getEntityService() {
		return servFlota;
	}

	@GetMapping("/{cflota}")
	@Override
	public ResponseEntity<Flota> getEntityOrNew(@PathVariable Integer cflota) {
		return super.getEntityOrNew(cflota);
	}
	
	@GetMapping
	public ResponseEntity<List<FlotaFilter>> getAllCamiones(
			@RequestParam(name = "cflota", required = false) Integer cflota,
			@RequestParam(name = "cliente", required = false) String filterCliente,
			@RequestParam(name = "flota", required = false) String filterFlota,
			@RequestParam(name = "numguia", required = false) String filterNumGuia
	) {
		List<FlotaFilter> flotas;
		
		flotas = servDb.getFlotas(cflota, filterCliente, 
				filterFlota, filterNumGuia);
		return new ResponseEntity<>(flotas, HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Flota> doSave(@RequestBody Flota flota) {
		Flota respFlota;
		
		respFlota = servFlota.doSave(flota);
		return new ResponseEntity<>(respFlota, HttpStatus.OK);
	}

	@Override
	@DeleteMapping("/{centity}")
	public ResponseEntity<Flota> doDelete(@PathVariable Integer centity) {
		return super.doDelete(centity);
	}
	
}
