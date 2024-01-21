package com.board.jooboard.vo;

import lombok.Data;

@Data
public class BoardAttachment {
    private int attachId;
	
	private String oriFileName;
	
	private String storedFileName;
	
	private String extNm;
	
	private String filePath;
	
	private int boardId;
	
	private long fileSize;
	
	private String regDt;
	
	private String modDt;


	// @Builder
    // public BoardAttachment(String oriFileName, String storedFileName, long fileSize) {
    //     this.oriFileName = oriFileName;
    //     this.storedFileName = storedFileName;
    //     this.fileSize = fileSize;
    // }

    // public BoardAttachment() {
    // }

    // public void setPostId(int attachId) {
    //     this.attachId = attachId;
    // }
}
