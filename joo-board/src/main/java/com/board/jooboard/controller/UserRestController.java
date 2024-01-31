package com.board.jooboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.board.jooboard.service.UserService;
import com.board.jooboard.vo.Result;
import com.board.jooboard.vo.Users;

@RestController
@ResponseBody
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    private UserService userService;

    public UserRestController(UserService userService){
        this.userService = userService;
    }


    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<Result> signup(@RequestBody Users users ){
        System.out.println("signup 컨트롤러 진입");
        userService.signup(users);

        // 잘몰랐던 부분 ********
		Result result = new Result();
		result.setCode("OK");
		result.setMessage("회원가입 되었습니다.");
		// 성공적인 응답을 반환
		return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 아이디 중복 검사
    @PostMapping("/dupliCheck")
    public ResponseEntity<Result> dupliCheck(@RequestBody Users users){
        System.out.println("dupliCheck 컨트롤러 진입");

        // 아이디 중복 검사
        int dupliCheck = userService.dupliCheck(users);

        Result result = new Result();
        if(dupliCheck >= 1){
            result.setCode("error");
            // result.setMessage("중복된 아이디가 있습니다.");

        }else{
            result.setCode("OK");
            // result.setMessage("중복된 아이디가 없습니다.");
        }
		// 성공적인 응답을 반환
		return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 로그인 
    @PostMapping("/login")
    public ResponseEntity<Result> login(@RequestBody Users users){
        System.out.println("signup 컨트롤러 진입");
        int loginChk = userService.login(users);

        // 잘몰랐던 부분 ********
		Result result = new Result();
        if(loginChk == 1){ 
            // 로그인 성공
            
            result.setCode("ok");
            // result.setMessage("로그인 되었습니다.");

        }else{
            // 로그인 실패
            result.setCode("error");
            // result.setMessage("아이디나 비밀번호를 다시 확인하세요.");
        }
		// 성공적인 응답을 반환
		return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
