package com.deekshith.recko.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deekshith.recko.models.Family;
import com.deekshith.recko.services.FamilyService;

@RestController
@RequestMapping("/family")
public class FamilyController {

	@Autowired
	private FamilyService familyservice;

	@GetMapping("/family/{id}")
	public List<Family> get(@PathVariable int id) {
		return familyservice.get(id);
	}
}
