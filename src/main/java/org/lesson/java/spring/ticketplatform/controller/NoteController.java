package org.lesson.java.spring.ticketplatform.controller;

import java.time.LocalDateTime;

import org.lesson.java.spring.ticketplatform.model.Note;
import org.lesson.java.spring.ticketplatform.model.Ticket;
import org.lesson.java.spring.ticketplatform.model.Ticket.Status;
import org.lesson.java.spring.ticketplatform.service.NoteService;
import org.lesson.java.spring.ticketplatform.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/notes")
public class NoteController {
	
	@Autowired
	private NoteService noteService;
	
	@Autowired
	private TicketService ticketService;
	
	
	//Create Method
	
	@GetMapping("/create")
	public String create(Model model) {
		
		Note note = new Note();
		model.addAttribute("note", note);
		
		return "/notes/create";
		
	}
	
	@PostMapping("/create")
	public String store(
			@Valid @ModelAttribute("note") Note formNote,
			BindingResult bindingResult,
			Model model,
			RedirectAttributes attributes ) {
		
		int ticketId = formNote.getTicket().getId();
		
		if(bindingResult.hasErrors()) {
			Ticket ticket = ticketService.findTicketById(ticketId);
			
			model.addAttribute("ticket", ticket );
			return "/tickets/show"  ;
		}
		
		formNote.setCreatedAt(LocalDateTime.now());
		//Temporary setting a string for the author of the notes
		formNote.setAuthor("Temp Author");
		noteService.createNote(formNote);
		
		
		return "redirect:/tickets/" + ticketId ;
	}

}
