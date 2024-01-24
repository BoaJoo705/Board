package com.board.jooboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.board.jooboard.dao.UserDao;
import com.board.jooboard.vo.Users;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public void signup(Users users) {
        userDao.insert(users);
    }

}
