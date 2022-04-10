package com.phone.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.phone.constants.AppConstants;
import com.phone.entity.Contact;
import com.phone.service.ContactService;
import com.phone.properties.AppProperties;

@RestController
@CrossOrigin
public class ContactRestController {
	@Autowired
	private ContactService contactservice;
	
	@Autowired
	private AppProperties appProps;
	
	@PostMapping("/contact")
	public String saveContact(@RequestBody Contact contact) {
		contact.setActiveSw("Y");
		boolean status = contactservice.saveContact(contact);
		
		Map<String, String> messages = appProps.getMessages();
		if(status) {
			return messages.get(AppConstants.CONTACT_SAVE_SUCC);
		}
		else {
			return messages.get(AppConstants.CONTACT_SAVE_FAIL);
		}	
	}
	
	@GetMapping("/contacts")
	public List<Contact> getAllContact(){
		return contactservice.getAllContacts();
	}
	
	@GetMapping ("/contact/{cid}")
	public Contact editContact(@PathVariable("cid") Integer contactId) {
		return contactservice.getContactById(contactId);
	}
	
	@DeleteMapping ("/contact/{cid}")
	public String deleteContact(@PathVariable("cid") Integer contactId) {
		boolean status = contactservice.deleteContactById(contactId);
		
		Map<String, String> messages = appProps.getMessages();
		if(status) {
			return messages.get(AppConstants.CONTACT_DEL_SUCC);
	    }
		else {
			return messages.get(AppConstants.CONTACT_DEL_FAIL);
		}
	}

}
