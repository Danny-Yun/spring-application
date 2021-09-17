package org.ict.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.ict.domain.BoardVO;

public interface BoardMapper {

	// board_tbl에서 글번호 3번 이하만 조회하는 쿼리문을 어노테이션을 이용해 작성
	//@Select("SELECT * FROM board_tbl WHERE b_no < 4")
	public List<BoardVO> getList(String keyword);
	
	// insert구문 실행용으로 메서드를 선언
	// VO내부에 적혀있는 정보를 이용해 insert 한다.
	// BoardVO를 매개로 insert 정보를 전달받음
	public void insert(BoardVO vo);
	
	public void insertSelectKey(BoardVO vo);
	
	// 글 번호(Long b_no)를 파라미터로 받아, 해당 글 번호에 해당하는 글을 리턴해 보여주는 메서드 작성
	public BoardVO select(Long b_no);
	
	// 글 번호(Long b_no)를 파라미터로 받고, 해당 글 번호에 해당하는 글을 삭제하는 메서드 작성
	public void delete(Long b_no);
	
	// 글 수정 로직, BoardVO를 받아서 수정하고, 바꿀 내역은 title, content, writer를 vo에 받음
	// updatedate는 sysdate로, where 구문은 b_no로 구분해서 처리한다. 
	public void update(BoardVO vo);
	
}
