package com.ljl.service;

import com.ljl.pojo.User;

public interface UserService {

    User login(String username,String password);

    User findById(Integer id);
}
