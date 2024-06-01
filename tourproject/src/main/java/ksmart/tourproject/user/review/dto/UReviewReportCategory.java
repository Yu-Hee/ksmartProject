package ksmart.tourproject.user.review.dto;

import lombok.Data;

@Data
public class UReviewReportCategory {
	private String reviewReportCategoryCode; //리뷰신고카테고리코드
	private String reviewReportCategoryContent; //리뷰신고카테고리내용
	private String reviewReportCategoryAction; //리뷰신고카테고리활성화여부

}
