package com.board.jooboard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.jooboard.vo.Board;

@Mapper
public interface BoardDao {

    // 게시글 등록
	void insert(Board board);

	List<Board> selectBoardList();

}
