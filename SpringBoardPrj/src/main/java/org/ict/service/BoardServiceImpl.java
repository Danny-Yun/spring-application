package org.ict.service;

import java.util.ArrayList;
import java.util.List;

import org.ict.domain.BoardVO;
import org.ict.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

// BoardServiceImpl은 BoardService 인터페이스를 구현한다. 
@Log4j  // 로깅을 위한 어노테이션, X가 뜨면 pom.xml에서 추가 수정
@Service  // 의존성 등록을 위한 어노테이션
@AllArgsConstructor  // 서비스 생성자 자동 생성
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	// 등록 작업시 BoardVO를 매개로 실행하기 때문에 아래와 같이 BoardVO를 파라미터에 설정해둠
	// BoardServiceTest에 VO를 생성해 테스트 실행
	@Override
	public void register(BoardVO vo) {
		log.info("글 등록 작업 실행");
		boardMapper.insert(vo);
	}

	// 전체 글이 아닌 특정 글 하나만 가져오는 로직
	@Override
	public BoardVO get(Long b_no) {
		log.info(b_no + "글 상세 조회 실행");
		BoardVO board = boardMapper.select(b_no);
		return board;
	}

	@Override
	public void modify(BoardVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Long b_no) {
		// TODO Auto-generated method stub
		
	}
	
	// 글 전체 목록을 가져오는 로직을 작성
	@Override
	public List<BoardVO> getList() {
		log.info("글 전체 목록 조회 실행");
		List<BoardVO> boardList = boardMapper.getList();
		return boardList;
	}

}
