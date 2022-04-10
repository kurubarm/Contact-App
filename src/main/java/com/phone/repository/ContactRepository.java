package com.phone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phone.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
	

}
