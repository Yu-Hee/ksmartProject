package ksmart.tourproject.user.destination.dto;

import lombok.Data;

@Data
public class TourInformation {
    private String contentid; // 콘텐츠 ID
    private String contenttypeid; // 콘텐츠 타입 ID
    private String title; // 제목
    private String createdtime; // 생성 시간
    private String modifiedtime; // 수정 시간
    private String tel; // 전화번호
    private String telname; // 전화번호 이름
    private String homepage; // 홈페이지
    private String booktour; // 예약 정보
    private String firstimage; // 대표 이미지(원본)
    private String firstimage2; // 대표 이미지(썸네일)
    private String cpyrhtDivCd; // 저작권 구분 코드
    private String areacode; // 지역 코드
    private String sigungucode; // 시군구 코드
    private String cat1; // 대분류 코드
    private String cat2; // 중분류 코드
    private String cat3; // 소분류 코드
    private String addr1; // 주소
    private String addr2; // 상세 주소
    private String zipcode; // 우편번호
    private String mapx; // 지도 X 좌표
    private String mapy; // 지도 Y 좌표
    private String mlevel; // 맵 레벨
}
