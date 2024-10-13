package org.lesson.java.spring.ticketplatform.controller;

import org.lesson.java.spring.ticketplatform.model.Operator;
import org.lesson.java.spring.ticketplatform.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/operators")
public class OperatorController {

	@Autowired
	private OperatorService operatorService;
	
	@GetMapping("/{id}")
	public String show(Model model,
			@PathVariable("id") Integer id) {
		
		Operator operator = operatorService.getOperatorByUserId(id);
				
		model.addAttribute("operator", operator );
		
		return "/operators/show";
	}
	
	
	@GetMapping("/updatestatus/{id}")
	public String updateStatus(Model model,
			@RequestParam(name = "setStatus", required = false)String setStatus,
			@PathVariable("id") Integer id) {
		
		//Get the operator by id
		Operator operator = operatorService.getOperatorByUserId(id);
		
		//If the setStatus variable have value "setInactive", the inactive field is set to true, otherwise to false
		if(setStatus.equals("setInactive")) {
			operator.setInactive(true);
		}else {
			operator.setInactive(false);
		}
		
		model.addAttribute("operator", operator );
		
		operatorService.updateOperator(operator);
		
		return "/operators/show";
	}
			
}
