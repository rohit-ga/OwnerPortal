package com.ga.service;

import java.sql.SQLException;

import com.ga.model.User;

public interface IUserService {

    public boolean registerUser(User user) throws SQLException;
}
