package com.sample.notice.dao;

import java.util.List;

import com.sample.notice.model.Notice;
import com.sample.notice.model.Paging;

public interface NoticeDAO {
	List<Notice> getNoticeList(Paging paging);

	Notice getNotice(Integer seq);

	boolean addNotice(Notice notice);
}
