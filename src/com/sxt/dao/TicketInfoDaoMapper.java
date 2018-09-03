package com.sxt.dao;

import java.util.List;
import java.util.Map;

import com.sxt.entity.TicketInfo;

public interface TicketInfoDaoMapper {

	List<TicketInfo> findTicketBookWithPage(Map<String, Object> map);

	int getBookCount();

	TicketInfo getBookInfoById(int bookId);

	int delTicketBookInfo(int bookId);

	List<TicketInfo> findAllBooks();

}
