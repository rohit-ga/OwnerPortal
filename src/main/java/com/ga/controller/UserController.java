package com.ga.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ga.model.Event;
import com.ga.model.User;
import com.ga.service.impl.EventServiceImpl;
import com.ga.service.impl.UserServiceImpl;
import com.ga.util.Constant;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    UserServiceImpl userService = new UserServiceImpl();
    EventServiceImpl eventService = new EventServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            doProcess(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        try {
            doProcess(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException, SQLException {

        String action = request.getParameter("action");

        if (action.equals("register")) {

            registerUser(request, response);
        } else if (action.equals("login")) {
            
            loginUser(request,response);
        }
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        boolean answer = userService.loginUser(email,password);
        
        if(answer){
            HttpSession session = request.getSession(true);
            session.setAttribute("email", email);
            request.setAttribute("message", Constant.LOGIN_SUCCESSFUL_MESSAGE);
            
            int dbUserId = userService.getUserIdByEmail(email);
            List<Event> myEventList = eventService.viewMyEvents(dbUserId);
            request.setAttribute("myEventList", myEventList);
            request.getRequestDispatcher("allEvents.jsp").forward(request, response);
        } else {
            request.setAttribute("message", Constant.LOGIN_ERROR_MESSAGE);
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws SQLException,
            ServletException, IOException {

        String fName = request.getParameter("fName");
        String lName = request.getParameter("lName");
        String sex = request.getParameter("sex");
        Long contact = Long.parseLong(request.getParameter("contact"));
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User(fName, lName, sex, contact, email, password);
        boolean answer = userService.registerUser(user);

        if (!answer) {
            request.setAttribute("message", Constant.REGESTRATION_ERROR_MESSAGE);
            request.getRequestDispatcher("registration.jsp").forward(request, response);
        } else {
            
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            
            request.setAttribute("message", Constant.REGESTRATION_SUCCESSFUL_MESSAGE);
            request.getRequestDispatcher("createEvent.jsp").forward(request, response);
        }
    }
}
