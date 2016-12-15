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

public class EventDaoImpl implements IEventDao{

    static Connection connection;

    static {
        try {
            connection = DatabaseConnection.doConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertEvent(Event event, int dbUserId) throws SQLException {
        
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
    }

    public List<Event> viewMyEvents(int dbUserId) throws SQLException {
        
        PreparedStatement pst = connection.prepareStatement("select * from event where user_id = ?");
        pst.setInt(1, dbUserId);
        List<Event> myEventList = new ArrayList<Event>();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
//            Blob imageBlob = (Blob) rs.getBlob("event_image");
            Event dbEvent = new Event();
            dbEvent.setEventTitle(rs.getString("event_title"));
            dbEvent.setArtistName(rs.getString("artist_name"));
            dbEvent.setStartDate(rs.getDate("event_start_date"));
            dbEvent.setEndDate(rs.getDate("event_end_date"));
            dbEvent.setStartTime(rs.getDate("event_start_time"));
            dbEvent.setEndTime(rs.getDate("event_end_time"));
            dbEvent.setLocation(rs.getString("location"));
            dbEvent.setDescription(rs.getString("description"));
            dbEvent.setEventImage((InputStream) rs.getBlob("event_image"));
            
//            InputStream binaryStream = imageBlob.getBinaryStream(0, imageBlob.length());
            dbEvent.setGenre(rs.getString("genre"));
            myEventList.add(dbEvent);
        }
        
        return myEventList;
    }
}
