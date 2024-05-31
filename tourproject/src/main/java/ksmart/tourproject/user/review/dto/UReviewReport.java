package ksmart.tourproject.user.review.dto;

import lombok.Data;

@Data
public class UReviewReport {
	private String reviewReportCode;//신고코드
	private String reviewReportUserId;//신고자 아이디
	private String reviewReportCategoryCode;//신고카테고리
	private String reviewReportReviewCode;//신고리뷰코드
	private String reviewReportPlatfomId;//승인자아이디
	private String reviewReportDate;//승인일자
	private String reviewReportAction;//승인여부
	private String reviewReportActionDate; //승인여부날짜
	private String reviewReportContent; //신고 내용
	private String contentId; //신고 내용
	
	private UReview reviewInfo;
	private UReivewReportCategory reviewReportCategoryInfo;
}
