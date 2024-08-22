package com.user_dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.user_dto.User;

@Repository
public class UserDao {
	
	@Autowired
	EntityManagerFactory emf;
	
	public void addUser(User u) {
		EntityManager em= emf.createEntityManager();
		EntityTransaction et= em.getTransaction();
		
		et.begin();
		em.persist(u);
		et.commit();
	}
	
	public User validateUser(String email, String password) {
		EntityManager em=emf.createEntityManager();
		
		Query query= em.createQuery("select u from User u where u.email=?1 and u.password=?2");
		query.setParameter(1, email);
		query.setParameter(2, password);
		
		try {
			User u=(User)query.getSingleResult();
			return u;
		}catch(NoResultException e){
			return null;
			
		}
	}
	
	public List<User> fetchUser(){
		EntityManager em=emf.createEntityManager();
		
		Query query=em.createQuery("select u from User u");
		return  query.getResultList();
		
	}
	
	public String setPassword(String email, String password, String newpassword) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		Query query=em.createQuery("select u from User u where u.email=?1 and u.password=?2");
		query.setParameter(1, email);
		query.setParameter(2, password);
		
			List<User> users=(List<User>)query.getResultList();
			try {
			if(!users.isEmpty()) {
				User u=users.get(0);
				u.setPassword(newpassword);
				et.begin();
				em.merge(u);
				et.commit();
				return "Yes";
			}else {
				return "No";
				}
			}catch(Exception e) {
				e.printStackTrace();
				return "NO";
			}finally {
				em.close();
			}
		
	}
		
	

}
