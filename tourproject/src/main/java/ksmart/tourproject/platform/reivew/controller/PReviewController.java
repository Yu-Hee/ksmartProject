package ksmart.tourproject.platform.reivew.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ksmart.tourproject.platform.reivew.dto.POpen;
import ksmart.tourproject.platform.reivew.dto.PReivewComment;
import ksmart.tourproject.platform.reivew.dto.PReview;
import ksmart.tourproject.platform.reivew.dto.PReviewReact;
import ksmart.tourproject.platform.reivew.dto.PReviewReport;
import ksmart.tourproject.platform.reivew.service.PReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping(value="/platform/review")
@RequiredArgsConstructor
@Slf4j
public class PReviewController {

	private final PReviewService pReviewService;
	


	/**
	 * 리뷰 신고 누적 개수
	 * @return
	 */
	@GetMapping("/total/list")
	public String reviewTotal(Model model) {
		
		List<PReviewReport> pReviewTotal = pReviewService.getPReviewReportTotal();
		log.info("pReviewTotal: {}",pReviewTotal);
		System.out.println("pReviewTotal"+pReviewTotal);
		
		model.addAttribute("title", "리뷰 신고 누적 조회");
		model.addAttribute("pReviewTotal", pReviewTotal);
		
		return "platform/review/reportTotal";
	}
	
	
	/**
	 * 신고 승인
	 */
	@PostMapping("/report/approve")
	public String modifyPReviewReport(PReviewReport report) {
		log.info("신고 승인 : {}", report);
		pReviewService.modifyPReviewReport(report);
		
		return "redirect:/platform/review/report/list";
	}
	
	/**
	 * 신고 승인 화면
	 */
	@GetMapping("/report/approve")
	public String reportApprove(@RequestParam(value="reportCode", required = false) String reportCode, Model model) {
		log.info("신고 승인 화면 reportApprove: {}", reportCode);
		PReviewReport reportInfo = pReviewService.getPReviewReportInfoById(reportCode);
		
		if(reportInfo == null) {
			System.out.println("reportInfo is null");
		}else {
			System.out.println("reportInfo : "+reportInfo);
		}
		
		model.addAttribute("title", "신고 승인 화면");
		model.addAttribute("reportInfo", reportInfo);
		
		return "platform/review/reportApprove";
	}
	
	
	/**
	 * 리뷰 신고 목록
	 */
	@GetMapping("/report/list")
	public String reportList(Model model) {
		
		List<PReviewReport> pReviewReport = pReviewService.getPReviewReport();
		log.info("pReviewReport: {}", pReviewReport);
		System.out.println("pReviewReport: "+pReviewReport);
		
		model.addAttribute("title", "상품리뷰신고목록");
		model.addAttribute("pReviewReport", pReviewReport);
		
		return "platform/review/reportList";
	}
	
	
	/**
	 * 답글 수정
	 */
	@PostMapping("/comment/open")
	public String modifyPReivewComment(PReivewComment comment) {
		log.info("답글 수정: {}", comment);
		pReviewService.modifyPReivewComment(comment);
		
		return "redirect:/platform/review/comment/list"; 
	}
	
	/**
	 * 답글 공개 여부 수정 화면
	 * @return
	 */
	@GetMapping("/comment/open")
	public String commentOpen(@RequestParam(value="commentCode") String commentCode, Model model) {
		log.info("답글수정화면 commentCode : {}", commentCode);
		PReivewComment commentInfo = pReviewService.getPReivewCommentInfoById(commentCode);
		List<POpen> pOpenList = pReviewService.getPOpenList();
		
		if(commentInfo == null){
			System.out.println("commentInfo is null");
		}else{
			System.out.println("commentInfo: "+commentInfo);
		}
		
		model.addAttribute("title", "답글수정화면");
		model.addAttribute("commentInfo", commentInfo);
		model.addAttribute("pOpenList", pOpenList);
		
		return "platform/review/commentOpen";
	}
	
	/**
	 * 답글 목록
	 * @return
	 */
	@GetMapping("/comment/list")
	public String commentList(Model model) {
		
		List<PReivewComment> pReivewComment = pReviewService.getPReivewComment();
		log.info("pReivewComment: {}", pReivewComment);
		System.out.println("pReivewComment: "+pReivewComment);
		
		model.addAttribute("title", "댓글 목록 리스트");
		model.addAttribute("pReivewComment", pReivewComment);
		
		return "platform/review/commentList";
	}
	
	
	
	/**
	 * 리뷰 좋아요 싫어요 기록 목록
	 */
	@GetMapping("/react")
	public String reviewReact(Model model) {
		
		List<PReviewReact> pReviewReact = pReviewService.getPReviewReact();
		log.info("pReviewReact: {}", pReviewReact);
		System.out.println("pReviewReact : " + pReviewReact);
		
		model.addAttribute("title", "좋아요 싫어요 기록 목록");
		model.addAttribute("pReviewReact", pReviewReact);
		
		return "platform/review/reviewReact";
	}
	
	
	
	
	/**
	 * 리뷰 수정
	 */
	  @PostMapping("/open") 
	  public String modifyPReview(PReview review) {
	  log.info("상품 리뷰 수정 : {}", review);

	  	pReviewService.modifyPReview(review);
	  
	  return "redirect:/platform/review/list"; 
	  }
	 
	/**
	 * 리뷰 공개 여부 수정화면(수정페이지)
	 * @return
	 */
	@GetMapping("/open")
	public String modifyPReview(@RequestParam("reviewCode") String reviewCode, Model model) {
		log.info("상품리뷰수정화면 reviewCode : {}", reviewCode);

		PReview reviewInfo = pReviewService.getPReviewInfoById(reviewCode);
		List<POpen> pOpenList = pReviewService.getPOpenList();
		
		if(reviewInfo == null){
			System.out.println("reviewInfo is null");
		}else{
			System.out.println("reviewInfo: "+reviewInfo);
		}
		
		
		model.addAttribute("title", "상품 리뷰 공개 여부 수정");
		model.addAttribute("reviewInfo", reviewInfo);
		model.addAttribute("pOpenList", pOpenList);
		
		
		return "platform/review/reviewOpen";
	}
	
	/**
	 * 리뷰 전체 목록
	 * @return
	 */
	@GetMapping("/list")
	public String getPReviewList(Model model) {
		
		List<PReview> pReviewList = pReviewService.getPReviewList();
		log.info("pReviewList: {}", pReviewList);
		System.out.println("pReviewList : " + pReviewList);
		
		model.addAttribute("title", "상품 리뷰 전체 목록");
		model.addAttribute("pReviewList", pReviewList);
		
		return "platform/review/PreviewList";
	}

}
