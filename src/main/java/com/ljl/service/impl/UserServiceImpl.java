package com.ljl.service.impl;

import com.ljl.dao.UserMapper;
import com.ljl.pojo.User;
import com.ljl.pojo.UserExample;
import com.ljl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(password);
        List<User> list = userMapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public User findById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
