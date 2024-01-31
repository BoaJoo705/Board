package com.board.jooboard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.jooboard.vo.Board;
import com.board.jooboard.vo.BoardAttachment;
import com.board.jooboard.vo.BoardComment;

@Mapper
public interface BoardDao {

    // 게시글 등록
	void insert(Board board);

	// 게시글 첨부파일 등록
	void attachInsert(BoardAttachment boardAttachment);

	// 게시글 리스트
	List<Board> selectBoardList();

	// 게시글 상세 조회
    Board selectBoardById(Board board);

	// 게시글 수정
    void update(Board board);

	// 게시글 삭제
    void delete(Board board);

	// 댓글 등록
	void commentWrite(BoardComment boardComment);

	// 댓글 리스트 목록
	List<BoardComment> selectBoardComment(BoardComment boardComment);

	// 댓글 수정 
    void contentUpdate(BoardComment boardComment);

	// 댓글 삭제
    void commentDelete(BoardComment boardComment);

}
