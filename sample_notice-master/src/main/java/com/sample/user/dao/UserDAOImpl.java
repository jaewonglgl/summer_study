package com.sample.user.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.sample.user.model.User;

@Repository
public class UserDAOImpl extends HibernateDaoSupport implements UserDAO {
	@Autowired
	public UserDAOImpl(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}

	@Override
	public User getUser(String id) {
		return getHibernateTemplate().get(User.class, id);
	}
}
