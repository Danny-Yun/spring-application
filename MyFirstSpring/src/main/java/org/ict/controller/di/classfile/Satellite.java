package org.ict.controller.di.classfile;

import org.springframework.stereotype.Component;

@Component
public class Satellite {

	private Broadcast broadcast;
	
	public Satellite(Broadcast broadcast) {
		this.broadcast = broadcast;
	}
	
	public void satelliteBroadcast() {
		System.out.print("À§¼º ");
		broadcast.broadcast();
	}
	
}
