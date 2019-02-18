package kr.ayukawa.hibernate;

import kr.ayukawa.hibernate.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Date;

public class AppMain {
	public static void main(String[] args) {
		Session session = null;
		try {
			// build Session Factory
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.configure().loadProperties("hibernate.cfg.xml").build();
			SessionFactory sessionFactory = new Configuration().buildSessionFactory(serviceRegistry);

			// open session
			session = sessionFactory.openSession();
			session.beginTransaction();

			User user = null;

			for(int i = 101; i <= 105; i++) {
				user = new User();
				user.setUserId(i);
				user.setUserName("Editor " + i);
				user.setCreatedBy("Admin");
				user.setCreatedDate(new Date());

				session.save(user);
			}
			session.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
			if(session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
		} finally {
			if(session != null)
				session.close();
		}
	}
}
