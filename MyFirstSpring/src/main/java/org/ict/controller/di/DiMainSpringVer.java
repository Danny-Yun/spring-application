package org.ict.controller.di;

import org.ict.controller.di.classfile.Broadcast;
import org.springframework.context.support.GenericXmlApplicationContext;

public class DiMainSpringVer {
	

	public static void main(String[] args) {
		// �� �����̳ʿ� ȣ���� �ϼ�ǰ ��ü�� �޾ƿ� �����ϴ� �ڵ带 �ۼ�
		// ȣ��� ����ϴ� ������ ��ü�� GenericXmlApplicationContext�̴�.
		GenericXmlApplicationContext context = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/root-context.xml");
		
		// ���� root-context.xml�̶�� bean-container�� ������ �ϰڴٰ� ������ ������ ���� �� ���忡 �ִ� ��ü�� ������� ���� �� �� �ִ�. 
		// ������ ����� ���� ������ context ��ü�� �̿��� context.getBean("bean�̸�", �ڷ���.class); �� �Է��ϸ� �ȴ�.
		Broadcast broadcast = context.getBean("broadcast", Broadcast.class);
		broadcast.broadcast();
		
		// ȣ���� ������ context�� �ݾ���� �Ѵ�. 
		context.close();
	}
}
