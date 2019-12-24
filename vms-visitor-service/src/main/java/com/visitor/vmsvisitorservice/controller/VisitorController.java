package com.visitor.vmsvisitorservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.visitor.vmsvisitorservice.dto.VisitorDto;
import com.visitor.vmsvisitorservice.model.Visitor;
import com.visitor.vmsvisitorservice.service.IVisitorService;

//@Controller
@RestController
@RequestMapping("/visitor")
public class VisitorController {

	@Autowired
	private IVisitorService visitorService;
	
	@PostMapping("/addVisitor")
	public ResponseEntity<String> addVisitor(@RequestBody VisitorDto visitorDto) {
		return new ResponseEntity<String>(visitorService.addVisitor(visitorDto), HttpStatus.OK);
	}
	@GetMapping("/visitorList")
	public List<Visitor> usersList() {
		return visitorService.visitorList();
	}
	@GetMapping("/visitorUpdate")
	public List<Visitor> visitorUpdate() {
		return visitorService.updateStatus();
	}

	@GetMapping("/viewVisitor")
	public ModelAndView addNewEmployee() {

		Visitor visitor = new Visitor();
		return new ModelAndView("RegistrationPageForVisitor", "form", visitor);
	}

	/*
	 * @GetMapping("/visitorList/{id}") public Visitor GetByIdIn(@PathVariable long
	 * id) { return visitorService.GetByIdIn(id); }
	 */
	/**
	 * method for update status
	 * @param id
	 * @param visitorDto
	 * @return
	 */
	@PutMapping("/visitorList/{id}")
	public Visitor UpdateVisitorStatus(@PathVariable long id,@RequestBody VisitorDto visitorDto)
	{
		System.out.println("visitor status"+visitorDto.getStatus());
		return visitorService.updateVesitorStatus(id, visitorDto);
	}
}
