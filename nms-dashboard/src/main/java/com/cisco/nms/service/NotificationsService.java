package com.cisco.nms.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cisco.nms.api.Notifications;
import com.cisco.nms.api.exception.NMSDashboardException;
import com.cisco.nms.api.model.Notification;
import com.cisco.nms.data.NMSRepository;



@Service
public class NotificationsService implements Notifications {
	@Autowired
	private NMSRepository nmsRepo;

	@Override
	public void add(Notification message) throws NMSDashboardException {
		if (message == null || message.getId() < 0 || message.getDescription().trim().length() == 0)
			throw new NMSDashboardException();
		if (nmsRepo.findById(message.getId()).orElse(null) != null)
			throw new NMSDashboardException();
		nmsRepo.save(message);
	}

	@Override
	public Notification find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Notification> findAllMessages() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
