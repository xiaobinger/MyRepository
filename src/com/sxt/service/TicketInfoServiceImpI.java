package com.sxt.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sxt.dao.TicketInfoDaoMapper;
import com.sxt.entity.TicketInfo;

@Transactional
@Component("ticketInfoService")
public class TicketInfoServiceImpI implements TicketInfoService {
	@Resource(name="ticketInfoDaoMapper")
	private TicketInfoDaoMapper ticketInfoDaoMapper;

	@Override
	public List<TicketInfo> findTicketBookWithPage(Map<String, Object> map) {
		List<TicketInfo> list=ticketInfoDaoMapper.findTicketBookWithPage(map);
		return list;
	}

	@Override
	public int getBookCount() {
		int count=ticketInfoDaoMapper.getBookCount();
		return count;
	}

	@Override
	public TicketInfo getBookInfoById(int bookId) {
		return ticketInfoDaoMapper.getBookInfoById(bookId);
	}

	@Override
	public boolean delTicketBookInfo(int bookId) {
		int flag=ticketInfoDaoMapper.delTicketBookInfo(bookId);
		if(flag!=0){
			return true;
		}
		return false;
	}

	@Override
	public List<TicketInfo> findAllBooks() {
		
		return ticketInfoDaoMapper.findAllBooks();
	}
	
}
