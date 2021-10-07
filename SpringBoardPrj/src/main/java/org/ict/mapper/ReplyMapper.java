package org.ict.mapper;

import java.util.List;

import org.ict.domain.ReplyVO;

public interface ReplyMapper {
	
	public List<ReplyVO> getReplyList(Long b_no);
	
	public void create(ReplyVO vo);
	
	public void update(ReplyVO vo);
	
	public void delete(Long r_no);
	
	public Long getBoardNum(Long r_no);
}
