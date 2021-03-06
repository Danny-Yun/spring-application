package org.ict.dao;

import static org.junit.Assert.fail;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

// 의존성 주입으로 생성한 Connection Pool 관련 변수를 가져오기 위해서 root-context.xml의 위치를 지정해준다. 
@RunWith(SpringJUnit4ClassRunner.class)
// Beans Graph 내부에 설정되어있는 dataSource를 쓰기 위해 위치를 설정
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class OracleConnectionPoolTest {
	
	// 자동 주입(ContextConfiguration에 설정된 공장 주소에서 맞는 자료형을 넣어줌)
	@Autowired
	private DataSource dataSource;
	
	@Test
	public void testConnection() {
		try(Connection con = dataSource.getConnection()) {
			log.info(con);
			log.info("hikariCP connection");
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}
	
}
