package com.board.jooboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.board.jooboard.service.BoardService;
import com.board.jooboard.vo.Board;
import com.board.jooboard.vo.BoardAttachment;


@RestController
@RequestMapping("/api")
// @CrossOrigin(origins = "http://allowed-origin.com")
public class BoardRestController {
	
	@Autowired
	private BoardService boardService;

	// private FileUtils fileUtils;
	
	// 게시글 작성
// 	@PostMapping(value = "/insert",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
// 	public void insertBoard(@RequestParam Board board,@RequestParam("file") MultipartFile file) throws Exception{
// 		System.out.println("insert에 들어오니????");
// 		// System.out.println("file :"+file);
// 		// List<BoardAttachment> files = fileUtils.uploadFiles(attachment.getFiles());
        
// 		// fileService.saveFiles(id, files);
// 		// System.out.println("board: "+board);
// 		// System.out.println("board: "+attachment);
// 		System.out.println("BoardRestController insertBoard 진입 ");

// 		// 파일이 있을경우 


// 		// boardService.insert(board);
// //		return new ResponseEntity<>(insertBoard,HttpStatus.CREATED);
// 	}
	
	@PostMapping(value = "/insert", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createPost(
			@RequestParam("title") String title,
			@RequestParam("content") String content,
			@RequestParam("file") MultipartFile file) {

				try {
					
				System.out.println("title: "+title);
				System.out.println("file: "+file.getOriginalFilename());
				System.out.println("file: "+file.getSize());
				System.out.println("file: "+file.getContentType());
				System.out.println("file: "+file.getName());
				Board board = new Board();
				BoardAttachment boardAttachment= new BoardAttachment();
				board.setBoardTitle(title);
				board.setBoardContent(content);
				// 여기서 제목, 내용, 첨부 파일을 사용하여 게시물을 생성하는 작업을 수행합니다.
				// 예를 들어, 파일 저장 경로, 데이터베이스 저장, 등의 작업을 수행합니다.
				boardService.insert(board,boardAttachment,file);

				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating post");
		// TODO: handle exception
				}
		// 성공적인 응답을 반환
		return ResponseEntity.ok("Post created successfully");
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

// @Controller
// @RequestMapping("/board")
// public class BoardRestController {

//     @Autowired
//     private BoardService boardService;

// 	private Board board;

//     @PostMapping("/add")
//     @ResponseBody
//     public String addBoard(@RequestParam("title") String title,
//                            @RequestParam("content") String content,
//                            @RequestParam("file") MultipartFile file) {
//         try {
// 					board.setBoardTitle(title);
// 					board.setBoardContent(content);
// 					boardService.insert(board);
// 					System.out.println("file: "+file);

//             // boardService.saveBoard(title, content, file);
//             return "게시물이 성공적으로 등록되었습니다.";
//         } catch (Exception e) {
//             return "게시물 등록 중 오류가 발생했습니다.";
//         }
//     }
// }
