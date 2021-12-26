package com.example.demo.models;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.example.demo.models.validations.SalespersonPOSTValidations;
import com.example.demo.models.validations.SalespersonPUTValidations;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Salesperson {
	@Id		
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true)
	@NotNull(message = "spid must be provided", groups = SalespersonPOSTValidations.class) // spid must be provided for the add
	@Null(message= "spid must not be provided", groups = SalespersonPUTValidations.class) // spid must not be provided for the update
	private String spid;
	@NotNull(message = "name must be provided", groups = SalespersonPOSTValidations.class) // name must be provided for the add
	@NotNull(message = "name must be provided", groups = SalespersonPUTValidations.class) // name must not be provided for the update
	private String name;
	@OneToMany(mappedBy = "orderSalesperson")
	@JsonIgnore
	private List<Order> salespersonOrders;
	public Salesperson() {
		super();
	}
	public Salesperson(String name) {
		super();
		this.name = name;
	}
	public String getSpid() {
		return spid;
	}
	public void setSpid(String spid) {
		this.spid = spid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Order> getSalespersonOrders() {
		return salespersonOrders;
	}
	public void SalespersonOrders(List<Order> salespersonOrders) {
		this.salespersonOrders = salespersonOrders;
	}
	
	
}
