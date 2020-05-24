package com.deekshith.recko.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.deekshith.recko.models.Family;

@Repository
public class FamilyDAOImpl implements FamilyDAO {

	@Autowired
	private EntityManager entitymanger;

	@Override
	public List<Family> get(int Universe_id) {
		Session currentsession = entitymanger.unwrap(Session.class);
		Query<Family> query = currentsession.createQuery("from Family f where f.Universe_id = " + Universe_id,
				Family.class);
		List<Family> list = query.getResultList();
		return list;
	}

	@Override
	public void save(Family family) {
		Session currentsession = entitymanger.unwrap(Session.class);
		currentsession.save(family);
	}

	@Override
	public void delete(int id) {
		Session currentsession = entitymanger.unwrap(Session.class);
		Family fml = currentsession.get(Family.class, id);
		currentsession.delete(fml);
	}

}
