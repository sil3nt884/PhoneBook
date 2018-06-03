package com.phonebook.hackajob.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.phonebook.hackajob.api.PhoneBookHelper;
import com.phonebook.hackajob.pojo.Contact;

@Controller
public class IndexController {

	
	
	@Autowired
	PhoneBookHelper helper;
	
	@RequestMapping(value = { "/" }, method = { RequestMethod.GET })
	public ModelAndView index (ModelAndView model) {
		ArrayList<Contact> contacts = helper.getContacts();
		model.addObject("contacts", contacts);
		model.setViewName("index.html");
		return model;
		
	}
}
