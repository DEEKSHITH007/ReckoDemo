package com.deekshith.recko.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Family")
public class Family {

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int family_id;
	@Column(name = "Universe_id")
	private int Universe_id;

	public int getFamily_id() {
		return family_id;
	}

	public void setFamily_id(int family_id) {
		this.family_id = family_id;
	}

	public int getUniverse_id() {
		return Universe_id;
	}

	public void setUniverse_id(int universe_id) {
		this.Universe_id = universe_id;
	}
}
