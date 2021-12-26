package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.example.demo.models.validations.OrderPOSTValidations;
import com.example.demo.models.validations.SalespersonPOSTValidations;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity(name = "ordertable")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull(message = "oid must be provided", groups = OrderPOSTValidations.class) // the oid must be provided, if not error message will be returned
	private String oid;
	@NotNull(message = "orderDate must be provided", groups = OrderPOSTValidations.class) // the orderDate must be provided, if not error message will be returned
	private String orderDate;
	@ManyToOne
	@JoinColumn(name = "spid_FK")
	@NotNull(message = "orderSalesperson must be provided", groups = OrderPOSTValidations.class) // the orderSalesperson must be provided, if not error message will be returned
	private Salesperson orderSalesperson;
	@ManyToOne
	@JoinColumn(name = "pid_FK")
	@NotNull(message = "orderProduct must be provided", groups = OrderPOSTValidations.class) // the orderProduct must be provided, if not error message will be returned
	private Product orderProduct;
	@NotNull(message = "orderQuantity must be provided", groups = OrderPOSTValidations.class) // the orderQuantity must be provided, if not error message will be returned
	private int orderQuantity;
	public Order() {
		super();
	}
	public Order(String orderDate) {
		super();
		this.orderDate = orderDate;
	}
	public Order(String orderDate, Salesperson orderSalesperson) {
		super();
		this.orderDate = orderDate;
		this.orderSalesperson = orderSalesperson;
	}
	public Order(String orderDate, Salesperson orderSalesperson, Product orderProduct) {
		super();
		this.orderDate = orderDate;
		this.orderSalesperson = orderSalesperson;
		this.orderProduct = orderProduct;
	}
	public Order(String oid, String orderDate, Salesperson orderSalesperson) {
		super();
		this.oid = oid;
		this.orderDate = orderDate;
		this.orderSalesperson = orderSalesperson;
	}
	public Order(String oid, String orderDate, Salesperson orderSalesperson, Product orderProduct) {
		super();
		this.oid = oid;
		this.orderDate = orderDate;
		this.orderSalesperson = orderSalesperson;
		this.orderProduct = orderProduct;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public Salesperson getOrderSalesperson() {
		return orderSalesperson;
	}
	public void setOrderSalesperson(Salesperson orderSalesperson) {
		this.orderSalesperson = orderSalesperson;
	}
	public Product getorderProduct() {
		return orderProduct;
	}
	public void setOrderProduct(Product orderProduct) {
		this.orderProduct = orderProduct;
	}
	public int getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
}
