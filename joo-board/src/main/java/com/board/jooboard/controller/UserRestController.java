package com.board.jooboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.board.jooboard.service.UserService;
import com.board.jooboard.vo.Result;
import com.board.jooboard.vo.Users;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Result> signup(@RequestBody Users users){
        System.out.println("signup 컨트롤러 진입");
        userService.signup(users);

        // 잘몰랐던 부분 ********
		Result result = new Result();
		result.setCode("OK");
		result.setMessage("회원가입 되었습니다.");
		// 성공적인 응답을 반환
		return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
