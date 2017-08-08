package com.sample.notice.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.sample.notice.model.Notice;
import com.sample.notice.model.Paging;

@Repository
public class NoticeDAOImpl extends HibernateDaoSupport implements NoticeDAO {
	@Autowired
	public NoticeDAOImpl(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Notice> getNoticeList(Paging paging) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Notice.class);
		criteria.addOrder(Order.desc("seq"));
		return (List<Notice>)getHibernateTemplate().findByCriteria(criteria, (paging.getPage() - 1) * paging.getCount(), paging.getCount());
	}

	@Override
	public Notice getNotice(Integer seq) {
		return getHibernateTemplate().get(Notice.class, seq);
	}

	@Override
	public boolean addNotice(Notice notice) {
		getHibernateTemplate().merge(notice);
		return true;
	}
}
