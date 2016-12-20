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
import com.ga.model.User;
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
            fetchEventData(request, response);
        } else if (action.equals("editEvent")) {
            editMyEventDetails(request, response);
        } else if(action.equals("updateUser")){
            getUserIdByEventId(request,response);
        } 
    }
    
    private void getUserIdByEventId(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        
        Integer eventId = Integer.parseInt(request.getParameter("eventId"));
        int dbUserId = eventService.getUserIdByEventId(eventId);
        List<User> userDetails = userService.getUserDetails(dbUserId);
        request.setAttribute("userDetails", userDetails);
        request.getRequestDispatcher("setting.jsp").forward(request, response);
    }

    private void fetchEventData(HttpServletRequest request, HttpServletResponse response) throws SQLException,
            ServletException, IOException {

        Integer eventId = Integer.parseInt(request.getParameter("eventId"));
        List<Event> anEventDetail = eventService.viewEventDetails(eventId);
        request.setAttribute("anEventDetail", anEventDetail);
        request.setAttribute("eventId", eventId);
        request.getRequestDispatcher("updateEvent.jsp").forward(request, response);
    }

    private void editMyEventDetails(HttpServletRequest request, HttpServletResponse response) throws SQLException,
            ServletException, IOException, ParseException {
        HttpSession session = request.getSession(true);
        session.getAttribute("email");
        
        Integer eventId = Integer.parseInt(request.getParameter("eventId"));
        String eventTitle = request.getParameter("eventTitle");
        String artistName = request.getParameter("artistName");
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("startDate"));
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("endDate"));
        Date startTime = new SimpleDateFormat("HH:mm").parse(request.getParameter("startTime"));
        Date endTime = new SimpleDateFormat("HH:mm").parse(request.getParameter("endTime"));
        String location = request.getParameter("location");
        String description = request.getParameter("description");
        Part filePart = request.getPart("eventImage");
        InputStream eventImage = filePart.getInputStream();
        String genre = request.getParameter("genre");

        Event event = new Event(eventTitle, artistName, startDate, endDate, startTime, endTime, location, description,
                eventImage, genre);
        eventService.editMyEventDetails(event, eventId);
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
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("startDate"));
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("endDate"));
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
    
    /*protected void sendBlobObjToJsp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer eventId = Integer.parseInt(request.getParameter("eventId"));
        Event dbEvent = eventService.getEventbyId(eventId);

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int nRead;
        byte[] data = new byte[8192];

        while ((nRead = dbEvent.getEventImage().read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();

        response.getOutputStream().write(buffer.toByteArray());
    }*/
}
