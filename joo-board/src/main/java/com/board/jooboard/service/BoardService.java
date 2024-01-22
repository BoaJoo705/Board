package com.board.jooboard.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.board.jooboard.dao.BoardAttachmentDao;
import com.board.jooboard.dao.BoardDao;
import com.board.jooboard.vo.Board;
import com.board.jooboard.vo.BoardAttachment;

import util.FileUtils;



@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;

	@Autowired
	private BoardAttachmentDao boardAttachmentDao;

	FileUtils fileUtils = new FileUtils();

	// 파일 업로드 경로 
	@Value("${upload.directory}")
	String uploadFilePath ;

	@Transactional
	public void insert(Board board, BoardAttachment boardAttachment,MultipartFile file) {

		
		// board 테이블 저장
		board.setBoardId(0);
		board.setUserId("test1");
		board.setDelYn("N");
		board.setBoardCnt(0);
		board.setAtchYn("N");
		// 첨부파일 있을시
		if (!file.isEmpty()) {
			board.setAtchYn("Y");
		}
		boardDao.insert(board); 

		if (!file.isEmpty()) {
			// board_id값을 board_attachment테이블에 넣기
			boardAttachment.setBoardId(board.getBoardId());
		
			// 파일 DB 저장
			this.attachInsert(boardAttachment,file);

			// 파일 업로드 
			this.fileupload(boardAttachment,file);
		}	
		
	}

	// 상세게시판 조회
	public Board selectBoardById(Board board){
		System.out.println("selectBoardById 상세게시판 조회");
		board = boardDao.selectBoardById(board);
		return board;
	}

	// 파일 DB 저장
	public void attachInsert(BoardAttachment boardAttachment,MultipartFile file){
		// BoardAttachment boardAttachment = new BoardAttachment();
		// // 파일 DB 저장
		try{
			boardAttachment.setBoardId(boardAttachment.getBoardId()); //임시 게시판 번호 저장
			boardAttachment.setFilePath(uploadFilePath);
			boardAttachment.setOriFileName(file.getOriginalFilename());

			// 테이블에 저장할 파일 변환
			String saveFileName = fileUtils.generateSaveFilename(file.getOriginalFilename()); 
			boardAttachment.setStoredFileName(saveFileName);
			boardAttachment.setFileSize(file.getSize()); // 파일 사이즈
			boardAttachment.setExtNm(fileUtils.generageSaveFileExtNm(file.getOriginalFilename())); // 파일 확장자
			System.out.println("boardAttachment: "+boardAttachment);
			boardAttachmentDao.insert(boardAttachment);

		}catch(Exception e) {
			e.printStackTrace();
		
		}
		
	}	
    
	// 파일 업로드
	public void fileupload(BoardAttachment boardAttachment,MultipartFile file){

		// 폴더 없으면 폴더 생성
		try {
			this.createFolder(uploadFilePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 파일 저장
		try {
			this.saveFile(boardAttachment,file, uploadFilePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 파일 저장
	public void saveFile(BoardAttachment boardAttachment,MultipartFile file, String uploadDir) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

		String projectRoot = System.getProperty("user.dir");
		System.out.println("projectRoot: "+projectRoot);


        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = file.getInputStream()) {
            Path filePath = uploadPath.resolve(boardAttachment.getStoredFileName());
            FileCopyUtils.copy(inputStream, Files.newOutputStream(filePath, StandardOpenOption.CREATE));
			System.out.println("파일 저장 완료");
        }
    }
	
	// 폴더 디렉토리 생성
	public void createFolder(String folderPath) throws Exception {
        Path path = Paths.get(folderPath);

        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
    }

	public List<Board> selectBoardList() {
		List<Board> selectBoardList = boardDao.selectBoardList();
		return selectBoardList;
	}

}