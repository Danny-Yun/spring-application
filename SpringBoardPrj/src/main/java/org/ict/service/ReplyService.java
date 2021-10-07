package org.ict.service;

import java.util.List;

import org.ict.domain.ReplyVO;

public interface ReplyService {
	
	public List<ReplyVO> replyList(Long b_no);
	
	public void addReply(ReplyVO vo);
	
	public void modifyReply(ReplyVO vo);
	
	public void removeReply(Long r_no);
	
}
