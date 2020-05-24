package com.deekshith.recko.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deekshith.recko.dao.FamilyDAO;
import com.deekshith.recko.models.Family;

@Service
public class FamilyServiceImpl implements FamilyService {

	@Autowired
	private FamilyDAO familydao;

	@Transactional
	@Override
	public List<Family> get(int universe_id) {
		return familydao.get(universe_id);
	}

	@Transactional
	@Override
	public void save(Family family) {
		familydao.save(family);

	}

	@Transactional
	@Override
	public void delete(int id) {
		familydao.delete(id);
	}

}
