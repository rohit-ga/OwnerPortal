package com.ga.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ga.model.User;
import com.ga.service.impl.UserServiceImpl;
import com.ga.util.Constant;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    UserServiceImpl userService = new UserServiceImpl();

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
            request.setAttribute("message", Constant.REGESTRATION_SUCCESSFUL_MESSAGE);
            request.getRequestDispatcher("createEvent.jsp").forward(request, response);
        }
    }
}
