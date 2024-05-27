package ksmart.tourproject.platform.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.tourproject.platform.board.dto.PReport;

@Mapper
public interface PReportMapper {
	
	// 신고내역 조회
	List<PReport> getReportList();

}
