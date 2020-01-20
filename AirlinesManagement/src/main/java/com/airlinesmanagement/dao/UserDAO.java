package com.airlinesmanagement.dao;

import com.airlinesmanagement.model.User;

import java.util.List;

public interface UserDAO {
    public int insertUser(User user);
    public boolean checkCredentials(User user);
    public int updateUser(User user);
    public int deleteUser(int userid);
    public List<User> showAllUsers();
    public User showUserDetails(int userid);
}
