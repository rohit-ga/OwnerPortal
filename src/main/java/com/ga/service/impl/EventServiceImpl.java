package com.ga.service.impl;

import java.util.List;

import com.ga.dao.impl.EventDaoImpl;
import com.ga.model.Event;
import com.ga.service.IEventService;

public class EventServiceImpl implements IEventService {

    EventDaoImpl eventDao;

    public EventServiceImpl() {
        eventDao = new EventDaoImpl();
    }

    public void insertEvent(Event event, int dbUserId) {
        eventDao.insertEvent(event, dbUserId);
    }

    public List<Event> viewMyEvents(int dbUserId) {
        return eventDao.viewMyEvents(dbUserId);
    }

    public List<Event> viewEventDetails(Integer eventId) {
        return eventDao.viewEventDetails(eventId);
    }

    public void editMyEventDetails(Event event, Integer eventId) {
        eventDao.editMyEventDetails(event, eventId);
    }

    public int getUserIdByEventId(Integer eventId) {
        return eventDao.getUserIdByEventId(eventId);
    }

    public Event getEventbyId(Integer eventId) {
        return eventDao.getEventById(eventId);
    }
}
