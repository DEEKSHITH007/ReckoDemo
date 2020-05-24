package com.deekshith.recko.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.deekshith.recko.models.Persons;

@Repository
public class PersonDAOImpl implements PersonDAO {

	@Autowired
	private EntityManager entitymanger;

	@Override
	public List<Persons> get(int id) {
		Session currentsession = entitymanger.unwrap(Session.class);
		Query<Persons> query = currentsession.createQuery("from Persons where Family_id = " + id, Persons.class);
		List<Persons> list = query.getResultList();
		return list;
	}

	@Override
	public void save(Persons person) {
		Session currentsession = entitymanger.unwrap(Session.class);
		currentsession.save(person);
	}

	@Override
	public void delete(int id) {
		Session currentsession = entitymanger.unwrap(Session.class);
		Persons person = currentsession.get(Persons.class, id);
		currentsession.delete(person);
	}

	@Override
	public Boolean getIfBalanced(int familyId) {
		Boolean result = false;
		Session currentsession = entitymanger.unwrap(Session.class);

		try {

			CriteriaBuilder builder = currentsession.getCriteriaBuilder();
			CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
			Root<Persons> root = query.from(Persons.class);
			query.multiselect(root.get("family_id"), root.get("super_power"))
					.where(builder.equal(root.get("family_id"), familyId));
			Query<Object[]> q = currentsession.createQuery(query);
			List<Object[]> list = q.getResultList();
			List<Integer> familyIdList = new ArrayList<Integer>();
			for (Object[] obj : list) {
				familyIdList.add((Integer) obj[1]);
			}
			Set<Integer> personSet = new HashSet<Integer>(familyIdList);
			if (personSet.size() == 1) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return result;

	}

	@Override
	public Set<Integer> getUnbalancedFamilys() {
		Session currentsession = entitymanger.unwrap(Session.class);
		Set<Integer> personSet = new HashSet<Integer>();
		ConcurrentHashMap<Integer, Integer> personmap = new ConcurrentHashMap<Integer, Integer>();
		ConcurrentHashMap<Integer, Integer> unbalancedFamilesMap = new ConcurrentHashMap<Integer, Integer>();
		int count = 0;
		try {

			CriteriaBuilder builder = currentsession.getCriteriaBuilder();
			CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
			Root<Persons> root = query.from(Persons.class);
			query.multiselect(root.get("family_id"), root.get("super_power"));
			Query<Object[]> q = currentsession.createQuery(query);
			List<Object[]> list = q.getResultList();

			for (Object[] obj : list) {
				personmap.put((Integer) obj[1], (Integer) obj[0]);

			}
			System.out.println(personmap);
			for (Entry<Integer, Integer> entry : personmap.entrySet()) {
				if (unbalancedFamilesMap.containsKey(entry.getValue()))
					unbalancedFamilesMap.put(entry.getValue(), ++count);
				else
					unbalancedFamilesMap.put(entry.getValue(), 1);

			}
			System.out.println(unbalancedFamilesMap);
			for (Entry<Integer, Integer> entry : unbalancedFamilesMap.entrySet()) {
				if (entry.getValue() != 1)
					personSet.add(entry.getKey());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return personSet;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Persons> balancedFamily(int id) {
		Session currentsession = entitymanger.unwrap(Session.class);
		Session currentsession1 = entitymanger.unwrap(Session.class);
		Session currentsession2 = entitymanger.unwrap(Session.class);
		Transaction transaction = null;

		List<Persons> FamilyIDlist = new ArrayList<Persons>();
		try {
			int sum = 0;
			CriteriaBuilder builder = currentsession.getCriteriaBuilder();
			CriteriaQuery<Integer> query = builder.createQuery(Integer.class);
			Root<Persons> root = query.from(Persons.class);
			query.multiselect(root.get("super_power")).where(builder.equal(root.get("family_id"), id));
			Query<Integer> q = currentsession.createQuery(query);
			List<Integer> list = q.getResultList();
			List<Integer> familyIdList = new ArrayList<Integer>();
			for (Integer obj : list) {
				familyIdList.add((Integer) obj);
			}

			for (Integer i : familyIdList) {
				sum = sum + i;
			}
			int avgPower = sum / list.size();

			transaction = currentsession1.beginTransaction();
			Query updatequery = currentsession1
					.createQuery("update Persons set super_power=" + avgPower + " where family_id =" + id);

			updatequery.executeUpdate();
			transaction.commit();

			Query<Persons> queryFamily = currentsession2.createQuery("from Persons where Family_id = " + id,
					Persons.class);
			FamilyIDlist = queryFamily.getResultList();
			currentsession2.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return FamilyIDlist;

	}

}
