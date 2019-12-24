package com.visitor.vmsvisitorservice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.visitor.vmsvisitorservice.dto.VisitorDto;
import com.visitor.vmsvisitorservice.model.Visitor;

public interface IVisitorService {

	String addVisitor(VisitorDto visitorDto);

	List<Visitor> visitorList();

//	Visitor GetByIdIn(long id);
	public List<Visitor> updateStatus() ;
	//ResponseEntity<Visitor> GetByIdIn(Integer id);

	//Visitor getVisitorById(long id);
	public Visitor sendMailToContactPerson(long id) throws JsonProcessingException;
	public Visitor updateVesitorStatus(long id, VisitorDto visitorDto);

}
