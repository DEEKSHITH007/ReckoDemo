package com.deekshith.recko.services;

import java.util.List;

import com.deekshith.recko.models.Family;

public interface FamilyService {

	List<Family> get(int universe_id);

	void save(Family family);

	void delete(int id);

}
