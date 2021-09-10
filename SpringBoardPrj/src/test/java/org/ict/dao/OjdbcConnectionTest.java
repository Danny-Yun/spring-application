package org.ict.dao;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

// @Log4j는 로깅 기능을 쓸 수 있도록 도와준다. 
// System.out.println();의 경우 로깅만을 목적으로 나온 기능이 아니기 때문에 메모리를 잡아먹게 된다.
// 따라서 로그를 찍을 때 로깅만 할 수 있도록 Log4j를 활용한다. 
// 참고로 Log4j2는 spring-boot에서 쓰고, Log4h는 spring에서 사용한다.
@Log4j
public class OjdbcConnectionTest {
	
	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 이 클래스 파일을 실행했을 때, @test가 붙은 메서드만 실행한다.
	@Test
	public void testConnection() {
		try(Connection con = 
				DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1",  // 접속주소
											"mytest",  // 계정 아이디
											"mytest"   // 계정 비번
											)) {
			log.info(con);
			log.info("정상적으로 연결되었습니다.");
		} catch (Exception e) {
			// 접속이 정상적이지 않을 때 출력할 내용을 아래에 작성한다.
			// fail 함수를 이용해 우측 Failure Trace에 메시지를 띄운다.
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testConnection2() {
		log.info("이 코드는 실행이 안됩니다.");
	}
}
