package com.deekshith.recko.services;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deekshith.recko.dao.PersonDAO;
import com.deekshith.recko.models.Persons;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDAO persondao;

	@Transactional
	@Override
	public List<Persons> get(int id) {
		return persondao.get(id);
	}

	@Transactional
	@Override
	public void save(Persons person) {
		persondao.save(person);
	}

	@Transactional
	@Override
	public void delete(int id) {
		persondao.delete(id);
	}

	@Transactional
	@Override
	public Boolean getIfBalanced(int id) {
		return persondao.getIfBalanced(id);
	}

	@Override
	public Set<Integer> getUnbalancedFamilys() {
		return persondao.getUnbalancedFamilys();
	}

	@Override
	public List<Persons> balancedFamily(int id) {
		return persondao.balancedFamily(id);
	}

}
