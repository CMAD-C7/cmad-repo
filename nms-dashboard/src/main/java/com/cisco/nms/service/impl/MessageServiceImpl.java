package com.cisco.nms.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cisco.nms.api.model.Message;
import com.cisco.nms.api.model.MessageCountDTO;
import com.cisco.nms.data.MessageRepository;
import com.cisco.nms.service.MessageService;

/**
 * 
 * @author dedadhic
 *
 */
@Service
public class MessageServiceImpl implements MessageService {
	
	
	
	@Autowired
	private MessageRepository messageRepository;

	@Override
	public void add(Message message) {
		messageRepository.save(message);
	}

	@Override
	public Optional<Message> findById(Long id) {
		return messageRepository.findById(id);
	}

	@Override
	public List<Message> findByDateAddedBetween(Date startDate, Date endDate, Pageable pageable) {
		return messageRepository.findByDateAddedBetween(startDate, endDate, pageable);
	}

	@Override
	public List<MessageCountDTO> getMessageCount(Date startDate, Date endDate) {
		return messageRepository.fetchMessageCount(startDate, endDate);
	}

	public void setMessageRepository(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

}
