package org.ict.service;

import java.util.List;

import org.ict.domain.ReplyVO;
import org.ict.mapper.BoardMapper;
import org.ict.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor  
public class ReplyServiceImpl implements ReplyService{
	
	@Autowired
	private ReplyMapper replyMapper;
	
	@Autowired
	private BoardMapper boardMapper;
	

	@Override
	public List<ReplyVO> replyList(Long b_no) {
		return replyMapper.getReplyList(b_no);
	}

	@Transactional
	@Override
	public void addReply(ReplyVO vo) {
		replyMapper.create(vo);
		boardMapper.updateReplyCount(vo.getB_no(), 1);
	}

	@Override
	public void modifyReply(ReplyVO vo) {
		replyMapper.update(vo);
	}

	@Transactional
	@Override
	public void removeReply(Long r_no) {
		Long b_no = replyMapper.getBoardNum(r_no);
		replyMapper.delete(r_no);		
		boardMapper.updateReplyCount(b_no, -1);
	}
	
}
