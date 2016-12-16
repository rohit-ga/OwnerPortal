package com.ga.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.ga.model.Event;
import com.ga.service.impl.EventServiceImpl;
import com.ga.service.impl.UserServiceImpl;

@WebServlet("/EventController")
@MultipartConfig
public class EventController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    UserServiceImpl userService = new UserServiceImpl();
    EventServiceImpl eventService = new EventServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            doProcess(request, response);
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        try {
            doProcess(request, response);
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ParseException,
            IOException, SQLException, ServletException {

        String action = request.getParameter("action");
        System.out.println("Action of event controller:::::" + action);
        if (action.equals("addEvent")) {
            insertEvent(request, response);
        } else if (action.equals("viewEvent")) {
            viewEventDetails(request, response);
        } else if (action.equals("update")) {
            fetchEventData(request,response);
        } else if (action.equals("editEvent")) {
            editMyEventDetails(request, response);
        }
    }

    private void fetchEventData(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        
        Integer eventId = Integer.parseInt(request.getParameter("eventId"));
        List<Event> anEventDetail = eventService.viewEventDetails(eventId);
        request.setAttribute("anEventDetail", anEventDetail);
        request.getRequestDispatcher("createEvent.jsp").forward(request, response);
    }

    private void editMyEventDetails(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ParseException {
        System.out.println("in edit_event_method::");
        HttpSession session = request.getSession(true);
        session.getAttribute("email");
        Integer eventId = Integer.parseInt(request.getParameter("eventId"));
System.out.println("eventId in editeventdetails method: " + eventId);
        String eventTitle = request.getParameter("eventTitle");
        String artistName = request.getParameter("artistName");
        Date startDate = new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("startDate"));
        Date endDate = new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("endDate"));
        Date startTime = new SimpleDateFormat("HH:mm").parse(request.getParameter("startTime"));
        Date endTime = new SimpleDateFormat("HH:mm").parse(request.getParameter("endTime"));
        String location = request.getParameter("location");
        String description = request.getParameter("description");
        Part filePart = request.getPart("eventImage");
        InputStream eventImage = filePart.getInputStream();
        String genre = request.getParameter("genre");

        Event event = new Event(eventTitle, artistName, startDate, endDate, startTime, endTime, location, description, eventImage, genre);
        eventService.editMyEventDetails(event,eventId);
        List<Event> anEventDetail = eventService.viewEventDetails(eventId);
        request.setAttribute("anEventDetail", anEventDetail);
        request.getRequestDispatcher("singleEvent.jsp").forward(request, response);
    }

    private void viewEventDetails(HttpServletRequest request, HttpServletResponse response) throws SQLException,
            ServletException, IOException {

        Integer eventId = Integer.parseInt(request.getParameter("eventId"));
        List<Event> anEventDetail = eventService.viewEventDetails(eventId);
        request.setAttribute("anEventDetail", anEventDetail);
        request.getRequestDispatcher("singleEvent.jsp").forward(request, response);
    }

    protected void insertEvent(HttpServletRequest request, HttpServletResponse response) throws ParseException,
            IOException, SQLException, ServletException {

        HttpSession session = request.getSession(true);
        String email = (String) session.getAttribute("email");
        int dbUserId = userService.getUserIdByEmail(email);

        String eventTitle = request.getParameter("eventTitle");
        String artistName = request.getParameter("artistName");
        Date startDate = new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("startDate"));
        Date endDate = new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("endDate"));
        Date startTime = new SimpleDateFormat("HH:mm").parse(request.getParameter("startTime"));
        Date endTime = new SimpleDateFormat("HH:mm").parse(request.getParameter("endTime"));
        String location = request.getParameter("location");
        String description = request.getParameter("description");
        Part filePart = request.getPart("eventImage");
        InputStream eventImage = filePart.getInputStream();
        String genre = request.getParameter("genre");

        Event event = new Event(eventTitle, artistName, startDate, endDate, startTime, endTime, location, description,
                eventImage, genre);
        eventService.insertEvent(event, dbUserId);

        List<Event> myEventList = eventService.viewMyEvents(dbUserId);
        request.setAttribute("myEventList", myEventList);
        request.getRequestDispatcher("allEvents.jsp").forward(request, response);
    }
}
