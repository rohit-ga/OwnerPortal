package com.ga.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ga.model.Event;
import com.ga.service.IEventService;
import com.ga.service.impl.EventServiceImpl;

@WebServlet("/photocontroller")
public class PhotoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Event dbEvent;
    public PhotoController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        Integer eventId = Integer.parseInt(request.getParameter("eventId"));
        IEventService eventService = new EventServiceImpl();
        
        dbEvent = eventService.getEventbyId(eventId);

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int nRead;
        byte[] data = new byte[8192];

        while ((nRead = dbEvent.getEventImage().read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();

        response.getOutputStream().write(buffer.toByteArray());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
    }
}
