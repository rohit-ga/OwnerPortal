package com.ga.service;

import java.sql.SQLException;
import java.util.List;

import com.ga.model.Event;

public interface IEventService {

    public List<Event> viewMyEvents(int dbUserId) throws SQLException;
    
    public void insertEvent(Event event, int dbUserId) throws SQLException;
    
    public List<Event> viewEventDetails(Integer eventId) throws SQLException;
}
