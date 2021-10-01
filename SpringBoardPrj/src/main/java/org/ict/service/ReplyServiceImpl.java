package org.ict.service;

import java.util.List;

import org.ict.domain.ReplyVO;
import org.ict.mapper.BoardMapper;
import org.ict.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor  
public class ReplyServiceImpl implements ReplyService{
	
	@Autowired
	private ReplyMapper replyMapper;

	@Override
	public List<ReplyVO> replyList(Long b_no) {
		return replyMapper.getReplyList(b_no);
	}

	@Override
	public void addReply(ReplyVO vo) {
		replyMapper.create(vo);
	}

	@Override
	public void modifyReply(ReplyVO vo) {
		replyMapper.update(vo);
	}

	@Override
	public void removeReply(Long r_no) {
		replyMapper.delete(r_no);		
	}
	
	
	
}
