package org.ict.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

// �� �����̳� �־�����(��ϵ� ��Ʈ�緯�� ������ �۵���)
@Controller
public class MvcController {
	
	// �⺻�ּ�(localhost:8181)�ڿ� /goA�� ���̸� goA()�޼��带 ����
	@RequestMapping(value="/goA")
	public String goA() {
		System.out.println("goA �ּ� ���� ����");
		// ��� �������� views ���� �Ʒ��� A.jsp
		return "A";
	}
	
	@RequestMapping(value="/goB")
	public String goB() {
		System.out.println("goB �ּ� ���� ����");
		return "B";
	}
	
	// goC�� �Ķ���͸� �Է¹޵��� �غ���
	@RequestMapping(value="/goC")
	// �ּ� �� ?cNum=�� ���·� ������ ���� ���� �� cNum���� ó���Ѵ�
	// ���� �Ķ���͸� .jsp���Ϸ� �����ϱ� ���ؼ��� Model model�� �Ķ���� �߰��� �������ش�.
	public String goC(int cNum, Model model) {
		System.out.println("�ּҷ� ���޹��� �� : " + cNum);
		
		// ���޹��� cNum�� ������
		model.addAttribute("cNum", cNum);
		
		return "C";
	}
	
	// goD�� requestParam�� �̿��� ������� �޴� �̸��� ��ġ���� �ʰ� �غ���
	@RequestMapping(value="/goD")
	// @RequestParam("��ü�̸�")�� ���� ���ʿ� �����Ѵ�. 
	// �̷��� �Ǹ� ���� ������ ��� ��ü�̸����� ġȯ�� �޾ƿ´�.
	public String goD(@RequestParam("d") int dNum, Model model) {
		System.out.println("d ���������� ������ dNum�� ���� : " + dNum);
		
		// �޾ƿ� ������ D.jsp��
		model.addAttribute("dNum", dNum);
		
		return "D";
	}
	
	
	// cToF �޼��� ����
	// ���� �µ��� �Է¹޾� ȭ�� �µ��� �ٲ㼭 ������ִ� ���� 
	// ������ post������� �������������� ����������� �Ѿ������ ����
	@RequestMapping(value="/ctof", method=RequestMethod.POST)
	public String cToF(@RequestParam("c")int celsius, Model model) {
		System.out.println("���� Ȯ�� : " + celsius);
		double fahrenheit = celsius * 1.8 + 32;
		System.out.println("ȭ�� Ȯ�� : " + fahrenheit);
		
		model.addAttribute("fh", fahrenheit);
		model.addAttribute("c", celsius);
		
		return "ctof";
	}
	
	// ������ �����ϴ� �޼���
	// ���� ����������� ���� �ּҸ� �����ϰ� �ϱ� ���ؼ� ������ �ٹ�� ���� ���
	@RequestMapping(value="/ctof", method=RequestMethod.GET)
	public String cToFForm() {
		
		return "ctofform";
	}
	
	
	// ���� ���� ������� �̹��� bmi���������� ����
	@RequestMapping(value="/bmi", method=RequestMethod.POST)
	public String bmi(int weight, int height, Model model) {
		System.out.println("������ Ȯ�� : " + weight);
		System.out.println("���� Ȯ�� : " + height);
		double bmi = weight / ((height / 100) ^ 2);
		System.out.println("bmi Ȯ�� : " + bmi);
		
		model.addAttribute("bmi", bmi);
		return "bmi";

	}
	
	@RequestMapping(value="/bmi", method=RequestMethod.GET)
	public String bmiForm() {
		return "bmi_form";
	}
	
	
	// PathVariable�� �̿��ϸ� url���ϸ����ε� Ư�� �Ķ���͸� �޾ƿ� �� �ִ�. 
	// REST������� url�� ó���� �� �ַ� ����ϴ� �������, /pathtest/����  �� ���� ��ġ�� �� ���� page��� ���������� ������
	@RequestMapping(value="/pathtest/{page}")
	// int page ���ʿ� @PathVariable�� �ٿ��� ó���ؾ� �����ȴ�
	public String pathTest(@PathVariable int page, Model model) {
		
		// �޾ƿ� page ������ path.jsp��
		model.addAttribute("page", page);
		return "path";
	}
	
	// ctof ������ PathVariable�� �����ؼ� ������
	@RequestMapping(value="/ctof/{celsius}")
	public String cToFPV(@PathVariable int celsius, Model model) {
		System.out.println("���� Ȯ�� : " + celsius);
		double fahrenheit = celsius * 1.8 + 32;
		System.out.println("ȭ�� Ȯ�� : " + fahrenheit);
		
		model.addAttribute("fh", fahrenheit);
		model.addAttribute("c", celsius);
		
		return "ctof";
	}
	
	
	// void Ÿ�� ��Ʈ�ѷ��� Ư¡
	// void Ÿ���� return���� ���¸�ŭ view������ �̸��� �׳� url����.jsp�� �ڵ� ���� �ع�����.
	@RequestMapping(value="/voidreturn")
	public void voidTest(int a, int b, Model model) {
		System.out.println("void ��Ʈ�ѷ��� ���ϱ����� �ʿ� ����.");
		System.out.println("a Ȯ�� : " + a);
		System.out.println("b Ȯ�� : " + b);
		
		int sum = a + b;
		model.addAttribute("sum", sum);
	}
}
