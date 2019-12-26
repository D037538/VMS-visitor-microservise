
package com.visitor.vmsvisitorservice.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.visitor.vmsvisitorservice.model.Visitor;

@Repository
public class UpdateVisitor {
	@PersistenceContext
	private EntityManager em;
    /**
     * it update status by visitor id
     * @param id
     */
	@Transactional
	public void updateStatusById(long id) {
		CriteriaBuilder cb = this.em.getCriteriaBuilder();
		// create update
		CriteriaUpdate<Visitor> update = cb.createCriteriaUpdate(Visitor.class);
		// set the root class
		Root e = update.from(Visitor.class);
		Predicate p1 = cb.and(cb.equal(e.get("id"), id));
		// set update and where clause
		update.set("status", 1);
		update.where(p1);
		// update.where(cb.);
		// perform update
		this.em.createQuery(update).executeUpdate();
		System.out.println("status updated successfully");
		// em.getTransaction().commit();
	}

}