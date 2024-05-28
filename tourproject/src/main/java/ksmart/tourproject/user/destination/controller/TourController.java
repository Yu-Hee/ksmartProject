package ksmart.tourproject.user.destination.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TourController {
    private static final String API_URL = "https://apis.data.go.kr/B551011/KorService1/searchStay1?MobileOS=ETC&MobileApp=test&_type=json&serviceKey=HmjxL3ZwIR9BRISocvJb3ajCyCPzKPzt64QVyJUExpNDFEoSd96yRhkcF6ln23pFPYTSP3v15n23f092lrVAmg%3D%3D";

    @GetMapping("/tour")
    public String getTour(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(API_URL, String.class);
        System.out.println("Response from Tour API:");
        System.out.println(responseEntity.getBody());
        
        // 여기서 JSON을 DTO 객체로 변환하는 작업을 수행할 수 있습니다.
        // 예를 들어, ObjectMapper를 사용하여 JSON을 TourItem 객체로 변환하고 모델에 추가하는 코드를 여기에 추가할 수 있습니다.

        return "user/destination/tour"; // tour.html 템플릿을 사용하여 데이터를 출력
    }
}
