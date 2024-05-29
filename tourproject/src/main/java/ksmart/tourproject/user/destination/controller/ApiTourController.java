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

import com.fasterxml.jackson.databind.ObjectMapper;

import ksmart.tourproject.exception.EDException;
import ksmart.tourproject.user.destination.dto.TourInformation;
import ksmart.tourproject.user.destination.dto.TourInformationResponse;
import ksmart.tourproject.user.destination.dto.TourItem;
import ksmart.tourproject.user.destination.dto.TourItemResponse;

/*@RestController*/
@Controller
public class ApiTourController {
	
	/**
	 * 숙소정보
	 * @param model
	 * @return
	 * @throws EDException
	 */
    @GetMapping("/jsonapi")
    public String callApi(Model model) throws EDException {
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
	                    "&arrange=O";
        
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
        
        return "user/destination/tour"; // Thymeleaf 템플릿 이름
    }
    
    @GetMapping("/jsonapiinfomation")
    public String callApi2(Model model) throws EDException {
        StringBuilder result = new StringBuilder();
        
        String serviceKey = "HmjxL3ZwIR9BRISocvJb3ajCyCPzKPzt64QVyJUExpNDFEoSd96yRhkcF6ln23pFPYTSP3v15n23f092lrVAmg=="; // 실제 서비스 키를 입력하세요
        int numOfRows = 100; // 한 페이지당 가져올 항목 수
        int startPage = 1; // 시작 페이지 번호
        String urlStr = "http://apis.data.go.kr/B551011/KorService1/detailCommon1" +
                        "?serviceKey=" + serviceKey +
		                "&MobileOS=ETC" +
		                "&MobileApp=AppTest" +
		                "&_type=json" +
		                "&listYN=Y" +
		                "&arrange=O" +
		                "&contentId=2465071" + // 콘텐츠 ID 조회 여부
		                "&contentTypeId=Y" + // 관광타입 ID 조회 여부
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
            TourInformationResponse tourInformationResponse = mapper.readValue(result.toString(), TourInformationResponse.class);
            
            // 모델에 TourItem 리스트 추가
            List<TourInformation> tourItems = tourInformationResponse.getResponse().getBody().getItems().getInformationList();
            model.addAttribute("TourInformation", tourItems);
        } catch (Exception e) {
            throw new EDException("Error occurred while calling the API", e);
        }
        
        return "user/destination/tour"; // Thymeleaf 템플릿 이름
    }
    
    

	/*
    @GetMapping("/jsonapi")
    public String callApi() throws EDException {
        StringBuilder result = new StringBuilder();
        
        String serviceKey = "YOUR_SERVICE_KEY"; // 실제 서비스 키를 입력하세요
        String urlStr = "http://apis.data.go.kr/B551011/KorService1/searchStay1" +
                        "?serviceKey=" + "HmjxL3ZwIR9BRISocvJb3ajCyCPzKPzt64QVyJUExpNDFEoSd96yRhkcF6ln23pFPYTSP3v15n23f092lrVAmg==" +
                        "&pageNo=1" +
                        "&numOfRows=10" +
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
        } catch (Exception e) {
            throw new EDException("Error occurred while calling the API", e);
        }
        
        return result.toString();
    }
    */
}
