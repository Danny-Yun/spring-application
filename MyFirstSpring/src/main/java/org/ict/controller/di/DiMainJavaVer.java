package org.ict.controller.di;

import org.ict.controller.di.classfile.Broadcast;
import org.ict.controller.di.classfile.Singer;
import org.ict.controller.di.classfile.Stage;

public class DiMainJavaVer {

	public static void main(String[] args) {
		// 가수 객체 호출
		Singer singer = new Singer();
		singer.sing();
		
		// 무대 객체 생성 후 기능 호출
		// 무대 객체는 생성시 반드시 singer가 먼저 있어야 하므로 무대는 singer에 의존한다. 
		Stage stage = new Stage(singer);
		stage.perform();
		
		// 방송 무대를 송출하는 기능 호출
		Broadcast broadcast = new Broadcast(stage);
		broadcast.broadcast();

	}
}
