package org.ict.mapper;

import org.ict.domain.BoardVO;
import org.ict.mapper.BoardMapperTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

// 테스트코드 기본세팅 (Runwith, ContextConfiguration, Log4j)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTest {
	// 이 테스트코드 내에서는 Mapper테스트를 담당한다. 따라서 BoardMapper내부의 메서드를 실행할 예정이다. 
	// BoardMapper타입의 변수가 필요하니 선언 후 자동 주입으로 넣어보자
	@Autowired
	private BoardMapper boardMapper;
	
	// 테스트용 메서드의 이름은 testGetList
	// 테스트 코드가 실행될 수 있도록 만들어보자
	@Test
	public void testGetList() {
		String keyword = "";
		log.info(boardMapper.getList(keyword));
	}
	
	// insert를 실행할 테스트 코드
	@Test
	public void testInsertSelectKey() {
		// 글 입력을 위해서 BoardVO 타입을 매개로 사용한다. 
		BoardVO vo = new BoardVO();
		
		// 입력할 글에 대한 제목, 글쓴이, 본문을 vo에 삽입
		vo.setB_title("새로 넣는 글");
		vo.setB_content("새로 넣는 본문");
		vo.setB_writer("새로 넣는 글쓴이");
		
		// log.info(vo);
		boardMapper.insertSelectKey(vo);
	}
	
	//@Test
	public void testSelect() {
		long b_no = 1;
		boardMapper.select(b_no);
	}
	
	//@Test
	public void testDelete() {
		long b_no = 3;
		boardMapper.delete(b_no);
	}
	
	//@Test
	public void testUpdate() {
		BoardVO vo = new BoardVO();
		
		vo.setB_title("수정된 제목");
		vo.setB_content("본문 수정됨");
		vo.setB_no(2L);
		
		boardMapper.update(vo);
	}
	
	
}
