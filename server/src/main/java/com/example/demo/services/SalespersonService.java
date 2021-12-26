package com.example.demo.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.SalespersonException;
import com.example.demo.models.Salesperson;
import com.example.demo.repositories.SalespersonRepository;

@Service
public class SalespersonService {

	@Autowired
	SalespersonRepository sr;
	
	// get salesperson
	public Salesperson getSalesperson (String spid) throws SalespersonException {
		// find the salesperson by spid 
		Optional<Salesperson> sp = sr.findBySpid(spid);
		// checks if it's empty
		// when empty the exception will be thorw with the below message 
		if(sp.isEmpty()) {
			throw new SalespersonException("Salesperson " + spid + " not found");
		}
			// gets the salesperson
			return sp.get(); 	
		}	
	
	// update salesperson
	public void updateSales(String spid, Salesperson s) throws SalespersonException {
		// find the salesperson by spid 
		Optional<Salesperson> sp = sr.findBySpid(spid);
		// checks if it's empty
		// when empty the exception will be thorw with the below message 
		if (sp.isEmpty()) {
			throw new SalespersonException("Salesperson " + spid + " not found");
		}
		// gets the salesperson 
		sp.get().setName(s.getName());
		// saves
		sr.save(sp.get());
	}
	
	// save salesperson
	public void save(Salesperson s) throws SalespersonException {
		try {
			// save salesperson
			sr.save(s);
		} catch (DataIntegrityViolationException e) {
			// if the salesperson already exists this message will be thrown
			throw new SalespersonException("Salesperson " + s.getSpid() + " already exists");
		}
	}
	
	// finds all salespeople 
	public Iterable<Salesperson> findAll() {
		return sr.findAll();
	}
	
	// delete salesperson
	public void deleteSalesperson(String spid) throws SalespersonException {
		// finds the salesperson by spid 
		Optional<Salesperson> sp = sr.findBySpid(spid);
		try {
			// deletes the salesperson
			sr.delete(sp.get());	
		} catch (DataIntegrityViolationException e) {
			// throws exception of the salesperson has orders and can't be deleted
			throw new SalespersonException("Salesperson " + spid + " can't be deleted. He/she has orders.");
		}
		
	}

}
