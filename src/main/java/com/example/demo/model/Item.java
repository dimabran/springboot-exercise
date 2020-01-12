package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Item {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@ApiModelProperty(value = "Item ID")
	private int id;
	@ApiModelProperty(value = "Item Name")
	private String name;
	@ApiModelProperty(value = "Item Amount")
	private int amount;
	@ApiModelProperty(value = "Item Inventory Code")
	private int invCode;
	
	public Item(String name, int amount, int invCode) {
		super();
		this.name = name;
		this.amount = amount;
		this.invCode = invCode;
	}
	public Item() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getInvCode() {
		return invCode;
	}
	public void setInvCode(int invCode) {
		this.invCode = invCode;
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", amount=" + amount + ", invCode=" + invCode + "]";
	}
	
	
	
}
