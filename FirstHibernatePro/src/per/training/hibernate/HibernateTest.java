package per.training.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import per.training.dto.UserDetails;

public class HibernateTest {

	public static void main(String[] args) {

		UserDetails user = new UserDetails();
		user.setUserId(1);
		user.setUserName("first user");
		
		SessionFactory sessionFactory = null;
		//@SuppressWarnings("resource") ..
		Session session = null;
		
		try {
		sessionFactory = new Configuration().configure().buildSessionFactory();
		
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.save(user);

		session.getTransaction().commit();  // ??
		
		}
		catch (Exception e) {
			session.getTransaction().rollback();
			
		}
		finally {
				session.close();
				user = null;
		}
		
		
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		user = (UserDetails)session.get(UserDetails.class, 1);
		System.out.println("retrievid " + user.getUserName());
		
		session.close();
		user = null;
		
	}
}
