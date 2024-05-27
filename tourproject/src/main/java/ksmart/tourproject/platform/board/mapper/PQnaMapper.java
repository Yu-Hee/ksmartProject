package ksmart.tourproject.platform.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.tourproject.platform.board.dto.PQna;

@Mapper
public interface PQnaMapper {
	
	// 1:1문의 조회
	List<PQna> getQnaList();
}
