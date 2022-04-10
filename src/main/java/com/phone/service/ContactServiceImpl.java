package com.phone.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.phone.entity.Contact;
import com.phone.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {
	@Autowired
	private ContactRepository contactRepo;
	
	@Override
	public boolean saveContact(Contact contact) {
	 Contact save = contactRepo.save(contact);
	 if(save.getContactId()!=null){
		 return true;
	 }
	 return false;
	}

	@Override
	public List<Contact> getAllContacts() {
		Contact contact = new Contact();
		contact.setActiveSw("Y");
		return contactRepo.findAll(Example.of(contact));
	}

	@Override
	public Contact getContactById(Integer contactId) {
		// ctrl+2 is shortcut to find the return type.
		Optional<Contact> findById = contactRepo.findById(contactId);
		if(findById.isPresent()) {
			return findById.get();
		}
		else
		return null;
	}

	@Override
	public boolean deleteContactById(Integer contactId) {
		Optional<Contact> findById = contactRepo.findById(contactId);
		if(findById.isPresent()) {
			 Contact contact = findById.get();
			 contact.setActiveSw("N");
			  contactRepo.save(contact);
			  return true;
		}
		return false;
	}

}
