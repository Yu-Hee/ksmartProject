package ksmart.tourproject.user.destination.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TourInformationResponse {
    private Response response;

    @Data
    public static class Response {
        private Header header;
        private Body body;

        @Data
        public static class Header {
            private String resultCode;
            private String resultMsg;
        }

        @Data
        public static class Body {
            private Items items;
            private int numOfRows;
            private int pageNo;
            private int totalCount;

            @Data
            public static class Items {
                @JsonProperty("item") // API 응답에서 제공하는 리스트 이름으로 수정
                private List<TourInformation> informationList;
            }
        }
    }
}