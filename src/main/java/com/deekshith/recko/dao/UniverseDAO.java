package com.deekshith.recko.dao;

import java.util.List;

import com.deekshith.recko.models.Universe;


public interface UniverseDAO {

List<Universe> get();
	
	Universe get(int id);
	
	void save(Universe universe);
	
	void delete(int id);
}
