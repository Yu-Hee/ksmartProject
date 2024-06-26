package ksmart.tourproject.platform.reivew.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.tourproject.platform.reivew.dto.POpen;
import ksmart.tourproject.platform.reivew.dto.PReivewComment;
import ksmart.tourproject.platform.reivew.dto.PReview;
import ksmart.tourproject.platform.reivew.dto.PReviewReact;
import ksmart.tourproject.platform.reivew.dto.PReviewReport;
import ksmart.tourproject.platform.reivew.mapper.PReviewMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PReviewService {

	private final PReviewMapper pReviewMapper;

	/**
	 * 신고 누적 개수 리스트
	 */
	public List<PReviewReport> getPReviewReportTotal() {
		List<PReviewReport> pReviewReportTotal = pReviewMapper.getPReviewReportTotal();
		
		
		
		return pReviewReportTotal;
	}

	/**
	 * 신고 정보 수정
	 */
	public int modifyPReviewReport(PReviewReport report) {
		
		try {
			return pReviewMapper.modifyPReviewReport(report);
	    } catch (Exception e) {
	        // 실패 시에는 -1을 반환합니다.
	        return -1;
	    }
	}
	
	/**
	 * 신고 정보 조회
	 */
	public PReviewReport getPReviewReportInfoById(String reportCode) {
		PReviewReport reportInfo = pReviewMapper.getPReviewReportInfoById(reportCode);
		return reportInfo;
	}

	/**
	 * 신고 목록 리스트
	 * 
	 * @return
	 */
	public List<PReviewReport> getPReviewReport() {
		List<PReviewReport> pReportList = pReviewMapper.getPReviewReport();
		if (pReportList != null) {
			pReportList.forEach(report -> {
				String reportApprove = report.getReportApprove();
				String reportApproveName = "";
				switch (reportApprove) {
				case "Y":
					reportApproveName = "승인";
					break;
				case "N":
					reportApproveName = "미승인";
					break;
				}
				report.setReportApproveName(reportApproveName);
			});
		}
		return pReportList;
	}
	
	
	/**
	 * 댓글정보수정
	 */
	public int modifyPReivewComment(PReivewComment comment) {
		try {
		return pReviewMapper.modifyPReivewComment(comment);
	    } catch (Exception e) {
	        // 실패 시에는 -1을 반환합니다.
	        return -1;
	    }
	}

	/**
	 * 댓글정보조회
	 */
	public PReivewComment getPReivewCommentInfoById(String commentCode) {
		PReivewComment commentInfo = pReviewMapper.getPReivewCommentInfoById(commentCode);

		return commentInfo;
	}

	/**
	 * 댓글 목록 리스트
	 */
	public List<PReivewComment> getPReivewComment() {

		List<PReivewComment> pCommentList = pReviewMapper.getPReivewComment();

		if (pCommentList != null) {
			pCommentList.forEach(review -> {
				String commentApprove = review.getCommentApprove();
				String commentApproveName = "";
				switch (commentApprove) {
				case "DISCLOSURE_001":
					commentApproveName = "전체공개";
					break;
				case "DISCLOSURE_002":
					commentApproveName = "나만공개";
					break;
				}
				review.setCommentApproveName(commentApproveName);
			});
		}
		return pCommentList;
	}
	

	/**
	 * 좋아요 싫어요 기록 목록 리스트
	 */
	public List<PReviewReact> getPReviewReact() {
		return pReviewMapper.getPReviewReact();
	}
	

	/**
	 * 리뷰정보수정
	 */
	  public int modifyPReview(PReview review) {
		  return pReviewMapper.modifyPReview(review);
	  }
	 

	/**
	 * 리뷰정보조회
	 */
	public PReview getPReviewInfoById(String reviewCode) {
		PReview reviewInfo = pReviewMapper.getPReviewInfoById(reviewCode);
		return reviewInfo;
	}

	/**
	 * 공개 조회
	 */
	public List<POpen> getPOpenList() {
		return pReviewMapper.getPOpenList();
	}

	/**
	 * 리뷰 목록 리스트
	 * 
	 * @return
	 */
	public List<PReview> getPReviewList() {
		List<PReview> pReviewList = pReviewMapper.getPReviewList();

		if (pReviewList != null) {
			pReviewList.forEach(review -> {
				String reviewApprove = review.getReviewApprove();
				String reviewApproveName = "";
				String reviewStar = review.getReviewStar();
				String reviewStarPoint = "";
				switch (reviewApprove) {
				case "DISCLOSURE_001":
					reviewApproveName = "전체공개";
					break;
				case "DISCLOSURE_002":
					reviewApproveName = "나만공개";
					break;
				}
				review.setReviewApproveName(reviewApproveName);
				switch (reviewStar) {
				case "RAT_CTGRY_01":
					reviewStarPoint = "1점";
					break;
				case "RAT_CTGRY_02":
					reviewStarPoint = "2점";
					break;
				case "RAT_CTGRY_03":
					reviewStarPoint = "3점";
					break;
				case "RAT_CTGRY_04":
					reviewStarPoint = "4점";
					break;
				case "RAT_CTGRY_05":
					reviewStarPoint = "5점";
					break;
				}
				review.setReviewStarPoint(reviewStarPoint);
			});
		}
		return pReviewList;
	}
}
