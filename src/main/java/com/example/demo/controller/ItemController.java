package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.ItemDao;
import com.example.demo.model.Item;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@Api(value = "Invetory API", description = "An API for managing inventory")
public class ItemController {

	@Autowired
	private ItemDao repo;
	
	@GetMapping("/")
	public String index() {
		return "Welcome to inventory API";
	}
	@ApiOperation(value = "Returns a list of all items")
	@GetMapping("/items")
	@ResponseBody
	public List<Item> getItems() {
		return (List<Item>) repo.findAll();
	}
	@ApiOperation(value = "Returns info about an item by id")
	@GetMapping("/items/{itemId}")
	@ResponseBody
	public ResponseEntity<Item> getItem(@PathVariable("itemId") int itemId) {
		Item item = repo.findById(itemId).orElse(null);
		if(item != null) {
			return new ResponseEntity<>(item, HttpStatus.OK);
		} else {
			throw new IllegalArgumentException("Item does not exist.");
		}
	}
	@ApiOperation(value = "Adds a new item")
	@PostMapping("/items")
	@ResponseBody
	public ResponseEntity<Item> addItem(@RequestBody Item newItem) {
		if(repo.findById(newItem.getId()).orElse(null) != null) {
			throw new IllegalArgumentException("An item with the same ID already exists.");
		} else {
			repo.save(newItem);
			return new ResponseEntity<>(newItem, HttpStatus.CREATED);
		}
	}
	@ApiOperation(value = "Withdrawal a quantity from an item by id")
	@PutMapping("/items/{itemId}/withdrawal/{quantity}")
	public ResponseEntity<String> withdrawalItem(@PathVariable int itemId, @PathVariable int quantity) {
		Item item = repo.findById(itemId).orElseThrow(() -> new IllegalArgumentException("Item does not exist"));
		int currentAmount = item.getAmount();
		int newAmount = currentAmount - quantity;
		if(newAmount < 1)
		{
			throw new IllegalArgumentException("Invalid Quantity");
		}
		item.setAmount(newAmount);
		repo.save(item);
		return new ResponseEntity<>("Withdrawal successful, new amount is: " + newAmount, 
				   HttpStatus.OK);
	}
	@ApiOperation(value = "Deposit a quantity from an item by id")
	@PutMapping("/items/{itemId}/deposit/{quantity}")
	public ResponseEntity<String> depositItem(@PathVariable int itemId, @PathVariable int quantity) {
		Item item = repo.findById(itemId).orElseThrow(() -> new IllegalArgumentException("Item does not exist"));
		int currentAmount = item.getAmount();
		int newAmount = currentAmount + quantity;
		item.setAmount(newAmount);
		repo.save(item);
		return new ResponseEntity<>("Deposit successful, new amount is: " + newAmount, 
				   HttpStatus.OK);
	}
	
	@ApiOperation(value = "Delete an item by id")
	@DeleteMapping("/items/{itemId}")
	public ResponseEntity<String> deleteItem(@PathVariable int itemId) {
		if(repo.findById(itemId).orElse(null) == null) {
			throw new IllegalArgumentException("Item does not exist");
		}
		repo.deleteById(itemId);
		return new ResponseEntity<>("Item successfuly deleted", 
				   HttpStatus.OK);
	}
}
