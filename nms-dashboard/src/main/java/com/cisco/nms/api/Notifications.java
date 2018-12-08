package com.cisco.nms.api;

import java.util.List;

import com.cisco.nms.api.model.Notification;

public interface Notifications {
	
	public void add(Notification message);

	public Notification find(int id);

	public List<Notification> findAllMessages();	
}