package com.cisco.nms.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cisco.nms.api.model.Notification;

@Repository
public interface NMSRepository extends CrudRepository<Notification, Integer> {

}