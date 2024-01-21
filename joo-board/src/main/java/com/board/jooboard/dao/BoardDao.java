package com.board.jooboard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.jooboard.vo.Board;
import com.board.jooboard.vo.BoardAttachment;

@Mapper
public interface BoardDao {

    // 게시글 등록
	void insert(Board board);

	void attachInsert(BoardAttachment boardAttachment);

	List<Board> selectBoardList();

}
