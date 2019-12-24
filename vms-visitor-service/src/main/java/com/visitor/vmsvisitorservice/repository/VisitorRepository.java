package com.visitor.vmsvisitorservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;


import com.visitor.vmsvisitorservice.model.Visitor;
@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {

	//boolean findById(long id);
	@Modifying
	@Query("update Visitor v set v.status = ?1 where v.id = ?1")
	int setStatusForEARAttachment(@Param("id") Long id,@Param("status") Integer status);	

}
