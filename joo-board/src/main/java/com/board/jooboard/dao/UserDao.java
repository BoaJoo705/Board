package com.board.jooboard.dao;

import org.apache.ibatis.annotations.Mapper;

import com.board.jooboard.vo.Users;

@Mapper
public interface UserDao {

    // 회원 등록
    void insert(Users users);

    int dupliCheck(Users users);

    int login(Users users);

}
