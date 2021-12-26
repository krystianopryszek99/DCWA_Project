package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Order;
import com.example.demo.models.Product;
import com.example.demo.models.Salesperson;

public interface OrderRepository extends CrudRepository<Order, Integer> {

	//  find by date and qty 
	public List<Order> findOrdersByOrderDateContainsAndOrderQuantityGreaterThan(int orderDate, int orderQuantity);
	
	// save product
	public void save(Product product);
}
