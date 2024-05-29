package ksmart.tourproject.user.destination.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import ksmart.tourproject.user.destination.dto.TourItem;

public class TourItemParser {

    public static TourItem parseJson(String jsonData) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        TourItem tourItem = objectMapper.readValue(jsonData, TourItem.class);
        return tourItem;
    }
}
