package ksmart.tourproject.platform.board.dto;

import lombok.Data;

@Data
public class PCommunity {
	
	private String postNum;
	private String postRegId;
	private String postCateType;
	private String postTitle;
	private String postContent;
	private int postInqCnt;
	private int commentTotal;
	private int likeTotal;
	private int dislikeTotal;
	private int reportTotal;
	private String postAct;
	private String postRegDate;
	private String postMdfDate;
	private String postDelDate;
	
	// 추가된 association을 위한 멤버 변수
	private PCommunity postCate;
	

}
