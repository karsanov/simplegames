package com.karsanov.simplegames.model.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.karsanov.simplegames.model.User;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	@Transactional(readOnly = true)
	public User getUser(int id) {
		Session session = sessionFactory.openSession();
        String hql = "from User where id=:userId";       
        Query<User> query = session.createQuery(hql);
        query.setParameter("userId", id);
        User user = query.getSingleResult();
        return user;
	}
	
	@Override
	@Transactional(readOnly = true)
	public User getUser(String name) {
		Session session = sessionFactory.openSession();
		String hql = "from User where name=:userName";       
		Query<User> query = session.createQuery(hql);
		query.setParameter("userName", name);
		User user = query.getSingleResult();
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		Session session = sessionFactory.openSession();
		String hql = "from User";       
		Query<User> query = session.createQuery(hql);
		List<User> users = query.getResultList();
		return users;
	}

}
