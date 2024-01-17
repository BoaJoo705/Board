package com.board.jooboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.board.jooboard.service.BoardService;
import com.board.jooboard.vo.Board;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/board")
public class BoardRestController {
	
	@Autowired
	private BoardService boardService;
	
	// 게시글 작성
	@PostMapping("/insert")
	public void insertBoard(@RequestBody Board board){
		
		System.out.println("board: "+board);
		System.out.println("BoardRestController insertBoard 진입 ");
		boardService.insert(board);
//		return new ResponseEntity<>(insertBoard,HttpStatus.CREATED);
	}
	
	// 게시글 리스트
	@RequestMapping("/list")
	public ModelAndView boardList(){
		ModelAndView mv = new ModelAndView("/board/board");
		
		List<Board> list = boardService.selectBoardList();
		mv.addObject("list",list);
		return mv;
	}
	
	
	
}
