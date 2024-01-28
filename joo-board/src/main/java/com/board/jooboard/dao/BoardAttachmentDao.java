package com.board.jooboard.dao;

import org.apache.ibatis.annotations.Mapper;

import com.board.jooboard.vo.BoardAttachment;

@Mapper
public interface BoardAttachmentDao {
    // 첨부파일 등록
    void insert(BoardAttachment boardAttachment);
    
    BoardAttachment selectBoardAttachById(BoardAttachment boardAttachment);

    void delete(BoardAttachment boardAttachment);
}
