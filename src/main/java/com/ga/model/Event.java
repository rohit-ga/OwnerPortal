package com.ga.model;

import java.io.InputStream;
import java.util.Date;

public class Event {

    private int eventId;
    private String eventTitle;
    private String artistName;
    private Date startDate;
    private Date endDate;
    private Date startTime;
    private Date endTime;
    private String location;
    private String description;
    private InputStream eventImage;
    private String genre;
    private int userId;

    private String imageStr;

    public Event(String eventTitle, String artistName, Date startDate, Date endDate, Date startTime, Date endTime2,
            String location, String description, InputStream eventImage, String genre) {
        super();
        this.eventTitle = eventTitle;
        this.artistName = artistName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime2;
        this.location = location;
        this.description = description;
        this.eventImage = eventImage;
        this.genre = genre;
    }

    public Event() {
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public InputStream getEventImage() {
        return eventImage;
    }

    public void setEventImage(InputStream binaryStream) {
        this.eventImage = binaryStream;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getImageStr() {
        return imageStr;
    }

    public void setImageStr(String imageStr) {
        this.imageStr = imageStr;
    }

}
