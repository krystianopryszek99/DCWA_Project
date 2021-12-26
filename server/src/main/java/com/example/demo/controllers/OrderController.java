package com.example.demo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.exceptions.OrderException;
import com.example.demo.exceptions.SalespersonException;
import com.example.demo.models.Order;
import com.example.demo.models.Salesperson;
import com.example.demo.models.validations.OrderPOSTValidations;
import com.example.demo.models.validations.SalespersonPUTValidations;
import com.example.demo.services.OrderService;

@RestController
@Validated
public class OrderController {
	
	@Autowired
	OrderService os;
	
	// Post method for orders
	@Validated(OrderPOSTValidations.class)
	@PostMapping(path = "/api/orders")
	public void addOrder(@Valid @RequestBody Order o) throws OrderException {
		try {
			os.save(o);
		} catch (OrderException e) {
			// throws exception 422
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
		}
	}
	
	// gets all orders on that specific path 
	@GetMapping(path = "/api/orders")
	public Iterable<Order> getOrders() {
		return os.findAll();
	}
	
	// get on the specific orders that return the data by year and qty
	@GetMapping(path = "api/specificOrders")
	public List<Order> specificOrders(@RequestParam("year") int orderDate, @RequestParam("qty") int orderQuantity){
		return os.findOrdersByOrderDateContainsAndOrderQuantityGreaterThan(orderDate, orderQuantity);
	}
	

}
