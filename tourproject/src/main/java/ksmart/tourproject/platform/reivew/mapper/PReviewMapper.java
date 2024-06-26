package ksmart.tourproject.platform.reivew.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.tourproject.platform.reivew.dto.POpen;
import ksmart.tourproject.platform.reivew.dto.PReivewComment;
import ksmart.tourproject.platform.reivew.dto.PReview;
import ksmart.tourproject.platform.reivew.dto.PReviewBusiness;
import ksmart.tourproject.platform.reivew.dto.PReviewReact;
import ksmart.tourproject.platform.reivew.dto.PReviewReport;
import ksmart.tourproject.platform.reivew.dto.PReviewReportCategory;

@Mapper
public interface PReviewMapper {
	
	
	//신고합계조회
	List<PReviewReport> getPReviewReportTotal();
	

	// 신고정보수정
	int modifyPReviewReport(PReviewReport report);
	//신고정보조회
	PReviewReport getPReviewReportInfoById(String reportCode);
	
	//신고목록조회
	List<PReviewReport> getPReviewReport();
	
	
	//댓글정보수정
	int modifyPReivewComment(PReivewComment comment);
	
	//댓글정보조회
	PReivewComment getPReivewCommentInfoById(String commentCode);
	
	//댓글목록조회
	List<PReivewComment> getPReivewComment();
	
	//신고카테고리
	List<PReviewReportCategory> getPReviewReportCategory();
	
	//좋아요싫어요기록목록조회
	List<PReviewReact> getPReviewReact();
	
	//상품정보
	List<PReviewBusiness> getPReviewBusiness();
	
	//리뷰정보수정 05.08
	int modifyPReview(PReview review);
	
	//리뷰정보조회 05.08
	PReview getPReviewInfoById(String reviewCode);
	
	//공개 조회 05.08
	List<POpen> getPOpenList();
	
	//리뷰목록조회
	List<PReview> getPReviewList();

}
