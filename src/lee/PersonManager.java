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
 * ��վ: <a href="http://www.crazyit.org">���Java����</a> <br/>
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
		// ����һ��Person����
		Person p = new Person();

		// ����Person��NameΪYeeku�ַ���
		p.setName("Yeeku");
		p.setAge(29);
		Interest interest = new Interest();
		interest.setInterest_content("����");
		p.getInterests().add(interest);

		School school = new School();
		school.setSchool_name("������ѧ");
		school.setList_order(0);
		session.persist(school);
		p.setSchool(school);

		// �־û�Person����
		session.persist(p);
		// ����һ��˲̬��Address����
		Address a2 = new Address("�Ϻ����"); // ��
		p.getAddrs().add(a2);
		a2.setPerson(p);
		session.save(a2);
		tx.commit();
		HibernateUtil.closeSession();
	}

	// 1.person��school�ǵ���N��1����
	// 2.person��address��˫��1��N����
	// 3.person��interest�ǵ���N��N����

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
		//����Person����
		Person1 yeeku = new Person1();
		yeeku.setName(new Name("crazyit.org", "���Java����"));
		yeeku.setEmail("test@crazyit.org");
		yeeku.setPet(new Cat("Garfield", "��ɫ"));
		session.save(yeeku);
		tx.commit();
		HibernateUtil.closeSession();
	}

}
