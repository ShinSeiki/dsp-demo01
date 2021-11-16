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

import com.apso.dsp.model.Cliente;
import com.apso.dsp.service.BaseService;
import com.apso.dsp.service.ClienteService;
import com.apso.dsp.service.CommonDbService;

@RestController
@RequestMapping("/cliente")
public class ClienteRest extends BaseRest<Cliente, Integer> {

	@Autowired
	private ClienteService servCliente;
	
	@Autowired
	private CommonDbService servDb;
	
	@Override
	protected BaseService<Cliente, Integer> getEntityService() {
		return servCliente;
	}

	@GetMapping("/{ccliente}")
	@Override
	public ResponseEntity<Cliente> getEntityOrNew(@PathVariable Integer ccliente) {
		return super.getEntityOrNew(ccliente);
	}

	@GetMapping
	public ResponseEntity<List<Cliente>> getClientes(
			@RequestParam(name = "ccliente", required = false) Integer ccliente, 
			@RequestParam(name = "filter", required = false) String filter) 
	{
		List<Cliente> clientes;
		
		clientes = servDb.getClientes(ccliente, filter);
		return new ResponseEntity<>(clientes, HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> doSave(@RequestBody Cliente cliente) {
		Cliente respCliente;
		
		respCliente = servCliente.doSave(cliente);
		return new ResponseEntity<>(respCliente, HttpStatus.OK);
	}

	
	@Override
	@DeleteMapping("/{ccliente}")
	public ResponseEntity<Cliente> doDelete(@PathVariable Integer ccliente) {
		return super.doDelete(ccliente);
	}

}
