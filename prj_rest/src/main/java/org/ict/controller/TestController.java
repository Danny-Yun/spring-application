package org.ict.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ict.domain.TestVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
	
	// 문자열 리턴
	@RequestMapping("/hello")
	public String sayHello() {
		return "Hello Hello";
	}
	
	// json 리턴
	@RequestMapping("/sendVO")
	public TestVO sendTestVO() {
		TestVO testVO = new TestVO();
		
		testVO.setName("윤지우");
		testVO.setAge(21);
		testVO.setM_no(1);
		return testVO;
	}
	
	@RequestMapping("/sendVOList")
	public List<TestVO> sendVoList() {
		List<TestVO> list = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			TestVO vo = new TestVO();
			vo.setM_no(i);
			vo.setName("지우" + i);
			vo.setAge(20 + i);
			list.add(vo);
		}
		return list;
	}
	@RequestMapping("/sendMap")
	public Map<Integer, TestVO> sendMap() {
		Map<Integer, TestVO> map = new HashMap<>();
		for(int i = 0; i < 10; i++) {
			TestVO vo = new TestVO();
			vo.setM_no(i);
			vo.setName("윤지우");
			vo.setAge(50 + i);
			map.put(i, vo);
		}
		return map;
	}
	
	// 정상적인 로직도 강제로 400에러를 발생시키는 ResponseEntity<>, 주소가 일치하지만 의도적으로 400에러를 발생시킨다. 
	@RequestMapping("/sendErrorAuth")
	public ResponseEntity<Void> sendListAuth() {
		// ResponseEntity는 생성자에 HttpStatus.코드번호를 적어서 해당 주소 접속시
		// 어떤 접속코드를 사용자에게 넘겨줄지 결정할 수 있다.
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST); 
	}
	
	@RequestMapping("/sendErrorNot")
	public ResponseEntity<List<TestVO>> sendListNot() {
		List<TestVO> list = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			TestVO vo = new TestVO();
			vo.setM_no(i);
			vo.setName("라끄베르" + i);
			vo.setAge(30 + i);
			list.add(vo);
		}
		// ResponseEntity의 생성자에, 파라미터 2개를 넘기면 전송할 데이터와, 전송시 결과로 나올 코드를 함께 넘길 수 있다.
		// 404를 띄우는 와중에 데이터를 띄워야 하는 경우도 있다. 
		return new ResponseEntity<List<TestVO>>(list, HttpStatus.NOT_FOUND);
	}
	
}
