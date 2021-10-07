package org.ict.service;

import java.util.List;

import org.ict.domain.BoardVO;
import org.ict.domain.SearchCriteria;

// 서비스 계층은 하나의 동작을 담당한다. 
// mapper 계층에서 하나의 메서드가 하나의 쿼리문만 담당했는데, 
// service 계층은 하나의 메서드가 2개 이상의 쿼리문을 담당할 수도 있으며
// 메서드 하나가 사용자의 하나의 동작단위를 담당한다. 
public interface BoardService {
	
	// 사용자의 동작단위를 기술해보자
	// 글 등록
	public void register(BoardVO vo);
	
	// 글 조회
	public BoardVO get(Long b_no);
	
	// 글 수정
	public void modify(BoardVO vo);
	
	// 글 삭제
	public void remove(Long b_no);
	
	// 전체 글 목록
	public List<BoardVO> getList(String keyword);
	
	// 페이징 글 목록 
	public List<BoardVO> getListPaging(SearchCriteria cri);
	
	// 전체 글 개수
	public int getBoardTotal(SearchCriteria cri);
	
}
