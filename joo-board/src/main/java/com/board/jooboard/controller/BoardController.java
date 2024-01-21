package com.board.jooboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
    // 게시글 리스트
	@RequestMapping("/boardList")
	public String index() {
		return "board/board";
	}
	
	@RequestMapping("/main")
	public String main(){
		return "main/main";
	}
	
	// 게시글 상세페이지
	@RequestMapping("/write")
	public String detail() {
		System.out.println("상세페이지 들어가짐");
		return "board/detail";
	}
}
