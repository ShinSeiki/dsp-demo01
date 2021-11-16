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
import org.springframework.web.bind.annotation.RestController;

import com.apso.dsp.model.TipoProducto;
import com.apso.dsp.service.BaseService;
import com.apso.dsp.service.CommonDbService;
import com.apso.dsp.service.TipoProductoService;

@RestController
@RequestMapping("/tipoproducto")
public class TipoProductoRest extends BaseRest<TipoProducto, Integer> {

	@Autowired
	private TipoProductoService servTipoProd;
	@Autowired
	private CommonDbService servDb;

	@Override
	protected BaseService<TipoProducto, Integer> getEntityService() {
		return servTipoProd;
	}

	@GetMapping("/{ctipoprod}")
	@Override
	public ResponseEntity<TipoProducto> getEntityOrNew(@PathVariable Integer ctipoprod) {
		return super.getEntityOrNew(ctipoprod);
	}

	@GetMapping
	public ResponseEntity<List<TipoProducto>> getTipoProductos() {
		List<TipoProducto> tps;
		
		tps = servDb.getTipoProductos();
		return new ResponseEntity<>(tps, HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TipoProducto> doSave(@RequestBody TipoProducto tp) {
		TipoProducto result;
		
		result = servTipoProd.doSave(tp);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Override
	@DeleteMapping("/{centity}")
	public ResponseEntity<TipoProducto> doDelete(@PathVariable Integer centity) {
		return super.doDelete(centity);
	}
	
}
