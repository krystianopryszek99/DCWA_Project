package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Salesperson;

public interface SalespersonRepository extends CrudRepository<Salesperson, Integer> {

	// find by spid
	Optional<Salesperson> findBySpid(String spid);
	
	// delete by spid
	Optional<Salesperson> deleteBySpid(String spid);

}
