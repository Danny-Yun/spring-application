package org.ict.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

// 빈 컨테이너 넣어주자(등록된 컨트톨러만 실제로 작동됨)
@Controller
public class MvcController {
	
	// 기본주소(localhost:8181)뒤에 /goA를 붙이면 goA()메서드를 실행
	@RequestMapping(value="/goA")
	public String goA() {
		System.out.println("goA 주소 접속 감지");
		// 결과 페이지는 views 폴더 아래의 A.jsp
		return "A";
	}
	
	@RequestMapping(value="/goB")
	public String goB() {
		System.out.println("goB 주소 접속 감지");
		return "B";
	}
	
	// goC는 파라미터를 입력받도록 해보자
	@RequestMapping(value="/goC")
	// 주소 뒤 ?cNum=값 형태로 들어오는 값을 로직 내 cNum으로 처리한다
	// 들어온 파라미터를 .jsp파일로 전달하기 위해서는 Model model을 파라미터 추가로 선언해준다.
	public String goC(int cNum, Model model) {
		System.out.println("주소로 전달받은 값 : " + cNum);
		
		// 전달받은 cNum을 보내줌
		model.addAttribute("cNum", cNum);
		
		return "C";
	}
	
	// goD는 requestParam을 이용해 변수명과 받는 이름이 일치하지 않게 해보자
	@RequestMapping(value="/goD")
	// @RequestParam("대체이름")은 변수 왼쪽에 선언한다. 
	// 이렇게 되면 적힌 변수명 대신 대체이름으로 치환해 받아온다.
	public String goD(@RequestParam("d") int dNum, Model model) {
		System.out.println("d 변수명으로 받은게 dNum에 저장 : " + dNum);
		
		// 받아온 변수를 D.jsp로
		model.addAttribute("dNum", dNum);
		
		return "D";
	}
	
	
	// cToF 메서드 생성
	// 섭씨 온도를 입력받아 화씨 온도로 바꿔서 출력해주는 로직 
	// 폼에서 post방식으로 제출했을때에만 결과페이지로 넘어오도록 설계
	@RequestMapping(value="/ctof", method=RequestMethod.POST)
	public String cToF(@RequestParam("c")int celsius, Model model) {
		System.out.println("섭씨 확인 : " + celsius);
		double fahrenheit = celsius * 1.8 + 32;
		System.out.println("화씨 확인 : " + fahrenheit);
		
		model.addAttribute("fh", fahrenheit);
		model.addAttribute("c", celsius);
		
		return "ctof";
	}
	
	// 폼으로 연결하는 메서드
	// 폼과 결과페이지가 같은 주소를 공유하게 하기 위해서 폼쪽을 겟방식 접근 허용
	@RequestMapping(value="/ctof", method=RequestMethod.GET)
	public String cToFForm() {
		
		return "ctofform";
	}
	
	
	// 위와 같은 방식으로 이번엔 bmi측정페이지 생성
	@RequestMapping(value="/bmi", method=RequestMethod.POST)
	public String bmi(int weight, int height, Model model) {
		System.out.println("몸무게 확인 : " + weight);
		System.out.println("신장 확인 : " + height);
		double bmi = weight / ((height / 100) ^ 2);
		System.out.println("bmi 확인 : " + bmi);
		
		model.addAttribute("bmi", bmi);
		return "bmi";

	}
	
	@RequestMapping(value="/bmi", method=RequestMethod.GET)
	public String bmiForm() {
		return "bmi_form";
	}
	
	
	// PathVariable을 이용하면 url패턴만으로도 특정 파라미터를 받아올 수 있다. 
	// REST방식으로 url을 처리할 때 주로 사용하는 방식으로, /pathtest/숫자  중 숫자 위치에 온 것은 page라는 변수값으로 간주함
	@RequestMapping(value="/pathtest/{page}")
	// int page 왼쪽에 @PathVariable을 붙여서 처리해야 연동된다
	public String pathTest(@PathVariable int page, Model model) {
		
		// 받아온 page 변수를 path.jsp로
		model.addAttribute("page", page);
		return "path";
	}
	
	// ctof 로직을 PathVariable을 적용해서 만들어보자
	@RequestMapping(value="/ctof/{celsius}")
	public String cToFPV(@PathVariable int celsius, Model model) {
		System.out.println("섭씨 확인 : " + celsius);
		double fahrenheit = celsius * 1.8 + 32;
		System.out.println("화씨 확인 : " + fahrenheit);
		
		model.addAttribute("fh", fahrenheit);
		model.addAttribute("c", celsius);
		
		return "ctof";
	}
	
	
	// void 타입 컨트롤러의 특징
	// void 타입은 return값이 없는만큼 view파일의 이름을 그냥 url패턴.jsp로 자동 지정 해버린다.
	@RequestMapping(value="/voidreturn")
	public void voidTest(int a, int b, Model model) {
		System.out.println("void 컨트롤러는 리턴구문이 필요 없음.");
		System.out.println("a 확인 : " + a);
		System.out.println("b 확인 : " + b);
		
		int sum = a + b;
		model.addAttribute("sum", sum);
	}
}
