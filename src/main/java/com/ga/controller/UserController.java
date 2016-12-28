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

/**
 * The Class UserController.
 */
@WebServlet("/usercontroller")
public class UserController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    UserServiceImpl userService;

    EventServiceImpl eventService;

    /**
     * Instantiates a new user controller.
     */
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

    /**
     * Do process.
     *
     * @param request the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws SQLException the SQL exception
     */
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException, SQLException {

        String action = request.getParameter("action");

        if (action.equals(Constant.REGISTER_USER)) {
            registerUser(request, response);

        } else if (action.equals(Constant.LOGIN_USER)) {
            loginUser(request, response);

        } else if (action.equals(Constant.EDIT_USER)) {
            editUserDetails(request, response);

        } else if (action.equals(Constant.LOGOUT)) {
            redirectToHomePage(request,response);
            
        }
    }

    private void redirectToHomePage(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        logout from the current page
        HttpSession session = request.getSession(false);
        if(session!=null)
            session.invalidate();
        response.sendRedirect("home.jsp");
//            session.setAttribute("email", session.getAttribute("email"));
    }

    /**
     * Edits the user details.
     *
     * @param request the request
     * @param response the response
     * @throws SQLException the SQL exception
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private void editUserDetails(HttpServletRequest request, HttpServletResponse response) throws SQLException,
            ServletException, IOException {
//        editing user personal details
        Map<String, String> errors = new HashMap<String, String>();
        
        HttpSession session = request.getSession(true);

        int dbUserId = userService.getUserIdByEmail((String) session.getAttribute("email"));

        Integer userId = Integer.parseInt(request.getParameter("userId"));

        String fName = request.getParameter("fName");
        String lName = request.getParameter("lName");
        String sex = request.getParameter("sex");
        Long contact = Long.parseLong(request.getParameter("contact"));
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        checkParameters(request, response, errors, contact, email, password);

        User user = new User(fName, lName, sex, contact, email, password);

        userService.editUserDetails(user, userId);
        List<Event> myEventList = eventService.viewMyEvents(dbUserId);
        request.setAttribute("myEventList", myEventList);
        request.getRequestDispatcher("allEvents.jsp").forward(request, response);
    }

    /**
     * Login user.
     *
     * @param request the request
     * @param response the response
     * @throws SQLException the SQL exception
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws SQLException,
            ServletException, IOException {
//        for login purpose
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        boolean answer = userService.loginUser(email, password);

        if (answer) {
            // if login credentials are correct
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

    /**
     * Register user.
     *
     * @param request the request
     * @param response the response
     * @throws SQLException the SQL exception
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
//        registration of new user
        Map<String, String> errors = new HashMap<String, String>();

        String fName = request.getParameter("fName");
        String lName = request.getParameter("lName");
        String sex = request.getParameter("sex");
        Long contact = Long.parseLong(request.getParameter("contact"));
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        checkParameters(request, response, errors, contact, email, password);

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

    private void checkParameters(HttpServletRequest request, HttpServletResponse response, Map<String, String> errors,
            Long contact, String email, String password) throws ServletException, IOException {

        if (contact == null) {
            errors.put("errors", Constant.BLANK_CONTACT);
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("registration.jsp").forward(request, response);
        }

        if (contact != null && contact.longValue() < 10) {
            errors.put("errors", Constant.LESS_CONTACT_DIGIT);
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("registration.jsp").forward(request, response);
        }

        if (email == null || "".equals(email)) {
            errors.put("errors", Constant.BLANK_EMAIL);
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("registration.jsp").forward(request, response);
        }

        if (password == null || "".equals(password)) {
            errors.put("errors", Constant.BLANK_PASSWORD_WITH_SPACE);
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("registration.jsp").forward(request, response);
        }

        if (password != null && password.length() < 6) {
            errors.put("errors", Constant.LESS_PASSWORD_CHAR);
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("registration.jsp").forward(request, response);
        }
    }
}
