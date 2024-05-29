package ksmart.tourproject.user.destination.dto;

import lombok.Data;

@Data
public class TourItem {
    private String addr1; //주소
    private String addr2; //상세주소
    private String areacode; //지역코드
    private String benikia; //베니키아여부
    private String cat1; //대분류
    private String cat2; //중분류
    private String cat3; //소분류
    private String contentid; //콘텐츠ID
    private String contenttypeid; //콘텐츠타입ID
    private String createdtime;//등록일
    private String firstimage;//대표이미지(원본)
    private String firstimage2;//대표이미지(썸네일)
    private String cpyrhtDivCd;//저작권유행
    private String goodstay;//굿스테이여부
    private String hanok;//한옥여부
    private String mapx;//x좌표
    private String mapy;//y좌표
    private String mlevel;//map level
    private String modifiedtime; //수정일
    private String tel; //전화번호
    private String title; //제목
    private String booktour;
    private String sigungucode;
}
