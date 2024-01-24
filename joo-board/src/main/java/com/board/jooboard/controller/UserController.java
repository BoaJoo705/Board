package com.board.jooboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    
    @RequestMapping("/signup")
    public String signup(){
        return "/signup/signup";
    }
}
