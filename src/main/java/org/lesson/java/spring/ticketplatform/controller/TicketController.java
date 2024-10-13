package org.lesson.java.spring.ticketplatform.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import org.lesson.java.spring.ticketplatform.model.Note;
import org.lesson.java.spring.ticketplatform.model.Ticket;
import org.lesson.java.spring.ticketplatform.model.Ticket.Status;
import org.lesson.java.spring.ticketplatform.service.CategoryService;
import org.lesson.java.spring.ticketplatform.service.OperatorService;
import org.lesson.java.spring.ticketplatform.service.TicketService;
import org.lesson.java.spring.ticketplatform.service.UserService;
import org.lesson.java.spring.ticketplatform.utils.AuthenticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/tickets")
public class TicketController {
	
	//Service class of the entities used
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private OperatorService operatorService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private UserService userService;
	
	// Controller methods
	
	// Index method
	@GetMapping
	public String index(Model model, @RequestParam( name = "searchedText", required = false ) String searchedText) {
		
		//The method return the complete list of tickets only if the search field is empty
		if( searchedText != null && !searchedText.isEmpty()) {
			
			model.addAttribute("ticketList", ticketService.findTicketsContains(searchedText));
		}else {
			
			model.addAttribute("ticketList", ticketService.findTickets());
		}	
		
		return "/tickets/index";
	}
	
	// Show method
	@GetMapping("/{id}")
	public String show(Model model,
			@PathVariable("id") Integer id,
			Authentication authentication) {
		
		//Get the required ticket and add it to the model
		Ticket ticket = ticketService.findTicketById(id);
		model.addAttribute("ticket", ticket );
		
		//Adding to model the attributes to set the status elements color with the method setStatusColor
		Status status = ticket.getTicketStatus();
		model.addAllAttributes(setStatusColor(status));
		
		//Adding to model a blank Note object
		Note note = new Note();
		note.setTicket(ticket);
		model.addAttribute("note", note);
		
		//Adding to model the current user id
		//Used to redirect the operators to their view
		Integer currentUserId =  AuthenticationUtil.getCurrentUser(authentication, userService).getId();
		model.addAttribute("currentUserId", currentUserId);
		
		return "/tickets/show";
	}
	
	
	//Create method
	@GetMapping("/create")
	public String create(Model model) {
		
		//Add to the model a blank ticket
		Ticket ticket = new Ticket();
		model.addAttribute("ticket", ticket);
		
		//Add to the model the category list used for the selection
		model.addAttribute("categoryList", categoryService.findCategories());
		
		//Add a list of active operators to the model
		model.addAttribute("activeOperators", operatorService.findActiveOperators());
		return "/tickets/create";
		
	}
	
	@PostMapping("/create")
	public String store(
			@Valid @ModelAttribute("ticket") Ticket formTicket,
			BindingResult bindingResult,
			Model model,
			RedirectAttributes attributes,
			Authentication authentication) {
		
		//Check if the operator field is null, and return a new error to inform the user
		if(formTicket.getOperator().getId() == null) {	
			ObjectError error = new ObjectError("globalError", "Select the operator to whom assign the ticket");
			bindingResult.addError(error);
		}
		
		//Check if the object has errors and reassign to the model the needed lists to try again the operation
		if(bindingResult.hasErrors()) {
			model.addAttribute("activeOperators", operatorService.findActiveOperators());
			model.addAttribute("categoryList", categoryService.findCategories());
			return "/tickets/create";
		}
		
		//Set the default field before saving the new ticket
		formTicket.setCreatedBy(authentication.getName());
		formTicket.setTicketStatus(Status.OPEN);
		formTicket.setCreatedAt(LocalDateTime.now());
		formTicket.setUpdatedAt(LocalDateTime.now());
		ticketService.createTicket(formTicket);
		
		attributes.addFlashAttribute("message","The ticket " + formTicket.getTicketHeader() + " has been created successfully");
		attributes.addFlashAttribute("alertClass", "alert-success" );
		
		return "redirect:/tickets";
	}
	
	
	// Update method
	
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		
		//Get the right ticket and assign it to the model
		Ticket ticket = ticketService.findTicketById(id);
		model.addAttribute("ticket", ticket);
		
		//Add the categories list to the model for the selection
		model.addAttribute("categoryList", categoryService.findCategories());
		
		return "/tickets/edit";
		
	}
	
	@PostMapping("/edit/{id}")
	public String update(
			@Valid @ModelAttribute("ticket") Ticket formTicket,
			BindingResult bindingResult,
			Model model,
			RedirectAttributes attributes) {
		
		//Check if the edited ticket has errors and redirect the user to the edit page
		if(bindingResult.hasErrors()) {
			return "/tickets/edit";
		}
		//Set the update time and save the edited ticket
		formTicket.setUpdatedAt(LocalDateTime.now());
		ticketService.updateTicket(formTicket);
		
		attributes.addFlashAttribute("message","The ticket " + formTicket.getTicketHeader() + " has been updated successfully");
		attributes.addFlashAttribute("alertClass", "alert-success" );
		return "redirect:/tickets";
	}
	
	//Delete method
	@PostMapping("/delete/{id}")
	public String delete( @PathVariable("id") Integer id,
			RedirectAttributes attributes) {
		
		
		ticketService.deleteTicket(id);
		
		attributes.addFlashAttribute("message","The ticket with id " + id + " has been deleted successfully");
		attributes.addFlashAttribute("alertClass", "alert-danger" );
		return "redirect:/tickets";
	}
	
	//Method to update the status of the ticket
	//Used by operators users
	@PostMapping("/updateStatus/{id}")
	public String updateStatus(
			@PathVariable("id") Integer id,
			@ModelAttribute("ticket") Ticket formTicket,
			Model model,
			RedirectAttributes attributes) {
		
		//The operators can change the status only from open to in_progress and from in_progress to closed
		if(formTicket.getTicketStatus() == Status.OPEN) {
			formTicket.setTicketStatus(Status.IN_PROGRESS);
		}else {
			if(formTicket.getTicketStatus() == Status.IN_PROGRESS) {
				formTicket.setTicketStatus(Status.CLOSED);
			}
		}
		
		formTicket.setUpdatedAt(LocalDateTime.now());
		ticketService.updateTicket(formTicket);
		
		return "redirect:/tickets/" + id;
	}
	
	
	//Utility methods
	private HashMap<String, String> setStatusColor(Status status) {
		
		HashMap<String, String> attributes = new HashMap<String, String>();
		
		switch (status) {
			case OPEN:
				attributes.put("badgeColor","text-bg-info");
				attributes.put("bgColor","bg-info-subtle");
				attributes.put("borderColor","border-info");

				break;
			case IN_PROGRESS :
				attributes.put("badgeColor","text-bg-warning");
				attributes.put("bgColor","bg-warning-subtle");
				attributes.put("borderColor","border-warning");
				break;
			case CLOSED:
				attributes.put("badgeColor","text-bg-success");
				attributes.put("bgColor","bg-success-subtle");
				attributes.put("borderColor","border-success");
				break;
		}
		
		return attributes;
	}


		
}
