package com.board.jooboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.jooboard.dao.BoardDao;
import com.board.jooboard.vo.Board;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
    
	public void insert(Board board) {
		board.setBoardId(0);
		board.setUserId("test1");
		board.setDelYn("N");
		board.setBoardCnt(0);
		boardDao.insert(board); 
	}
    
	
	public List<Board> selectBoardList() {
		List<Board> selectBoardList = boardDao.selectBoardList();
		return selectBoardList;
	}

}