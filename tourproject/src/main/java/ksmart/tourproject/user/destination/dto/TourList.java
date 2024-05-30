package ksmart.tourproject.user.destination.dto;

import lombok.Data;

@Data
public class TourList {
    
   
    private String addr1;  // 주소1
    private String addr2; // 주소2
    
    // 지역 코드
    private int areacode;
    
    // 예약 정보
    private String booktour;
    
    // 대분류 코드
    private String cat1;
    
    // 중분류 코드
    private String cat2;
    
    // 소분류 코드
    private String cat3;
    
    // 콘텐츠 ID
    private long contentid;
    
    // 콘텐츠 타입 ID
    private int contenttypeid;
    
    // 생성 시간
    private String createdtime;
    
    // 대표 이미지 (원본)
    private String firstimage;
    
    // 대표 이미지 (썸네일)
    private String firstimage2;
    
    // 저작권 구분 코드
    private String cpyrhtDivCd;
    
    // 지도 x 좌표
    private double mapx;
    
    // 지도 y 좌표
    private double mapy;
    
    // 지도 레벨
    private int mlevel;
    
    // 수정 시간
    private String modifiedtime;
    
    // 시군구 코드
    private int sigungucode;
    
    // 전화번호
    private String tel;
    
    // 제목
    private String title;
    
    // 우편번호
    private String zipcode;
    
    // 표시 여부
    private int showflag;
}
