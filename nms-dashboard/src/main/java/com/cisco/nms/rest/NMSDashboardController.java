package com.cisco.nms.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cisco.nms.api.Notifications;
import com.cisco.nms.api.model.Notification;

@RestController
public class NMSDashboardController {
	@Autowired
	private Notifications notifications;

	
	// -------------------Post Notification ---------------------------------------------
	@RequestMapping(value = "/message", method = RequestMethod.POST)
	public ResponseEntity<Notification> add(@RequestBody Notification message, UriComponentsBuilder builder) {
		notifications.add(message);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/message/{id}").buildAndExpand(message.getId()).toUri());
		return new ResponseEntity<Notification>(message, headers, HttpStatus.CREATED);
	}
	
	// -------------------Retrieve Messages By Id---------------------------------------------
	@RequestMapping(value = "/message/{id}", method = RequestMethod.GET)
	public ResponseEntity<Notification> add(@PathVariable("id") int id) {
		Notification message = notifications.find(id);
		return new ResponseEntity<Notification>(message, HttpStatus.OK);
	}
	
	// -------------------Retrieve All Messages---------------------------------------------	 
	@RequestMapping(value = "/message", method = RequestMethod.GET)
	public ResponseEntity<List<Notification>> getMessages() {
		return new ResponseEntity<List<Notification>>(notifications.findAllMessages(), HttpStatus.OK);
	}
	
	
}
