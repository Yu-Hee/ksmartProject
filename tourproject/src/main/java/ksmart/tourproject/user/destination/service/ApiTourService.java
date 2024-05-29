package ksmart.tourproject.user.destination.service;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiTourService {

	   private final String BASE_URL = "http://apis.data.go.kr/B551011/KorService1";
	    private final String apiUri = "/areaBasedList";
	    private final String serviceKey = "?ServiceKey=HmjxL3ZwIR9BRISocvJb3ajCyCPzKPzt64QVyJUExpNDFEoSd96yRhkcF6ln23pFPYTSP3v15n23f092lrVAmg==";
	    private final String defaultQueryParam = "&MobileOS=ETC&MobileApp=AppTest&_type=json";
	    private final String numOfRows = "&numOfRows=100";
	    private final String areaCode = "&areaCode=1";
	    private final String contentTypeId = "&contentTypeId=12";


	    private String makeUrl() throws UnsupportedEncodingException {
	        return BASE_URL +
	                apiUri +
	                serviceKey +
	                defaultQueryParam +
	                numOfRows +
	                areaCode +
	                contentTypeId;
	    }

	    public ResponseEntity<?> fetch() throws UnsupportedEncodingException {
	        System.out.println(makeUrl());
	        RestTemplate restTemplate = new RestTemplate();
	        HttpEntity<?> entity = new HttpEntity<>(new HttpHeaders());
	        ResponseEntity<Map> resultMap = restTemplate.exchange(makeUrl(), HttpMethod.GET, entity, Map.class);
	        System.out.println(resultMap.getBody());
	        return resultMap;

	    }
}
