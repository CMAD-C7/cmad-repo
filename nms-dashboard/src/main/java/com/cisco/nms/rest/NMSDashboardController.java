package com.cisco.nms.rest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cisco.nms.api.model.Message;
import com.cisco.nms.api.model.MessageCountDTO;
import com.cisco.nms.service.MessageService;

/**
 * 
 * @author dedadhic
 *
 */
@RestController
@RequestMapping(value = "/message")
public class NMSDashboardController {
	
	@Autowired
	private MessageService messageService;

	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Message> add(@RequestBody Message message, UriComponentsBuilder builder) {
		messageService.add(message);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/message/{id}").buildAndExpand(message.getId()).toUri());
		return new ResponseEntity<Message>(message, headers, HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Message> add(@PathVariable("id") Long id) {
		Optional<Message> optional = messageService.findById(id);
		return new ResponseEntity<Message>(optional.get(), HttpStatus.OK);
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Message>> searchMessages() {
		Sort sort =  Sort.by(new Sort.Order(Direction.ASC, "dateAdded"));
		PageRequest pageRequest =  PageRequest.of(0, 5, sort);
		return new ResponseEntity<List<Message>>(messageService.findByDateAddedBetween(new Date(), new Date(), pageRequest), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public ResponseEntity<List<MessageCountDTO>> getMessageCount() {
		Date currentDate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate); // Now use today date.
		c.add(Calendar.DATE, -1);
		return new ResponseEntity<List<MessageCountDTO>>(messageService.getMessageCount(currentDate, c.getTime()), HttpStatus.OK);
	}
	
	
}
