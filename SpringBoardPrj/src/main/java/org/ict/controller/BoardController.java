package org.ict.controller;

import java.util.List;

import org.ict.domain.BoardVO;
import org.ict.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@GetMapping("/list")  //Get방식으로 주소연결
	public void list(Model model) {
		
		log.info("list로직 접속");
		// 전체 글 정보 얻어온 다음
		List<BoardVO> boardList = boardService.getList();
		// view 파일에 list라는 이름으로 넘겨주기
		model.addAttribute("list", boardList);
	}
	
	@PostMapping("/register")  // Post방식으로만 접속 허용
	public String register(BoardVO vo, RedirectAttributes rttr) {
		// 글을 썼으면 상세페이지나 글 목록으로 이동시켜야 한다. 
		// 1. 글 쓰는 로직 실행
		boardService.register(vo);
		// 2. list 주소로 강제로 이동을 시킨다. 
		rttr.addFlashAttribute("result", vo.getB_no());
		
		// redirect로 이동시킬 때는 "redirect:파일명" 
		return "redirect:/board/list";
	}
	
	@GetMapping("/register")
	public String registerForm() {
		return "/board/register";
	}
	
	// 상세페이지 조회는 Long bno에 적힌 글번호를 이용한다. 
	// /get을 주소로 getMapping을 사용하는 메서드 get을 활용
	@GetMapping("/get/{b_no}")
	public String get(@PathVariable Long b_no, Model model) {
		log.info("get 로직 접속");
		BoardVO vo = boardService.get(b_no);
		model.addAttribute("board", vo);
		return "/board/detail";
	}
}
