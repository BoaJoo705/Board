package com.board.jooboard.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.board.jooboard.dao.UserDao;
import com.board.jooboard.dto.CustomUserDetails;
import com.board.jooboard.vo.Users;

@Service
public class CustomUserDetailsService implements UserDetailsService{


    private final UserDao userDao;

    public CustomUserDetailsService(UserDao userDao){
        this.userDao = userDao;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername 접근!!!");
        System.out.println("username:"+username);
        Users users = new Users();
        try{
            users.setUserId(username);
            Users userData = userDao.findByUserId(users);
            System.out.println("userData!!! : "+userData);

            if(userData != null){
                System.out.println("userData가 존재함");
                return new CustomUserDetails(userData);
            }
        }catch (Exception e) {
            System.err.println("Exception caught: " + e.getMessage());

        }
        



        return null;
    }
    
}
