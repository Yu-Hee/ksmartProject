package ksmart.tourproject.user.destination.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

import ksmart.tourproject.exception.EDException;
import ksmart.tourproject.user.destination.dto.TourItem;
import ksmart.tourproject.user.destination.dto.TourItemResponse;

@Controller
@RequestMapping(value = "/user/destination")
public class destinationController {
	
	@GetMapping("/lodgingCheck")
	public String lodgingCheck(Model model) throws EDException {
		StringBuilder result = new StringBuilder();
		
        String serviceKey = "HmjxL3ZwIR9BRISocvJb3ajCyCPzKPzt64QVyJUExpNDFEoSd96yRhkcF6ln23pFPYTSP3v15n23f092lrVAmg=="; // 실제 서비스 키를 입력하세요
        int numOfRows = 100; // 한 페이지당 가져올 항목 수
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
		
		return "/user/destination/lodgingCheck";
	}
	
	@GetMapping("/lodgingCheckDetails")
	public String lodgingCheckDetails(Model model) {
		
		model.addAttribute("title", "숙소 세부사항");
		
		return "/user/destination/lodgingCheckDetails";
	}
	
	@GetMapping("/restaurantCheck")
	public String restaurantCheck(Model model) throws EDException {
		
		StringBuilder result = new StringBuilder();
		
        String serviceKey = "HmjxL3ZwIR9BRISocvJb3ajCyCPzKPzt64QVyJUExpNDFEoSd96yRhkcF6ln23pFPYTSP3v15n23f092lrVAmg=="; // 실제 서비스 키를 입력하세요
        int numOfRows = 100; // 한 페이지당 가져올 항목 수
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
		
		model.addAttribute("title", "음식점 조회");
		
		return "/user/destination/restaurantCheck";
	}
	
	@GetMapping("/restaurantCheckDetails")
	public String restaurantCheckDetails(Model model) {
		
		model.addAttribute("title", "음식점 세부사항");
		
		return "/user/destination/restaurantCheckDetails";
	}
	

	
	

	@GetMapping("/tourCheck")
	public String tourCheck(Model model) throws EDException {
		
		StringBuilder result = new StringBuilder();
		
        String serviceKey = "HmjxL3ZwIR9BRISocvJb3ajCyCPzKPzt64QVyJUExpNDFEoSd96yRhkcF6ln23pFPYTSP3v15n23f092lrVAmg=="; // 실제 서비스 키를 입력하세요
        int numOfRows = 100; // 한 페이지당 가져올 항목 수
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
        
		model.addAttribute("title", "관광지 조회");
		
		return "/user/destination/tourCheck";
	}
	
	
	@GetMapping("/tourCheckDetails")
	public String tourCheckDetails(Model model) {
		
		model.addAttribute("title", "관광지 세부사항");
		
		return "/user/destination/tourCheckDetails";
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
