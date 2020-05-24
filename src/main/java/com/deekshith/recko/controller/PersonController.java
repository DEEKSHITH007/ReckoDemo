package com.deekshith.recko.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deekshith.recko.models.Persons;
import com.deekshith.recko.services.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonService personservice;

	@GetMapping("/persons/{id}")
	public List<Persons> get(@PathVariable int id) {
		return personservice.get(id);
	}

	@PostMapping("/save")
	public Persons save(@RequestBody Persons perobj) {
		personservice.save(perobj);
		return perobj;
	}

	@GetMapping("/balance/{id}")
	public Boolean getIfBalanced(@PathVariable int id) {
		return personservice.getIfBalanced(id);
	}

	@GetMapping("/unbalanced")
	public Set<Integer> getUnblanacedFamilies() {
		return personservice.getUnbalancedFamilys();
	}

	@GetMapping("/trybalance/{id}")
	public List<Persons> getfamilyAfterBalancing(@PathVariable int id) {
		return personservice.balancedFamily(id);
	}
}
