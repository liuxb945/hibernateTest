package lee;

import org.crazyit.app.domain.Address;
import org.crazyit.app.domain.Cat;
import org.crazyit.app.domain.Interest;
import org.crazyit.app.domain.Name;
import org.crazyit.app.domain.Person;
import org.crazyit.app.domain.Person1;
import org.crazyit.app.domain.School;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * Description: <br/>
 * 网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> <br/>
 * Copyright (C), 2001-2012, Yeeku.H.Lee <br/>
 * This program is protected by copyright laws. <br/>
 * Program Name: <br/>
 * Date:
 * 
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class PersonManager {
	public static void main(String[] args) {
		PersonManager mgr = new PersonManager();
		mgr.testPerson();
		HibernateUtil.sessionFactory.close();
	}

	private void testPerson() {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		// 创建一个Person对象
		Person p = new Person();

		// 设置Person的Name为Yeeku字符串
		p.setName("Yeeku");
		p.setAge(29);
		Interest interest = new Interest();
		interest.setInterest_content("篮球");
		p.getInterests().add(interest);

		School school = new School();
		school.setSchool_name("北京大学");
		school.setList_order(0);
		session.persist(school);
		p.setSchool(school);

		// 持久化Person对象
		session.persist(p);
		// 创建一个瞬态的Address对象
		Address a2 = new Address("上海虹口"); // ②
		p.getAddrs().add(a2);
		a2.setPerson(p);
		session.save(a2);
		tx.commit();
		HibernateUtil.closeSession();
	}

	// 1.person与school是单向N对1关联
	// 2.person与address是双向1对N关联
	// 3.person与interest是单向N对N关联

	@Test
	public void testLoad() {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		Person p = (Person) session.load(Person.class, new Integer(9));
		System.out.println(p.getAddrs().size());
		System.out.println(p.getSchool().getSchool_name());
		tx.commit();
		HibernateUtil.closeSession();
	}

	@Test
	public void testLoad1ToN() {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		Address a = (Address) session.load(Address.class, new Integer(2));
		System.out.println(a.getPerson().getName());
		System.out.println(a.getPerson().getSchool().getSchool_name());
		tx.commit();
		HibernateUtil.closeSession();
	}

	@Test
	public void testLoadNTo1() {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		Person p = (Person) session.load(Person.class, new Integer(6));
		System.out.println(p.getSchool().getSchool_name());
		tx.commit();
		HibernateUtil.closeSession();
	}

	@Test
	public void testLoadNToN() {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		Person p = (Person) session.load(Person.class, new Integer(11));
		System.out.println(((Interest)(p.getInterests().toArray()[0])).getInterest_content());
		tx.commit();
		HibernateUtil.closeSession();
	}
	
	@Test
	public void testCreatePerson1(){
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		//创建Person对象
		Person1 yeeku = new Person1();
		yeeku.setName(new Name("crazyit.org", "疯狂Java联盟"));
		yeeku.setEmail("test@crazyit.org");
		yeeku.setPet(new Cat("Garfield", "黄色"));
		session.save(yeeku);
		tx.commit();
		HibernateUtil.closeSession();
	}

}
