package com.ga.dao;

import java.sql.SQLException;
import java.util.List;

import com.ga.model.Event;

public interface IEventDao {

    public List<Event> viewMyEvents(int dbUserId) throws SQLException;

    public void insertEvent(Event event, int dbUserId) throws SQLException;

    public List<Event> viewEventDetails(Integer eventId) throws SQLException;

    public void editMyEventDetails(Event event, Integer eventId) throws SQLException;

    public int getUserIdByEventId(Integer eventId) throws SQLException;

    public Event getEventById(Integer eventId);
}
