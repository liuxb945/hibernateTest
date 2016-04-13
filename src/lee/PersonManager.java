package lee;

import org.crazyit.app.domain.Address;
import org.crazyit.app.domain.Interest;
import org.crazyit.app.domain.Person;
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
		// 创建一个瞬态的Address对象
		Address a = new Address("广州天河"); // ①
		// 设置Person的Name为Yeeku字符串
		p.setName("Yeeku");
		p.setAge(29);
		// 设置Person和Address之间的关联关系
		//p.setAddress(a);
		Interest interest=new Interest();
		interest.setInterest_content("篮球");
		p.getInterests().add(interest);
		// 持久化Person对象
		session.persist(p);
		// //创建一个瞬态的Address对象
		// Address a2 = new Address("上海虹口"); //②
		// //修改持久化状态的Person对象
		// p.setAddress(a2); //③
		tx.commit();
		HibernateUtil.closeSession();
	}
	
	@Test
	public void testLoad(){
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		Person p=(Person) session.load(Person.class, new Integer(6));
		System.out.println(p.getAddress().getAddressDetail());
		p.getAddress().setAddressDetail("dddd");
		tx.commit();
		HibernateUtil.closeSession();
	}
	
	@Test
	public void testLoad1ToN(){
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		Person p=(Person) session.load(Person.class, new Integer(2));
		System.out.println(p.getSchools().size());
		System.out.println(((School)(p.getSchools().toArray()[0])).getSchool_name());
		tx.commit();
		HibernateUtil.closeSession();
	}
	
	@Test
	public void testLoadNTo1(){
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		Person p=(Person) session.load(Person.class, new Integer(6));
		System.out.println(p.getAddress().getAddressDetail());
		tx.commit();
		HibernateUtil.closeSession();
	}
	
	@Test
	public void testLoadNToN(){
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		Person p=(Person) session.load(Person.class, new Integer(8));
		System.out.println(p.getInterests().size());
		tx.commit();
		HibernateUtil.closeSession();
	}

}
