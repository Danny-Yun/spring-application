package org.ict.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.ict.domain.BoardVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

// Service테스트는 BoardServiceImpl 내부 기능을 서버 가동 없이 테스트하기 위해 작성한다.
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTest {

	// 다형성 원리에 의해서 BoardService로 만들어도 BoardServiceImpl이 주입됨
	@Autowired
	private BoardService boardService;
	
	// 먼저 서비스가 제대로 주입되었는지 여부만 확인
	@Test
	public void testExist() {
		log.info(boardService);
		// assertNotNull은 해당 객체가 주입이 되지 않아 null인 경우 에러를 발생시킨다.
		assertNotNull(boardService);
	}
	
	@Test
	public void testRegister() {
		// register 로직이 BoardVO를 필요로 하므로,
		// 먼저 vo부터 생성해서 자료 입력 후 전달함
		BoardVO vo = new BoardVO();
		
		vo.setB_title("서비스 작성글");
		vo.setB_content("서비스 본문글");
		vo.setB_writer("서비스 글쓴이");
		
		boardService.register(vo);
	}
	
	@Test
	public void testGetList() {
		String keyword = "";
		boardService.getList(keyword);
	}
	
	//@Test 
	public void testGet() {
		long b_no = 3;
		boardService.get(b_no);
	}
	
	//@Test
	public void testModify() {
		BoardVO vo = new BoardVO();
		
		vo.setB_title("서비스 수정글");
		vo.setB_content("서비스 본문수정");
		vo.setB_writer("서비스 글쓴이 수정");
		vo.setB_no(2L);
		
		boardService.modify(vo);
	}
	
	//@Test
	public void testRemove() {
		long b_no = 11;
		boardService.remove(b_no);
	}
}