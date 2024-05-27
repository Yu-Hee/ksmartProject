package ksmart.tourproject.platform.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.tourproject.platform.board.dto.PNotice;

@Mapper
public interface PNoticeMapper {
	
	// 공지사항 수정
	String noticeModify(PNotice pNotice);
	
	// 공지사항 작성
	String noticeWrite(PNotice pNotice);
	
	// 공지사항 상세목록 조회
	List<PNotice> getNoticeDetailList();

	// 공지사항 목록조회
	List<PNotice> getNoticeList();

	PNotice getNoticeByNum(String noticeNum);

}
