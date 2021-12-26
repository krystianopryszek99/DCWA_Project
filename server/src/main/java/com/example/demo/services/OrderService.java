package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.OrderException;
import com.example.demo.exceptions.SalespersonException;
import com.example.demo.models.Order;
import com.example.demo.models.Product;
import com.example.demo.models.Salesperson;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.SalespersonRepository;

@Service
public class OrderService {
	
	@Autowired
	OrderRepository or;
	
	@Autowired
	SalespersonRepository sr;
	
	@Autowired
	ProductRepository pr;
	
	public void save(Order o) throws OrderException {
		// strings that are equal to the .getSpid and .getPid methods
		String orderSpid = o.getOrderSalesperson().getSpid();
		String orderPid = o.getorderProduct().getPid();
		
		// find by spid and then checks in the if statment if it exists or not
		Optional<Salesperson> salesperson = sr.findBySpid(orderSpid);
		// find by pid and then checks in the if statment if it exists or not
		Optional<Product> product = pr.findByPid(orderPid);
		
		// Salesperson - if doesn't exists, throw a exception with error message "does not exist"
		if(salesperson.isEmpty()) {
			throw new OrderException("Salesperson " + o.getOrderSalesperson().getSpid() + " does not exist");
		}
		
		// Product - if doesn't exists, throw a exception with error message "does not exist"
		if(product.isEmpty()) {
			throw new OrderException("Product " + o.getorderProduct().getPid() + " does not exist");
		}
		
		// if the product is not orderable
		if(product.get().getOrderable().equals("false")) {
			throw new OrderException("Product " + o.getorderProduct().getPid() + " is not orderable");
		}
		
		// If the product exists and is orderable, but the quantity is less than the order Quantity
		if(product.get().getQuantity() < o.getOrderQuantity()) {
			throw new OrderException("Stock on hand: " + product.get().getQuantity() + ", less than quantity: " + o.getOrderQuantity()); 
		}
		
		// try for when the product exists or doesn't - if already exists a exception will be thrown with the below message
		try {
			// sets the new qty
			int newOty = product.get().getQuantity() - o.getOrderQuantity();
			product.get().setQuantity(newOty);
			// save
			or.save(product.get());
		} catch(DataIntegrityViolationException e) {
			// message returned if it already exists 
			throw new OrderException("Order " + o.getOid() + " already exist");
		}
		
	}
	
	// finds all Orders 
	public Iterable<Order> findAll() {
		return or.findAll();
	}
	
	// when specific date and qty is entered, it will return that specific data and qty specified 
	public List<Order> findOrdersByOrderDateContainsAndOrderQuantityGreaterThan(int orderDate, int orderQuantity){
		return or.findOrdersByOrderDateContainsAndOrderQuantityGreaterThan(orderDate, orderQuantity);
	}
	
}
