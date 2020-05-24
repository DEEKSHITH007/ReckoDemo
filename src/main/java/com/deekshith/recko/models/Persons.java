package com.deekshith.recko.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Persons")
public class Persons {

	@Column(name = "Personid")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int person_id;
	@Column(name = "Family_id")
	private int family_id;
	@Column(name = "Super_power")
	private int super_power;

	public int getSuper_power() {
		return super_power;
	}

	public void setSuper_power(int super_power) {
		this.super_power = super_power;
	}

	public int getPerson_id() {
		return person_id;
	}

	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}

	public int getFamily_id() {
		return family_id;
	}

	public void setFamily_id(int family_id) {
		this.family_id = family_id;
	}

}