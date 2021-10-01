package org.ict.controller;

import java.util.List;

import org.ict.domain.ReplyVO;
import org.ict.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/replies")
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	
	// consumes 이 메서드의 파라미터를 넘겨줄 때 어떤 형식으로 넘겨줄지를 설정하는데 
	// 기본적으로 rest방식에서는 json을 사용한다. produces는 입력받은 데이터를 토대로
	// 로직을 실행한 다음 사용자에게 결과를 보여줄 데이터의 형식을 나타냅니다. 
	@PostMapping(value="", consumes="application/json", 
					produces={MediaType.TEXT_PLAIN_VALUE})
	// produces에 TEXT_PLAIN_VALUES를 줬으므로 결과코드와 문자열을 넘김
	public ResponseEntity<String> register(
			// rest컨트롤러에서 받는 파라미터 앞에 @RequestBody 어노테이션이 붙어야 consumes와 연결됨	
			@RequestBody ReplyVO vo) {
		// 깡통 entity를 먼저 생성
		ResponseEntity<String> entity = null;
		
		try {
			replyService.addReply(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			
		} catch(Exception e) {
			// 글쓰기 로직에 문제가 생길 경우
			e.printStackTrace();
			entity = new ResponseEntity<String>(
					e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		// 위의 try 혹은 catch블럭에서 얻어온 entity를 리턴
		return entity;
	}
	
	@GetMapping(value="/all/{b_no}", 
			// 단일 숫자데이터 b_no만 넣기도 하고, PathVariable어노테이션으로 이미 입력데이터가
			// 명시되었으므로 consumes는 따로 주지 않아도 된다. produces는 댓글 목록이 XML로도,
			// JSON으로도 표현될 수 있도록 아래와 같이 세팅한다. 
			produces= {MediaType.APPLICATION_XML_VALUE,
						MediaType.APPLICATION_JSON_UTF8_VALUE})
	// produces에 TEXT_PLAIN_VALUES를 줬으므로 결과코드와 문자열을 넘김
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("b_no") Long b_no) {
		
		ResponseEntity<List<ReplyVO>> entity = null;
		
		try {
			entity = new ResponseEntity<>(
					replyService.replyList(b_no), HttpStatus.OK);
			
		} catch(Exception e) {
			// 글쓰기 로직에 문제가 생길 경우
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@DeleteMapping(value="/{r_no}", produces={MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("r_no") Long r_no) {
		ResponseEntity<String> entity = null;
		try {
			replyService.removeReply(r_no);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}	
	
	@RequestMapping(method= {RequestMethod.PUT, RequestMethod.PATCH},
			value="/{r_no}", produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify
					// vo는 우선 payload에 적힌 데이터로 받아온다. 
					// @RequestBody가 붙은 vo는 payload에 적힌 데이터를 VO로 환산해서 가져온다.
					// 단, 댓글번호는 주소에 기입된 숫자를 자원으로 받아온다.
					(@RequestBody ReplyVO vo, @PathVariable("r_no") Long r_no) {
		
		ResponseEntity<String> entity = null;
		try {
			// payload에는 reply만 넣어줘도 되는데 그 이유는 r_no는 요청주소로 받아오기 때문이다.
			// 단, r_no를 주소로 받아오는 경우는 아직 ReplyVO에 r_no가 세팅이 되지 않은 상태이므로
			// setter로 r_no까지 지정해준다. 
			vo.setR_no(r_no);
			replyService.modifyReply(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
