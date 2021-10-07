package org.ict.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class BoardVO {
	
	private Long b_no; 
	private String b_title;
	private String b_content;
	private String b_writer;
	private Date b_regdate;
	private Date b_updatedate;
	private Long replyCount;
}
