package org.ict.dao;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;
/*
@Log4j
public class MySQLConnectionTest {
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testConnection() {
		try(Connection con = 
				DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mysql?useSSL=false&serverTimezone=UTC",  // 접속주소
											"root",  // 계정 아이디
											"mysql"   // 계정 비번
											)) {
			log.info(con);
			log.info("정상적으로 연결되었습니다.");
		} catch (Exception e) {
			// 접속이 정상적이지 않을 때 출력할 내용을 아래에 작성한다.
			// fail 함수를 이용해 우측 Failure Trace에 메시지를 띄운다.
			fail(e.getMessage());
		}
		
	}

}
*/