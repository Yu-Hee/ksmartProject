package ksmart.tourproject.user.review.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import ksmart.tourproject.user.review.dto.UReivewReportCategory;
import ksmart.tourproject.user.review.dto.UReview;
import ksmart.tourproject.user.review.dto.UReviewComment;
import ksmart.tourproject.user.review.dto.UReviewFile;
import ksmart.tourproject.user.review.dto.UReviewReport;
import ksmart.tourproject.user.review.service.UReviewService;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping(value="/user/review")
@Slf4j
public class UReviewController {
	
	private final UReviewService uReviewService;
	
	@Value("${files.path}")
	private String filePath;
	 
	private static final Logger log = LoggerFactory.getLogger(UReviewController.class);
	
	public UReviewController(UReviewService uReviewService) {
		this.uReviewService = uReviewService;
	}
	
	
	/**
	 * 리뷰작성화면
	 * @param model
	 * @param session
	 * @return
	 */
	/*		@GetMapping("/write")
			public String reviewWrite(Model model, HttpSession session) {
				// 세션에서 로그인된 사용자의 ID를 가져옴
				String loginId = (String) session.getAttribute("loginId");
				
				if (loginId != null) {
					// 로그인된 경우
					List<UReview> reviewList = uReviewService.getUReviewList();
					System.out.println("reviewList: " + reviewList);
					
					// 세션에 저장된 로그인 아이디를 모델에 추가하여 HTML 페이지에서 사용할 수 있도록 함
					model.addAttribute("loginId", loginId);
					model.addAttribute("title", "리뷰작성");
					model.addAttribute("reviewList", reviewList);
					
					return "user/review/reviewWrite";
				} else {
					// 로그인되지 않은 경우 로그인 페이지로 리다이렉트
					return "redirect:/trip"; // 로그인 페이지 경로로 변경
				}
			}*/
		 
	/**
	 * 리뷰 작성 화면
	 * @return
	 */
	@GetMapping("/write") 
	public String reviewWrite(@RequestParam String contentId, Model model) {
		
		model.addAttribute("contentId", contentId);
		
	    List<UReview> reviewList = uReviewService.getUReviewList();
	    log.info("reviewList: {} ",reviewList);


	    model.addAttribute("title", "리뷰작성");
	    model.addAttribute("reviewList", reviewList);

		
		return "user/review/reviewWrite"; 
	}
	
	/**
	 * 리뷰작성
	 * @param review
	 * @return
	 */
	@PostMapping("/write")
	public String reviewWrite(@RequestParam String contentId, UReview review, HttpServletRequest request, HttpSession session, Model model, @RequestParam(required = false) MultipartFile[] uploadfile) {
		
	    // 세션에서 로그인 ID 가져오기
	    String loginId = (String) session.getAttribute("loginId");
	    model.addAttribute("reviewId", loginId);
		
		model.addAttribute("contentId", contentId);
		
		log.info("리뷰 작성 화면에서 입력받은 data: {}",review);
		log.info("입력받은 file data: {}",Arrays.toString(uploadfile));
		
		uReviewService.reviewWrite(review, uploadfile); //리뷰 데이터를 db에 저장
		
		return "redirect:/user/review/list";
	}
	
	/**
	 * 신고작성
	 * @return
	 */
	@PostMapping("/list")
	public String reviewReport(UReviewReport reviewReport, HttpServletRequest request) {
		log.info("신고 모달 화면에서 입력받은 data: {}", reviewReport);

		uReviewService.reviewReport(reviewReport);
		
		return "redirect:/user/review/list";
	}
	
	
	/**
	 * 리뷰 목록 조회
	 * @param model
	 * @return
	 */
	@GetMapping("/list")
	public String reviewList(Model model, Principal principal) {
		
		List<UReview> uReviewList = uReviewService.getUReviewList();
		List<UReviewComment> uReviewCommentList = uReviewService.getUReveiwComment();
		List<UReviewFile> uReviewFileList = uReviewService.getFileList();
		List<UReivewReportCategory> uReviewReportList = uReviewService.getReviewReportCategory();
		
		//log.info("uReview: {}", uReviewList);
		//log.info("uReviewCommentList: {}", uReviewCommentList);
		//log.info("uReviewFileList: {}",uReviewFileList);
		//log.info("uReviewReportList: {}",uReviewReportList);
		
		model.addAttribute("title", "상품 후기 목록");
		model.addAttribute("uReviewList", uReviewList);
		model.addAttribute("uReviewCommentList", uReviewCommentList);
		model.addAttribute("uReviewFileList", uReviewFileList);
		model.addAttribute("uReviewReportList", uReviewReportList);
        
		// 로그인 여부 확인
		boolean isLoggedIn = (principal != null);
		model.addAttribute("isLoggedIn", isLoggedIn);
		
		return "user/review/reviewList";
	}

		
		
		
	/**
	 * 리뷰 상세 내용
	 * @return
	 */
	@GetMapping("/detail")
	public String reviewDetail() {
		return "user/review/reviewdetail";
	}
	
	

	
	/**
	 * 파일 json
	 */
	@GetMapping("/file/json")
	@ResponseBody
	public List<UReviewFile> reviewFileJsonView(Model model){
		
		List<UReviewFile> uReviewFileList = uReviewService.getFileList();
		log.info("uReviewFileList: {}", uReviewFileList);
		
		model.addAttribute("title","리뷰 파일 목록");
		model.addAttribute("uReviewFileList",uReviewFileList);
		
		return uReviewFileList;
	}

	
	
	/**
	 * 리뷰 json
	 * @param model
	 * @return
	 */
	/*
	@GetMapping("/json")
    @ResponseBody
	    public ResponseEntity<List<UReview>> reviewJsonView() {
	        List<UReview> uReviewList = uReviewService.getUReviewList();
	        log.info("uReview: {}", uReviewList);
	        return ResponseEntity.ok(uReviewList);
	    
	}
	 */
	
	@GetMapping("/json")
	@ResponseBody //반환값을 HTTP로 직접 전송 json 으로 반환
	public List<UReview> reviewJsonView(Model model) {
		
		List<UReview> uReviewList = uReviewService.getUReviewList();
		log.info("uReview: {}", uReviewList);
		
		model.addAttribute("title", "상품 후기 목록");
		model.addAttribute("uReviewList", uReviewList);
		
		
		return uReviewList;
	}
	
	
	/**
	 * 답글 json
	 * @param model
	 * @return
	 */
	@GetMapping("/comment/json")
	@ResponseBody //반환값을 HTTP로 직접 전송 json 으로 반환
	public List<UReviewComment> reviewCommentJsonView(Model model) {
		
		List<UReviewComment> uReviewCommentList = uReviewService.getUReveiwComment();
		//log.info("getUReveiwComment: {}", uReviewCommentList);
		
		model.addAttribute("title", "리뷰 답글 목록");
		model.addAttribute("getUReveiwComment", uReviewCommentList);
		
		
		return uReviewCommentList;
	}
	
	

	
	@GetMapping("/list2")
	public String reviewList2() {
		return "user/review/reviewList2";
	}
	
	@GetMapping("/list3")
	public String reviewList3() {
		return "user/review/reviewList3";
	}
	
	@GetMapping("/list4")
	public String reviewList4() {
		return "user/review/reviewList4";
	}
	@GetMapping("/list5")
	public String reviewList5() {
		return "user/review/reviewList5";
	}
	
}
