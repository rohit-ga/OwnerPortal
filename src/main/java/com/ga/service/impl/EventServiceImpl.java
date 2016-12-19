package com.ga.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.ga.dao.impl.EventDaoImpl;
import com.ga.model.Event;
import com.ga.service.IEventService;

public class EventServiceImpl implements IEventService {

    EventDaoImpl eventDao = new EventDaoImpl();

    public void insertEvent(Event event, int dbUserId) throws SQLException {
        
        eventDao.insertEvent(event, dbUserId);
    }

    public List<Event> viewMyEvents(int dbUserId) throws SQLException {
        
        return eventDao.viewMyEvents(dbUserId);
    }

    public List<Event> viewEventDetails(Integer eventId) throws SQLException {
        
        return eventDao.viewEventDetails(eventId);
    }

    public void editMyEventDetails(Event event, Integer eventId) throws SQLException {

        eventDao.editMyEventDetails(event, eventId);
    }

    public int getUserIdByEventId(Integer eventId) throws SQLException {
      
        return eventDao.getUserIdByEventId(eventId);
    }
}
