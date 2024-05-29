package ksmart.tourproject.user.destination.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import ksmart.tourproject.user.destination.dto.TourItem;

@Controller
public class TourController {

    @GetMapping("/tour")
    public String getTour(Model model) {

        TourItem tourItem = new TourItem();
        // TourItem에 필요한 데이터를 설정하거나 가져와야 합니다.
        model.addAttribute("tourItem", tourItem);
        return "user/destination/tour"; // tour.html 템플릿을 사용하여 데이터를 출력
    }

}
