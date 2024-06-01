package ksmart.tourproject.user.review.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import ksmart.tourproject.user.review.dto.UOpen;
import ksmart.tourproject.user.review.dto.UReviewReportCategory;
import ksmart.tourproject.user.review.dto.UReview;
import ksmart.tourproject.user.review.dto.UReviewComment;
import ksmart.tourproject.user.review.dto.UReviewFile;
import ksmart.tourproject.user.review.dto.UReviewReport;

@Mapper
public interface UReviewMapper {
	//파일 업데이트
	public int addFile(List<UReviewFile> fileList); 
	//파일목록
	public List<UReviewFile> getFileList();
	//파일과 관련된 어떠한것
	public UReviewFile getFileInfoByIdx(String fileIdx);
	//파일 삭제
	public int deleteFileByIdx(String fileIdx);
	
	//리뷰신고카테고리
	List<UReviewReportCategory> getUReviewReportCategoryList();
	//리뷰 조회
	List<UReview> getUReview();
	//신고 작성
	void reviewReport(UReviewReport reviewReport);

	// 가장 큰 PRCHS_REV_CD 값을 조회하는 메서드 추가
    String getMaxPrchsRevCd();
    //가장 큰 REV_REPT_NO 값을 조회하는 메서드 추가
    String getMaxReviewReportCd();
	
	//리뷰작성
	void reviewWrite(UReview review);
	
	
	//리뷰상세페이지
	UReview getReviewDetail(String reviewId);
	//리뷰수정
	void modifyReview(UReview review);
	
	
	//공개여부조회
	List<UOpen> getUOpen();
	
	
	
	
	
	//댓글 조회
	List<UReviewComment> getUReviewComment();
	
	public List<UReviewFile> parseFileInfo(MultipartFile[] uploadfile);

}
