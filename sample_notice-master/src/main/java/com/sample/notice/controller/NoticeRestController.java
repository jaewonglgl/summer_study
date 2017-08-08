package com.sample.notice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.notice.dao.NoticeDAO;
import com.sample.notice.model.Notice;
import com.sample.notice.model.Paging;

@RestController
@RequestMapping(value = "notice/json")
public class NoticeRestController {
	private static final Logger logger = LoggerFactory
			.getLogger(NoticeRestController.class);

	@Autowired
	private NoticeDAO noticeDAO;

	@RequestMapping(value = "list")
	public List<Notice> list(Paging paging) {
		return noticeDAO.getNoticeList(paging);
	}

	@RequestMapping(value = "/{seq}")
	public Notice seq(Paging paging, @PathVariable("seq") int seq) {
		return noticeDAO.getNotice(seq);
	}
}
