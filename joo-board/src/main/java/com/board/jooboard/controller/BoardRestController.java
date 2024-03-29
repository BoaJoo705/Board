package com.board.jooboard.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.board.jooboard.service.BoardService;
import com.board.jooboard.vo.Board;
import com.board.jooboard.vo.BoardAttachment;
import com.board.jooboard.vo.BoardComment;
import com.board.jooboard.vo.Result;


@RestController
@RequestMapping("/api")
// @CrossOrigin(origins = "http://allowed-origin.com")
public class BoardRestController {
	
	@Autowired
	private BoardService boardService;


	// 게시글 등록
	@RequestMapping(value = "/insert")
	public ResponseEntity<Result> createPost(
			@RequestParam("title") String title,
			@RequestParam("content") String content,
			@RequestParam("file") MultipartFile file) {

				try {
					
					System.out.println("insert title: "+title);
					System.out.println("insert content: "+content);
					System.out.println("insert file: "+file.getOriginalFilename());
					System.out.println("insert file: "+file.getSize());
					System.out.println("insert file: "+file.getContentType());
					System.out.println("insert file: "+file.getName());
					Board board = new Board();
					BoardAttachment boardAttachment= new BoardAttachment();
					board.setBoardTitle(title);
					board.setBoardContent(content);
					// 여기서 제목, 내용, 첨부 파일을 사용하여 게시물을 생성하는 작업을 수행합니다.
					// 예를 들어, 파일 저장 경로, 데이터베이스 저장, 등의 작업을 수행합니다.
					boardService.insert(board,boardAttachment,file);

				} catch (Exception e) {
					Result result = new Result();
					result.setCode("500");
					result.setMessage(e.getMessage());
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
				}
		System.out.println("insert success!");

		// 잘몰랐던 부분 ********
		Result result = new Result();
		result.setCode("OK");
		result.setMessage("게시글을 등록하였습니다.");
		// 성공적인 응답을 반환
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}


	// 게시글 수정
	@RequestMapping("/update")
	public ResponseEntity<Result> update(
			@RequestParam("boardId") int boardId,
			@RequestParam("title") String title,
			@RequestParam("content") String content,
			@RequestParam("atchYn") String atchYn,
			@RequestParam("attachId") int attachId,
			@RequestParam("imgDeleteYn") String imgDeleteYn,
			@RequestParam("file") MultipartFile file){
		

		System.out.println("게시글 수정 로직 CONTROLLER");
				
		// Board 테이블 수정 로직
		Board board = new Board();
		BoardAttachment boardAttachment= new BoardAttachment();
		board.setBoardId(boardId);
		board.setBoardTitle(title);
		board.setBoardContent(content);
		board.setAtchYn(atchYn);
		board.setImgDeleteYn(imgDeleteYn);
		boardAttachment.setAttachId(attachId);

		// 여기서 제목, 내용, 첨부 파일을 사용하여 게시물을 생성하는 작업을 수행합니다.
		// 예를 들어, 파일 저장 경로, 데이터베이스 저장, 등의 작업을 수행합니다.
		boardService.update(board,boardAttachment,file);		

		Result result = new Result();
		result.setCode("OK");
		result.setMessage("게시글을 수정하였습니다.");
		// 성공적인 응답을 반환
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}


	// 게시글 삭제
	@RequestMapping("/delete")
	public ResponseEntity<Result> delete(
	@RequestParam("boardId") int boardId){

		System.out.println("게시글 삭제 로직 CONTROLLER");

		Board board = new Board();
		board.setBoardId(boardId);
		boardService.delete(board);

		Result result = new Result();
		result.setCode("OK");
		result.setMessage("게시글을 삭제하였습니다.");
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}


	// 게시글 리스트
	@RequestMapping("/list")
	public ModelAndView boardList(){
		ModelAndView mv = new ModelAndView("/board/board");
		
		List<Board> list = boardService.selectBoardList();

		// 사용자 아이디 
		String name = SecurityContextHolder.getContext().getAuthentication().getName();

		mv.addObject("name", name);

		mv.addObject("list",list);
		return mv;
	}


	// 댓글 등록
	@RequestMapping("/commentWrite")
	public ResponseEntity<Result> commentWrite(
		@RequestParam("boardId") int boardId,
		@RequestParam("commentContent") String commentContent){

		System.out.println("게시글 댓글 로직 CONTROLLER");

		BoardComment boardComment = new BoardComment();
		boardComment.setBoardId(boardId);
		boardComment.setCommentContent(commentContent);
		boardComment.setDelYn("N");
		boardService.commentWrite(boardComment);

		Result result = new Result();
		result.setCode("OK");
		result.setMessage("댓글을 등록하였습니다.");
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	
	// 댓글 수정
	@RequestMapping("/contentUpdate")
	public ResponseEntity<Result> contentUpdate(
		@RequestParam("commentId") int commentId,
		@RequestParam("commentContent") String commentContent){

		System.out.println("게시글 댓글 수정 로직 CONTROLLER");

		BoardComment boardComment = new BoardComment();
		boardComment.setCommentId(commentId);
		boardComment.setCommentContent(commentContent);

		boardService.contentUpdate(boardComment);

		Result result = new Result();
		result.setCode("OK");
		result.setMessage("댓글을 수정하였습니다.");
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	// 댓글 삭제
	@RequestMapping("/commentDelete")
	public ResponseEntity<Result> commentDelete(
		@RequestParam("commentId") int commentId){

		System.out.println("게시글 댓글 삭제 로직 CONTROLLER");

		BoardComment boardComment = new BoardComment();
		boardComment.setCommentId(commentId);
		boardComment.setDelYn("Y");
		
		boardService.commentDelete(boardComment);

		Result result = new Result();
		result.setCode("OK");
		result.setMessage("댓글을 삭제하였습니다.");
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	
}

