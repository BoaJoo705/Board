package com.board.jooboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.board.jooboard.service.BoardService;
import com.board.jooboard.vo.Board;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

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
	// @RequestMapping("/write/{id}")
	// public String detail(@RequestParam("id") int id) {
	// 	System.out.println("상세페이지 들어가짐");
	// 	Board board = new Board();
	// 	board.setBoardId(id);
	// 	board = boardService.selectBoardById(board);
	// 	System.out.println("board!!!!: "+board);
	// 	Request.addAttribute("board", board);
	// 	return "board/detail2";
		
	// }

	
	// @RequestMapping("/write/{id}")
	// public String detail(@PathVariable("id") int id, Model model) {
	// 	System.out.println("상세페이지 들어가짐");
	// 	Board board = new Board();
	// 	board.setBoardId(id);
	// 	board = boardService.selectBoardById(board);
	// 	System.out.println("board!!!!: " + board);
	// 	model.addAttribute("board", board);
	// 	return "board/detail2";
	// }

	// // 게시글 상세페이지
	// @RequestMapping("/write")
	// public String detail() {
	// 	System.out.println("상세페이지 들어가짐");
	// 	// Board board = new Board();
	// 	// board.setBoardId(id);
	// 	// board = boardService.selectBoardById(board);
	// 	// System.out.println("board!!!!: "+board);
	// 	// model.addAttribute("board", board);
	// 	return "board/detail";
		
	// }

	// 게시글 상세페이지
	@RequestMapping(value = {"/write/{id}", "/write"})
	public String detail(@PathVariable(value = "id", required = false) Integer id, Model model) {
    System.out.println("상세페이지 들어가짐");
	System.out.println("id:"+id);
    if (id != null) {
		System.out.println("id값 존재?");
        Board board = new Board();
        board.setBoardId(id);
        board = boardService.selectBoardById(board);
        System.out.println("board!!!!: " + board);
        model.addAttribute("board", board);
    }
    return "board/detail";
}

	
}
