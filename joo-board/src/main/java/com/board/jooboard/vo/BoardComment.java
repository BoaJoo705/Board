package com.board.jooboard.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class BoardComment {
    private int commentId;
	
	private String commentContent;
	
	private int boardId;
	
	private String userId;
	
	private String regDt;
	
	private String modDt;

    private String delYn;
	
    
    // 댓글 목록 조회 메서드
    public List<BoardComment> selectBoardComment(BoardComment boardComment) {
       
        return new ArrayList<>();
    }
    

}
