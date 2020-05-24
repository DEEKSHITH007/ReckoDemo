package com.deekshith.recko.dao;

import java.util.List;
import java.util.Set;

import com.deekshith.recko.models.Persons;

public interface PersonDAO {

	List<Persons> get(int id);

	void save(Persons person);

	void delete(int id);

	Boolean getIfBalanced(int familyId);

	Set<Integer> getUnbalancedFamilys();

	List<Persons> balancedFamily(int id);
}
