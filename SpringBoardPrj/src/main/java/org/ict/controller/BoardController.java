package org.ict.controller;

import java.util.List;

import org.ict.domain.BoardVO;
import org.ict.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

// 의존성 추가, 로깅 기능 추가
@Controller
@Log4j
@RequestMapping("/board/*")  // 클래스 위에 작성시 이 클래스를 사용하는 모든 메서드의 연결 주소 앞에 /board/ 를 추가하게 된다.
@AllArgsConstructor  //의존성 주입을 설정을 위해 생성자만 생성 
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping(value="/list")  //Get방식으로 주소연결
	public void list(Model model) {
		
		log.info("list로직 접속");
		// 전체 글 정보 얻어온 다음
		List<BoardVO> boardList = boardService.getList();
		// view 파일에 list라는 이름으로 넘겨주기
		model.addAttribute("list", boardList);
	}

}
