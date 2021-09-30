package org.ict.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class ReplyVO {
	
	private Long r_no;
	private Long b_no;
	private String reply;
	private String replyer;
	private Date regDate; 
	private Date updateDate; 
}
