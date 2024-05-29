package ksmart.tourproject.user.destination.service;

import org.springframework.stereotype.Service;

import ksmart.tourproject.user.destination.controller.TourItemParser;
import ksmart.tourproject.user.destination.dto.TourItem;

@Service
public class TourItemService {


    public TourItem parseJson(String jsonData) throws Exception {
        return TourItemParser.parseJson(jsonData);
    }
}
