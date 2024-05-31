package ksmart.tourproject.user.destination.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import ksmart.tourproject.exception.EDException;
import ksmart.tourproject.user.destination.dto.TourInformation;
import ksmart.tourproject.user.destination.dto.TourInformationResponse;
import ksmart.tourproject.user.destination.dto.TourItem;
import ksmart.tourproject.user.destination.dto.TourItemResponse;
import ksmart.tourproject.user.destination.dto.TourList;
import ksmart.tourproject.user.destination.dto.TourListResponse;
import ksmart.tourproject.user.review.dto.UReivewReportCategory;
import ksmart.tourproject.user.review.dto.UReviewReport;
import ksmart.tourproject.user.review.service.UReviewService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value="/user/destination")
@Slf4j
public class destinationController {
	
private final UReviewService uReviewService;
	
	public destinationController(UReviewService uReviewService) {
		this.uReviewService = uReviewService;
	}
	
	/**
	 * 신고작성
	 * @return
	 */
	@PostMapping("/lodgingCheckDetails")
	public String lodgingreviewReport(@RequestParam(name = "contentId", required = false) String contentId, UReviewReport reviewReport, HttpServletRequest request) {
		log.info("신고 모달 화면에서 입력받은 data: {}", reviewReport);

		uReviewService.reviewReport(reviewReport);
		
		return "redirect:/user/destination/lodgingCheckDetails?contentId=" + contentId;
	}
	
	/**
	 * 신고작성
	 * @return
	 */
	
	@PostMapping("/restaurantCheckDetails")
	public String restaurantreviewReport(@RequestParam(name = "contentId", required = false) String contentId, UReviewReport reviewReport, HttpServletRequest request) {
		log.info("신고 모달 화면에서 입력받은 data: {}", reviewReport);

		uReviewService.reviewReport(reviewReport);
		
		return "redirect:/user/destination/restaurantCheckDetails?contentId=" + contentId;
	}
	
	/**
	 * 신고작성
	 * @return
	 */
	
	@PostMapping("tourCheckDetails")
	public String tourreviewReport(@RequestParam(name = "contentId", required = false) String contentId, UReviewReport reviewReport, HttpServletRequest request) {
		log.info("신고 모달 화면에서 입력받은 data: {}", reviewReport);

		uReviewService.reviewReport(reviewReport);
		
		return "redirect:/user/destination/tourCheckDetails?contentId=" + contentId;
	}
	
	
	/**
	 * 숙소
	 * @param model
	 * @return
	 * @throws EDException
	 */
	@GetMapping("/lodgingCheck")
	public String lodgingCheck(@RequestParam(name = "contentId", required = false) String contentId, Model model) throws EDException {
		StringBuilder result = new StringBuilder();
		
        String serviceKey = "HmjxL3ZwIR9BRISocvJb3ajCyCPzKPzt64QVyJUExpNDFEoSd96yRhkcF6ln23pFPYTSP3v15n23f092lrVAmg=="; // 실제 서비스 키를 입력하세요
        int numOfRows = 500; // 한 페이지당 가져올 항목 수
        int startPage = 1; // 시작 페이지 번호
        String urlStr = "http://apis.data.go.kr/B551011/KorService1/searchStay1" +
                        "?serviceKey=" + serviceKey +
	                    "&numOfRows=" + numOfRows + // 수정된 부분
	                    "&pageNo=" +
	                    "&MobileOS=ETC" +
	                    "&MobileApp=AppTest" +
	                    "&_type=json" +
	                    "&listYN=Y" +
	                    "&arrange=A";
        
        try {
            URL url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8))) {
                    String returnLine;
                    while ((returnLine = br.readLine()) != null) {
                        result.append(returnLine).append("\n");
                    }
                }
            } else {
                throw new EDException("API 요청 실패: 응답 코드 " + responseCode);
            }
            
            urlConnection.disconnect();
            
            // JSON 응답을 파싱하여 TourItemResponse 객체로 변환
            ObjectMapper mapper = new ObjectMapper();
            TourItemResponse tourItemResponse = mapper.readValue(result.toString(), TourItemResponse.class);
            
            // 모델에 TourItem 리스트 추가
            List<TourItem> tourItems = tourItemResponse.getResponse().getBody().getItems().getItemList();
            model.addAttribute("tourItems", tourItems);
        } catch (Exception e) {
            throw new EDException("Error occurred while calling the API", e);
        }

		model.addAttribute("title", "숙소 조회");
		
		return "user/destination/lodgingCheck";
	}
	
	/**
	 * 숙소 상제 정보
	 * @param contentId
	 * @param model
	 * @return
	 * @throws EDException
	 */
	@GetMapping("/lodgingCheckDetails")
	public String lodgingCheckDetails(@RequestParam(name = "contentId", required = false) String contentId, Model model) throws EDException {
		
	StringBuilder result = new StringBuilder();
        
        String serviceKey = "HmjxL3ZwIR9BRISocvJb3ajCyCPzKPzt64QVyJUExpNDFEoSd96yRhkcF6ln23pFPYTSP3v15n23f092lrVAmg=="; // 실제 서비스 키를 입력하세요
        int numOfRows = 100; // 한 페이지당 가져올 항목 수
        int startPage = 1; // 시작 페이지 번호
        String urlStr = "https://apis.data.go.kr/B551011/KorService1/detailCommon1" +
                "?serviceKey=" + serviceKey +
                "&MobileOS=ETC" +
                "&MobileApp=test" +
                "&_type=json" +
                "&contentId=" + contentId + // 콘텐츠 ID
                "&defaultYN=Y" + // 기본정보 조회 여부
                "&firstImageYN=Y" + // 대표이미지 조회 여부
                "&areacodeYN=Y" + // 지역코드 조회 여부
                "&catcodeYN=Y" + // 서비스분류코드 조회 여부
                "&addrinfoYN=Y" + // 주소 조회 여부
                "&mapinfoYN=Y" + // 좌표 조회 여부
                "&overviewYN=Y"; // 개요 조회 여부
        
        try {
            URL url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            System.out.println("Requesting URL: " + urlStr); // 요청 URL 로그

            int responseCode = urlConnection.getResponseCode();
            System.out.println("Response Code: " + responseCode); // 응답 코드 로그

            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8))) {
                    String returnLine;
                    while ((returnLine = br.readLine()) != null) {
                        result.append(returnLine).append("\n");
                    }
                }
                System.out.println("API Response: " + result.toString()); // API 응답 로그
            } else {
                throw new EDException("API 요청 실패: 응답 코드 " + responseCode);
            }

            urlConnection.disconnect();

            // 빈 문자열을 null로 대체
            String jsonResponse = result.toString();
            jsonResponse = jsonResponse.replace("\"items\": \"\"", "\"items\": null");

            // JSON 응답을 파싱하여 TourItemResponse 객체로 변환
            ObjectMapper mapper = new ObjectMapper();
            TourInformationResponse tourInformationResponse = mapper.readValue(jsonResponse, TourInformationResponse.class); // 수정
            System.out.println("Parsed Response: " + tourInformationResponse); // 파싱된 응답 로그

            // 모델에 TourItem 리스트 추가
            // 빈 문자열인 경우 빈 리스트 반환
            List<TourInformation> tourInformation;
            if (tourInformationResponse.getResponse().getBody().getItems() == null || tourInformationResponse.getResponse().getBody().getItems().getInformationList() == null) {
                tourInformation = Collections.emptyList();
            } else {
                tourInformation = tourInformationResponse.getResponse().getBody().getItems().getInformationList();
            }
            System.out.println("Tour Information List: " + tourInformation); // TourInformation 리스트 로그
            model.addAttribute("TourInformation", tourInformation);
        } catch (Exception e) {
            e.printStackTrace(); // 예외 스택 트레이스 출력
            throw new EDException("Error occurred while calling the API", e);
        }
        
        List<UReivewReportCategory> uReviewReportList = uReviewService.getReviewReportCategory();
        model.addAttribute("uReviewReportList", uReviewReportList);
        
        // model에 contentId 추가
        model.addAttribute("contentId", contentId);
        
		model.addAttribute("title", "숙소 세부사항");
		
		return "user/destination/lodgingCheckDetails";
	}
	
	
	
	
	/**
	 * 음식점
	 * @param model
	 * @return
	 * @throws EDException
	 */
	@GetMapping("/restaurantCheck")
	public String restaurantCheck(@RequestParam(name = "contentId", required = false) String contentId, Model model) throws EDException {
		
		StringBuilder result = new StringBuilder();
		
        String serviceKey = "HmjxL3ZwIR9BRISocvJb3ajCyCPzKPzt64QVyJUExpNDFEoSd96yRhkcF6ln23pFPYTSP3v15n23f092lrVAmg=="; // 실제 서비스 키를 입력하세요
        int contentTypeId = 39; // 콘텐츠 타입 ID
        String urlStr = "https://apis.data.go.kr/B551011/KorService1/areaBasedSyncList1" +
                "?serviceKey=" + serviceKey + // 서비스 키
                "&MobileOS=ETC" + // 모바일 OS
                "&MobileApp=1" + // 모바일 앱 이름
                "&_type=json" + // 응답 형식
                "&contentTypeId=" + contentTypeId+ // 콘텐츠 타입 ID
        		"&numOfRows=" + 500; // 한 페이지당 가져올 항목 수
        
        try {
            URL url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            int responseCode = urlConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8))) {
                    String returnLine;
                    while ((returnLine = br.readLine()) != null) {
                        result.append(returnLine).append("\n");
                    }
                }
            } else {
                throw new EDException("API 요청 실패: 응답 코드 " + responseCode);
            }

            urlConnection.disconnect();

            // 콘솔에 JSON 응답 출력
            System.out.println("API 응답: " + result.toString());

            // JSON 응답을 파싱하여 TourListResponse 객체로 변환
            ObjectMapper mapper = new ObjectMapper();
            TourListResponse tourListResponse = mapper.readValue(result.toString(), TourListResponse.class);

            // 모델에 TourList 리스트 추가
            List<TourList> tourList = tourListResponse.getResponse().getBody().getItems().getTourList();
            model.addAttribute("tourList", tourList);
        } catch (Exception e) {
            throw new EDException("Error occurred while calling the API", e);
        }
		
		model.addAttribute("title", "음식점 조회");
		
		return "user/destination/restaurantCheck";
	}
	
	
	
	
	
	/**
	 * 음식점 상세 정보
	 * @param contentId
	 * @param model
	 * @return
	 * @throws EDException
	 */
	
	@GetMapping("/restaurantCheckDetails")
	public String restaurantCheckDetails(@RequestParam(name = "contentId", required = false) String contentId, Model model) throws EDException {
		StringBuilder result = new StringBuilder();
        
        String serviceKey = "HmjxL3ZwIR9BRISocvJb3ajCyCPzKPzt64QVyJUExpNDFEoSd96yRhkcF6ln23pFPYTSP3v15n23f092lrVAmg=="; // 실제 서비스 키를 입력하세요
        int numOfRows = 100; // 한 페이지당 가져올 항목 수
        int startPage = 1; // 시작 페이지 번호
        String urlStr = "https://apis.data.go.kr/B551011/KorService1/detailCommon1" +
                "?serviceKey=" + serviceKey +
                "&MobileOS=ETC" +
                "&MobileApp=test" +
                "&_type=json" +
                "&contentId=" + contentId + // 콘텐츠 ID
                "&defaultYN=Y" + // 기본정보 조회 여부
                "&firstImageYN=Y" + // 대표이미지 조회 여부
                "&areacodeYN=Y" + // 지역코드 조회 여부
                "&catcodeYN=Y" + // 서비스분류코드 조회 여부
                "&addrinfoYN=Y" + // 주소 조회 여부
                "&mapinfoYN=Y" + // 좌표 조회 여부
                "&overviewYN=Y"; // 개요 조회 여부
        
        try {
            URL url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            System.out.println("Requesting URL: " + urlStr); // 요청 URL 로그

            int responseCode = urlConnection.getResponseCode();
            System.out.println("Response Code: " + responseCode); // 응답 코드 로그

            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8))) {
                    String returnLine;
                    while ((returnLine = br.readLine()) != null) {
                        result.append(returnLine).append("\n");
                    }
                }
                System.out.println("API Response: " + result.toString()); // API 응답 로그
            } else {
                throw new EDException("API 요청 실패: 응답 코드 " + responseCode);
            }

            urlConnection.disconnect();

            // 빈 문자열을 null로 대체
            String jsonResponse = result.toString();
            jsonResponse = jsonResponse.replace("\"items\": \"\"", "\"items\": null");

            // JSON 응답을 파싱하여 TourItemResponse 객체로 변환
            ObjectMapper mapper = new ObjectMapper();
            TourInformationResponse tourInformationResponse = mapper.readValue(jsonResponse, TourInformationResponse.class); // 수정
            System.out.println("Parsed Response: " + tourInformationResponse); // 파싱된 응답 로그

            // 모델에 TourItem 리스트 추가
            // 빈 문자열인 경우 빈 리스트 반환
            List<TourInformation> tourInformation;
            if (tourInformationResponse.getResponse().getBody().getItems() == null || tourInformationResponse.getResponse().getBody().getItems().getInformationList() == null) {
                tourInformation = Collections.emptyList();
            } else {
                tourInformation = tourInformationResponse.getResponse().getBody().getItems().getInformationList();
            }
            System.out.println("Tour Information List: " + tourInformation); // TourInformation 리스트 로그
            model.addAttribute("TourInformation", tourInformation);
        } catch (Exception e) {
            e.printStackTrace(); // 예외 스택 트레이스 출력
            throw new EDException("Error occurred while calling the API", e);
        }
        
        List<UReivewReportCategory> uReviewReportList = uReviewService.getReviewReportCategory();
        model.addAttribute("uReviewReportList", uReviewReportList);
        
        // model에 contentId 추가
        model.addAttribute("contentId", contentId);
		
		model.addAttribute("title", "음식점 세부사항");
		
		return "user/destination/restaurantCheckDetails";
	}
	

	
	
	/**
	 * 관광지 조회
	 * @param model
	 * @return
	 * @throws EDException
	 */
	@GetMapping("/tourCheck")
	public String tourCheck(@RequestParam(name = "contentId", required = false) String contentId, Model model) throws EDException {
		
		StringBuilder result = new StringBuilder();
		
        String serviceKey = "HmjxL3ZwIR9BRISocvJb3ajCyCPzKPzt64QVyJUExpNDFEoSd96yRhkcF6ln23pFPYTSP3v15n23f092lrVAmg=="; // 실제 서비스 키를 입력하세요
        int contentTypeId = 12; // 콘텐츠 타입 ID
        String urlStr = "https://apis.data.go.kr/B551011/KorService1/areaBasedSyncList1" +
                "?serviceKey=" + serviceKey + // 서비스 키
                "&MobileOS=ETC" + // 모바일 OS
                "&MobileApp=1" + // 모바일 앱 이름
                "&_type=json" + // 응답 형식
                "&contentTypeId=" + contentTypeId+ // 콘텐츠 타입 ID
        		"&numOfRows=" + 500; // 한 페이지당 가져올 항목 수
        
        try {
            URL url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            int responseCode = urlConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8))) {
                    String returnLine;
                    while ((returnLine = br.readLine()) != null) {
                        result.append(returnLine).append("\n");
                    }
                }
            } else {
                throw new EDException("API 요청 실패: 응답 코드 " + responseCode);
            }

            urlConnection.disconnect();

            // 콘솔에 JSON 응답 출력
            System.out.println("API 응답: " + result.toString());

            // JSON 응답을 파싱하여 TourListResponse 객체로 변환
            ObjectMapper mapper = new ObjectMapper();
            TourListResponse tourListResponse = mapper.readValue(result.toString(), TourListResponse.class);

            // 모델에 TourList 리스트 추가
            List<TourList> tourList = tourListResponse.getResponse().getBody().getItems().getTourList();
            model.addAttribute("tourList", tourList);
        } catch (Exception e) {
            throw new EDException("Error occurred while calling the API", e);
        }
        
		model.addAttribute("title", "관광지 조회");
		
		return "user/destination/tourCheck";
	}
	
	/**
	 * 관광지 세부정보
	 * @param contentId
	 * @param model
	 * @return
	 * @throws EDException
	 */
	@GetMapping("/tourCheckDetails")
	public String tourCheckDetails(@RequestParam(name = "contentId", required = false) String contentId, Model model) throws EDException {

		StringBuilder result = new StringBuilder();
        
        String serviceKey = "HmjxL3ZwIR9BRISocvJb3ajCyCPzKPzt64QVyJUExpNDFEoSd96yRhkcF6ln23pFPYTSP3v15n23f092lrVAmg=="; // 실제 서비스 키를 입력하세요
        int numOfRows = 100; // 한 페이지당 가져올 항목 수
        int startPage = 1; // 시작 페이지 번호
        String urlStr = "https://apis.data.go.kr/B551011/KorService1/detailCommon1" +
                "?serviceKey=" + serviceKey +
                "&MobileOS=ETC" +
                "&MobileApp=test" +
                "&_type=json" +
                "&contentId=" + contentId + // 콘텐츠 ID
                "&defaultYN=Y" + // 기본정보 조회 여부
                "&firstImageYN=Y" + // 대표이미지 조회 여부
                "&areacodeYN=Y" + // 지역코드 조회 여부
                "&catcodeYN=Y" + // 서비스분류코드 조회 여부
                "&addrinfoYN=Y" + // 주소 조회 여부
                "&mapinfoYN=Y" + // 좌표 조회 여부
                "&overviewYN=Y"; // 개요 조회 여부
        
        try {
            URL url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            System.out.println("Requesting URL: " + urlStr); // 요청 URL 로그

            int responseCode = urlConnection.getResponseCode();
            System.out.println("Response Code: " + responseCode); // 응답 코드 로그

            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8))) {
                    String returnLine;
                    while ((returnLine = br.readLine()) != null) {
                        result.append(returnLine).append("\n");
                    }
                }
                System.out.println("API Response: " + result.toString()); // API 응답 로그
            } else {
                throw new EDException("API 요청 실패: 응답 코드 " + responseCode);
            }

            urlConnection.disconnect();

            // 빈 문자열을 null로 대체
            String jsonResponse = result.toString();
            jsonResponse = jsonResponse.replace("\"items\": \"\"", "\"items\": null");

            // JSON 응답을 파싱하여 TourItemResponse 객체로 변환
            ObjectMapper mapper = new ObjectMapper();
            TourInformationResponse tourInformationResponse = mapper.readValue(jsonResponse, TourInformationResponse.class); // 수정
            System.out.println("Parsed Response: " + tourInformationResponse); // 파싱된 응답 로그

            // 모델에 TourItem 리스트 추가
            // 빈 문자열인 경우 빈 리스트 반환
            List<TourInformation> tourInformation;
            if (tourInformationResponse.getResponse().getBody().getItems() == null || tourInformationResponse.getResponse().getBody().getItems().getInformationList() == null) {
                tourInformation = Collections.emptyList();
            } else {
                tourInformation = tourInformationResponse.getResponse().getBody().getItems().getInformationList();
            }
            System.out.println("Tour Information List: " + tourInformation); // TourInformation 리스트 로그
            model.addAttribute("TourInformation", tourInformation);
        } catch (Exception e) {
            e.printStackTrace(); // 예외 스택 트레이스 출력
            throw new EDException("Error occurred while calling the API", e);
        }
        
        List<UReivewReportCategory> uReviewReportList = uReviewService.getReviewReportCategory();
        model.addAttribute("uReviewReportList", uReviewReportList);
        
        // model에 contentId 추가
        model.addAttribute("contentId", contentId);
       
        model.addAttribute("title", "관광지 세부사항");
		
		return "user/destination/tourCheckDetails";
	}
	
	/*
	 * @GetMapping("/destination/regionalCheck") public String regionalCheck(Model
	 * model) {
	 * 
	 * model.addAttribute("title", "지역 조회");
	 * 
	 * return "/user/destination/regionalCheck"; }
	 * 
	 * @GetMapping("/destination/regionalCheckDetails") public String
	 * regionalCheckDetails(Model model) {
	 * 
	 * model.addAttribute("title", "지역 세부 조회");
	 * 
	 * return "/user/destination/regionalCheckDetails"; }
	 */

}
