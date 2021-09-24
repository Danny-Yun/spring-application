package org.ict.controller;

import java.util.List;

import org.ict.domain.BoardVO;
import org.ict.domain.Criteria;
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
	
	/*
	@GetMapping("/list")  //Get방식으로 주소연결
	public void list(String keyword, Model model) {
		
		log.info("list로직 접속");
		if(keyword == null) {
			keyword = "";
		}
		// 전체 글 정보 얻어온 다음
		List<BoardVO> boardList = boardService.getList(keyword);
		// view 파일에 list라는 이름으로 넘겨주기
		model.addAttribute("list", boardList);
		model.addAttribute("keyword", keyword);
	}
	*/
	
	// 페이징 처리가 되는 리스트 메서드
	@GetMapping("/list")
	// Criteria를 파라미터에 선언해 pageNum, amount처리
	public void List(Criteria cri, Model model) {
		log.info("list로직 접속");
		// pagenUM, amount 로 전달된 자료를 활용해 게시글 목록 가져오기
		List<BoardVO> PagingList = boardService.getListPaging(cri);
		model.addAttribute("list", PagingList);
	}
	
	
	@PostMapping("/register")  // Post방식으로만 접속 허용
	public String register(BoardVO vo, RedirectAttributes rttr) {
		// 글을 썼으면 상세페이지나 글 목록으로 이동시켜야 한다. 
		// 1. 글 쓰는 로직 실행
		boardService.register(vo);
		log.info("insertSelectKey 확인 : " + vo);
		// 2. list 주소로 강제로 이동을 시킨다. 
		rttr.addFlashAttribute("b_no", vo.getB_no());
		rttr.addFlashAttribute("success", "registerOK");
		
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
	
	// get방식으로 삭제를 허용하면 매크로 등을 이용해서 마음대로 글 삭제를 하는 경우가 생길 수 있다. 
	// 무조건 삭제 버튼을 클릭해서 삭제할 수 있도록 post방식의 접근만 허용한다. 
	@PostMapping("/remove")
	public String remove(Long b_no, RedirectAttributes rttr) {
		
		log.info("remove 로직 접속");
		boardService.remove(b_no);
		rttr.addFlashAttribute("success", "removeOK");
		rttr.addFlashAttribute("b_no", b_no);		
		return "redirect:/board/list";
	}
	
	// 수정로직도 post방식으로 진행해야 한다.
	@PostMapping("/modify") 
	public String modify(BoardVO vo, RedirectAttributes rttr) {
		
		log.info("modify 로직 접속");
		boardService.modify(vo);
		rttr.addFlashAttribute("success", "success");
		return "redirect:/board/get/" + vo.getB_no();
	}
	
	@PostMapping("modify/{b_no}")
	public String modifyForm(@PathVariable Long b_no, Model model) {
		
		BoardVO vo = boardService.get(b_no);
		model.addAttribute("board", vo);
		return "/board/modify";
	}
	
	
}
