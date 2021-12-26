package com.example.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.exceptions.SalespersonException;
import com.example.demo.models.Salesperson;
import com.example.demo.models.validations.SalespersonPOSTValidations;
import com.example.demo.models.validations.SalespersonPUTValidations;
import com.example.demo.services.SalespersonService;

@RestController
@Validated
public class SalespersonController {
	
	@Autowired
	SalespersonService ss;
	
	// find all
	@GetMapping(path = "api/salespeople")
	// Access-Control-Allow-Origin
	//@CrossOrigin(origins = "http://localhost:4200")
	public Iterable<Salesperson> getSalepeople() {
		return ss.findAll();
	}
	
	// gets all salespeople by spid 
	@GetMapping(path = "api/salespeople/{spid}")
	public Salesperson getSalesperson(@PathVariable String spid) {
		try {
			// returns the salesperson by spid 
			return ss.getSalesperson(spid);
		} catch (SalespersonException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	// add salesperson
	@Validated(SalespersonPOSTValidations.class)
	@PostMapping(path = "api/salespeople")
	public void addSalesperson(@Valid @RequestBody Salesperson s) {
		try {
			// save
			ss.save(s);
		} catch (SalespersonException e) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
		}
	}
	
	//update the salesperson by spid
	@Validated(SalespersonPUTValidations.class)
	@PutMapping(path = "api/salespeople/{spid}")
	public void updateSalesperson(@PathVariable String spid, @Valid @RequestBody Salesperson s) {
		try {
			ss.updateSales(spid, s);
		} catch (SalespersonException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	//deletes salesperson by spid
	@DeleteMapping(path = "api/salespeople/{spid}")
	public void deleteSalesperson(@PathVariable String spid) {
		try {
			// delete salesperson
			ss.deleteSalesperson(spid);
		} catch (SalespersonException e) {
			// throws exception if salesperson has orders and can't be deleted
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
		}
		
	}
	
}
