package ksmart.tourproject.user.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.tourproject.user.board.dto.UFaq;
import ksmart.tourproject.user.board.mapper.UFaqMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UFaqService {
	
	private final UFaqMapper uFaqMapper;

	
	/**
	 * 자주찾는 질문 조회
	 * @return List<UFaq>
	 */
	public List<UFaq> getFaqList() {
		return uFaqMapper.getFaqList();
	}

}
