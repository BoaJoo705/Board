package com.board.jooboard.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.board.jooboard.dao.UserDao;
import com.board.jooboard.vo.Users;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService (UserDao userDao , BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userDao = userDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /* 회원 가입 */
    public void signup(Users users) {
        System.out.println("userService 진입 ");
        
        
        // 중복회원 뒷단에서 또 체크 
        if(this.dupliCheck(users) > 0){
            return;
        }
        
        // 비밀번호
        users.setUserPw(bCryptPasswordEncoder.encode(users.getUserPw()));

        // 사용자 롤추가
        users.setRole("USER");

        userDao.insert(users);
    }

    public int dupliCheck(Users users) {
        int dupliCheck = userDao.dupliCheck(users);
        return dupliCheck;
    }

    public int login(Users users) {
        int loginChk = userDao.login(users);
        return loginChk;
    }

    public Optional<Users> findOne(String insertedUserId) {
        Users users = new Users();
        users.setUserId(insertedUserId);
        return  userDao.findOne(users);
    }

    

}
