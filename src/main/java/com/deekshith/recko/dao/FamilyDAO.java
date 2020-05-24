package com.deekshith.recko.dao;

import java.util.List;

import com.deekshith.recko.models.Family;

public interface FamilyDAO {

	List<Family> get(int universe_id);

	void save(Family family);

	void delete(int id);
}
