package com.ga.service;

import java.util.List;

import com.ga.model.Event;

public interface IEventService {

    public List<Event> viewMyEvents(int dbUserId);
    
    public void insertEvent(Event event, int dbUserId);
    
    public List<Event> viewEventDetails(Integer eventId);
    
    public void editMyEventDetails(Event event, Integer eventId);
    
    public int getUserIdByEventId(Integer eventId);

    public Event getEventbyId(Integer eventId);
}
