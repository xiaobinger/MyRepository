package com.sxt.service;

import java.util.List;
import java.util.Map;

import com.sxt.entity.TicketInfo;

public interface TicketInfoService {

	List<TicketInfo> findTicketBookWithPage(Map<String, Object> map);

	int getBookCount();

	TicketInfo getBookInfoById(int bookId);

	boolean delTicketBookInfo(int bookId);

	List<TicketInfo> findAllBooks();

}
