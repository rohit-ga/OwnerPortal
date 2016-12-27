package com.ga.dao.impl;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.ga.dao.IEventDao;
import com.ga.model.Event;
import com.ga.util.DatabaseConnection;
import com.mysql.jdbc.Blob;

public class EventDaoImpl implements IEventDao {

    static Connection connection;

    static {
        try {
            connection = DatabaseConnection.doConnection();
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void insertEvent(Event event, int dbUserId) throws SQLException {
        try {
            PreparedStatement pst = connection.prepareStatement("insert into event values (?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setInt(1, 0);
            pst.setString(2, event.getEventTitle());
            pst.setString(3, event.getArtistName());
            pst.setDate(4, new java.sql.Date(event.getStartDate().getTime()));
            pst.setDate(5, new java.sql.Date(event.getEndDate().getTime()));
            pst.setTime(6, new Time(event.getStartTime().getTime()));
            pst.setTime(7, new Time(event.getEndTime().getTime()));
            pst.setString(8, event.getLocation());
            pst.setString(9, event.getDescription());
            pst.setBlob(10, event.getEventImage());
            pst.setString(11, event.getGenre());
            pst.setInt(12, dbUserId);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error::" + e.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public List<Event> viewMyEvents(int dbUserId) throws SQLException {
        List<Event> myEventList = new ArrayList<Event>();
        try {
            PreparedStatement pst = connection.prepareStatement("select * from event where user_id = ?");
            pst.setInt(1, dbUserId);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                Event dbEvent = new Event();
                dbEvent.setEventId(rs.getInt("event_id"));
                dbEvent.setEventTitle(rs.getString("event_title"));
                Blob imageBlob = (Blob) rs.getBlob("event_image");
                InputStream binaryStream = imageBlob.getBinaryStream();
                dbEvent.setEventImage(binaryStream);
                myEventList.add(dbEvent);
            }

        } catch (SQLException e) {
            System.err.println("Error::" + e.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return myEventList;
    }

    public List<Event> viewEventDetails(Integer eventId) throws SQLException {

        List<Event> anEventDetail = new ArrayList<Event>();

        try {
            PreparedStatement pst = connection.prepareStatement("select * from event where event_id = ?");
            pst.setInt(1, eventId);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {

                Event dbEvent = new Event();
                dbEvent.setEventId(rs.getInt("event_id"));
                dbEvent.setEventTitle(rs.getString("event_title"));
                dbEvent.setArtistName(rs.getString("artist_name"));
                dbEvent.setStartDate(rs.getDate("event_start_date"));
                dbEvent.setEndDate(rs.getDate("event_end_date"));
                dbEvent.setStartTime(rs.getTime("event_start_time"));
                dbEvent.setEndTime(rs.getTime("event_end_time"));
                dbEvent.setLocation(rs.getString("location"));
                dbEvent.setDescription(rs.getString("description"));
                dbEvent.setGenre(rs.getString("genre"));
                anEventDetail.add(dbEvent);

            }
        } catch (SQLException e) {
            System.err.println("Error::- " + e.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return anEventDetail;
    }

    public void editMyEventDetails(Event event, Integer eventId) throws SQLException {
        try {
            PreparedStatement pst = connection
                    .prepareStatement("update event set event_title=?,artist_name=?,event_start_date=?,event_end_date=?,event_start_time=?,event_end_time=?,location=?,description=?,event_image=?,genre=? where event_id=?");
            pst.setString(1, event.getEventTitle());
            pst.setString(2, event.getArtistName());
            pst.setDate(3, new java.sql.Date(event.getStartDate().getTime()));
            pst.setDate(4, new java.sql.Date(event.getEndDate().getTime()));
            pst.setTime(5, new Time(event.getStartTime().getTime()));
            pst.setTime(6, new Time(event.getEndTime().getTime()));
            pst.setString(7, event.getLocation());
            pst.setString(8, event.getDescription());
            pst.setBlob(9, event.getEventImage());
            pst.setString(10, event.getGenre());
            pst.setInt(11, eventId);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error::" + e.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public int getUserIdByEventId(Integer eventId) throws SQLException {

        int dbUserId = 0;

        try {
            PreparedStatement pst = connection.prepareStatement("select user_id from event where event_id=?");
            pst.setInt(1, eventId);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                dbUserId = rs.getInt("user_id");
            }
        } catch (SQLException e) {
            System.err.println("Error::: " + e.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return dbUserId;
    }

    public Event getEventById(Integer eventId) throws SQLException {

        Event dbEvent = new Event();

        try {
            PreparedStatement pst = connection.prepareStatement("select event_image from event where event_id=?");
            pst.setInt(1, eventId);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Blob imageBlob = (Blob) rs.getBlob("event_image");
                InputStream binaryStream = imageBlob.getBinaryStream();
                dbEvent.setEventImage(binaryStream);
            }
        } catch (SQLException e) {
            System.err.println("Error:: " + e.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return dbEvent;
    }
}