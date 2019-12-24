package com.visitor.vmsvisitorservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.visitor.vmsvisitorservice.service.IVisitorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.visitor.vmsvisitorservice.dto.VisitorDto;
import com.visitor.vmsvisitorservice.model.Visitor;
import com.visitor.vmsvisitorservice.repository.VisitorRepository;

;

@Controller
public class VisitorCOn {
	@Autowired
	private IVisitorService iVisitorService;

	@Autowired
	private VisitorRepository visitorRepository;
/**
 * It direct to /visitor
 * @param model
 * @return
 */
	@RequestMapping(value = "/visitor", method = RequestMethod.GET)
	public String newRegistration(ModelMap model) {
		Visitor visitor = new Visitor();
		model.addAttribute("visitor", visitor);
		return "visitor";
	}
	/**
	 * It views list of countries
	 * @return
	 */
	@ModelAttribute("countries")
	public List<String> initializeCountries() {

		List<String> countries = new ArrayList<String>();
		countries.add("INDIA");
		countries.add("USA");
		countries.add("CANADA");
		countries.add("FRANCE");
		countries.add("GERMANY");
		countries.add("ITALY");
		countries.add("OTHER");
		return countries;
	}
	/**
	 * It views list of states
	 * @return
	 */
	@ModelAttribute("states")
	public List<String> initializeStates() {

		List<String> countries = new ArrayList<String>();
		countries.add("Andhra Pradesh");
		countries.add("Arunachal Pradesh ");
		countries.add(" Bihar ");
		countries.add("Himachal Pradesh ");
		countries.add("Jharkhand ");
		countries.add("Maharashtra ");
		countries.add("Manipur ");
		return countries;
	}
/**
 * It views list of cities
 * @return
 */
	@ModelAttribute("cities")
	public List<String> initializeCities() {

		List<String> countries = new ArrayList<String>();
		countries.add("Mumbai");
		countries.add("Pune");
		countries.add(" Nagpur ");
		countries.add("Nashik ");
		countries.add("Palghar ");
		countries.add("Aurangabad ");
		countries.add("Solapur ");
		return countries;
	}
/**
 * It saves record and redirects to /viewvisitor
 * @param visitorDto
 * @param result
 * @param model
 * @param redirectAttributes
 * @return
 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveRegistration(@Valid VisitorDto visitorDto, BindingResult result, ModelMap model,
			RedirectAttributes redirectAttributes) {
		iVisitorService.addVisitor(visitorDto);
	return "redirect:/viewvisitor";// will redirect to viewvisitor request mapping
	}
/**
 * It views list of visitors
 * @return
 * @throws JsonProcessingException
 */
	@RequestMapping("/viewvisitor")
	public ModelAndView viewstudents() throws JsonProcessingException {
		List<Visitor> list = iVisitorService.visitorList();
		return new ModelAndView("viewvisitor", "list", list);
	}

	/* It opens the record for the given id in editstudent page */
	@RequestMapping(value = "/editvisitor/{id}")
	public String edit(@PathVariable int id, ModelMap model) {
		Optional<Visitor> visitor = visitorRepository.findById((long) id);
		model.addAttribute("visitor", visitor);
		return "editvisitor";

	}

	/*
	 * It updates record for the given id in editstudent page and redirects to
	 * /viewstudents
	 */
	@RequestMapping(value = "/editsave", method = RequestMethod.POST)
	public ModelAndView editsave(@ModelAttribute("visitor") VisitorDto visitorDto) {
		System.out.println("id is" + visitorDto.getId());
		iVisitorService.addVisitor(visitorDto);
		return new ModelAndView("redirect:/viewvisitor");
	}

	/* It deletes record for the given id and redirects to /viewstudents */
	@RequestMapping(value = "/deletevisitor/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable int id) {
		visitorRepository.deleteById((long) id);
		return new ModelAndView("redirect:/viewvisitor");
	}
   /**
    * It deletes all record and reditect /visitor
    * @return
    */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete() {
		visitorRepository.deleteAll();
		return new ModelAndView("redirect:/visitor");
	}

}
