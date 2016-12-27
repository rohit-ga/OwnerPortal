package com.ga.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    UserServiceImpl userService;
    EventServiceImpl eventService;
    
    public UserController() {
        userService = new UserServiceImpl();
        eventService = new EventServiceImpl();
    }

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
            loginUser(request, response);

        } else if (action.equals("editUser")) {
            editUserDetails(request, response);

        } else if (action.equals("logout")) {
            response.sendRedirect("home.jsp");
        }
    }

    private void validation(HttpServletRequest request, HttpServletResponse response) {

        
        
    }

    private void editUserDetails(HttpServletRequest request, HttpServletResponse response) throws SQLException,
            ServletException, IOException {

        HttpSession session = request.getSession(true);

        int dbUserId = userService.getUserIdByEmail((String) session.getAttribute("email"));

        Integer userId = Integer.parseInt(request.getParameter("userId"));

        String fName = request.getParameter("fName");
        String lName = request.getParameter("lName");
        String sex = request.getParameter("sex");
        Long contact = Long.parseLong(request.getParameter("contact"));
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User(fName, lName, sex, contact, email, password);

        userService.editUserDetails(user, userId);
        List<Event> myEventList = eventService.viewMyEvents(dbUserId);
        request.setAttribute("myEventList", myEventList);
        request.getRequestDispatcher("allEvents.jsp").forward(request, response);
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws SQLException,
            ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        boolean answer = userService.loginUser(email, password);

        if (answer) {
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

        Map<String, String> errors = new HashMap<String, String>();
        
        String fName = request.getParameter("fName");
        String lName = request.getParameter("lName");
        String sex = request.getParameter("sex");
        Long contact = Long.parseLong(request.getParameter("contact"));
        if (contact == null) {
            errors.put("errors", "Contact Should Not Be Blank");
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("registration.jsp").forward(request, response);
        }
        if(contact !=null && contact.SIZE < 10){
            errors.put("errors", "Minimum 10 Digits Required In Contact Field.");
        }
        String email = request.getParameter("email");
        if (email == null || "".equals(email)) {
//            throw new NullPointerException("Email is required for registration.");
            errors.put("errors", "Email Is Required For Registration.");
        }
        String password = request.getParameter("password");
        if (password == null || "".equals(password)) {
//            throw new NullPointerException("Password shouldn't be blank or contain space");
            errors.put("errors", "Password Shouldn't Be Blank Or Contain Space");
        }
        if (password != null && password.length() < 6) {
//            throw new ServletException("Password should be greater than 6 digit");
            errors.put("errors", "Password Should Be Greater Than 6 Digit");
        }

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
