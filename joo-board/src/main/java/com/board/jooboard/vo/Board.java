package com.board.jooboard.vo;

import lombok.Data;

@Data
public class Board {
	private int boardId;
	
	private String userId;
	
	private String boardTitle;
	
	private String boardContent;
	
	private int boardCnt;
	
	private String delYn;
	
	private String regDate;
	
	private String modDate;
	
	private String atchYn;
	
	
	
}

