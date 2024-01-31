package com.board.jooboard.dao;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.board.jooboard.vo.Users;

@Mapper
public interface UserDao {

    // 회원 등록
    void insert(Users users);

    // 회원 아이디 중복 체크 
    int dupliCheck(Users users);

    // 로그인
    int login(Users users);

    // username을 받아 DB테이블에서 회원을 조회하는 메소드
    Users findByUserId(Users users);

    // 아이디 있는지 체크 
    Optional<Users> findOne(Users users);

}
