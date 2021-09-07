package org.ict.controller.di;

import org.ict.controller.di.classfile.Broadcast;
import org.ict.controller.di.classfile.Singer;
import org.ict.controller.di.classfile.Stage;

public class DiMainJavaVer {

	public static void main(String[] args) {
		// ���� ��ü ȣ��
		Singer singer = new Singer();
		singer.sing();
		
		// ���� ��ü ���� �� ��� ȣ��
		// ���� ��ü�� ������ �ݵ�� singer�� ���� �־�� �ϹǷ� ����� singer�� �����Ѵ�. 
		Stage stage = new Stage(singer);
		stage.perform();
		
		// ��� ���븦 �����ϴ� ��� ȣ��
		Broadcast broadcast = new Broadcast(stage);
		broadcast.broadcast();

	}
}
