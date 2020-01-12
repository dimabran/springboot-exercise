package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Item;

public interface ItemDao extends CrudRepository<Item, Integer>{

}
