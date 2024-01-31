package com.board.jooboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session.Cookie;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.board.jooboard.service.BoardService;
import com.board.jooboard.vo.Board;
import com.board.jooboard.vo.BoardAttachment;
import com.board.jooboard.vo.BoardComment;

import io.jsonwebtoken.security.Request;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

    // 게시글 리스트
	@RequestMapping("/boardList")
	public String index() {

	String name = SecurityContextHolder.getContext().getAuthentication().getName();


		return "board/board"+name;
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

			System.out.println("id값 존재");

			Board board = new Board();
			BoardAttachment boardAttachment = new BoardAttachment();
			BoardComment boardComment = new BoardComment();

			// board 데이터
			board.setBoardId(id);
			board = boardService.selectBoardById(board);

			// 첨부파일 데이터
			boardAttachment.setBoardId(board.getBoardId());
			boardAttachment.setDelYn("N");
			boardAttachment = boardService.selectBoardAttachById(boardAttachment);
			System.out.println("board!!!!: " + board);

			// 댓글 데이터 
			boardComment.setBoardId(id);
			boardComment.setDelYn("N");
			List<BoardComment> boardCommentList = boardService.selectBoardComment(boardComment);


			model.addAttribute("board", board);
			model.addAttribute("boardAttachment", boardAttachment);
			model.addAttribute("boardCommentList", boardCommentList);

		}
		return "/board/detail";
	}

	
}
