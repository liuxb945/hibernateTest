package lee;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.crazyit.app.domain.Enrolment;
import org.crazyit.app.domain.Student;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * Description:
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a> 
 * <br/>Copyright (C), 2001-2012, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */ 
public class CriteriaTest
{
	public static void main(String[] args)
	{
		CriteriaTest criteriaTest = new CriteriaTest();
//		criteriaTest.query();
//		criteriaTest.queryWithJoin();
		criteriaTest.queryWithFecth();
		HibernateUtil.sessionFactory.close();
	}
	private void query()
	{
		//��Session������
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		//ʹ��createCriteria��ʼ������ѯ
		List l = session.createCriteria(Student.class)
			//����Student�����Խ��й�������
			.add( Restrictions.eq("name" , "�����"))
			.list();
		Iterator it = l.iterator();
		System.out.println("=====��������ѯ��ȡ����ѧ����¼=====");
		while (it.hasNext())
		{
			Student s = (Student)it.next();
			System.out.println(s.getName());
			Set enrolments = s.getEnrolments();
			Iterator iter = enrolments.iterator();
			System.out.println("=====��ȡ-" + s.getName()
				+ "-��ѡ�μ�¼=====");
			while(iter.hasNext())
			{
				Enrolment e = (Enrolment)iter.next();
				System.out.println(e.getCourse().getName());
			}
		}
		tx.commit();
		HibernateUtil.closeSession();
	}
	//ʾ�����ݹ���ʵ������Թ�������
	private void queryWithJoin()
	{
		//��Session������
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		//ʹ��createCriteria��ʼ������ѯ
		List l = session.createCriteria(Student.class)
			//�˴�������������������Student�Ѿ����ڵ�����
			.add( Restrictions.gt("studentNumber" , 20050231))
			//���Ҫ���Ӷ�Student�Ĺ���������Ե�����
			//���������createCriteria()
			//����˹��������Ǽ��ϣ���ֻҪ����������һ���������������������������
//			.createCriteria("enrolments")
//			.add( Restrictions.gt("semester" , 2))
			.createAlias("enrolments", "en")
			.add( Restrictions.gt("en.semester" , 2))
			.list();
		Iterator it = l.iterator();
		System.out.println("=====����������ѯ��ȡ����ѧ����¼=====");
		while (it.hasNext())
		{
			Student s = (Student)it.next();
			System.out.println(s.getName());
			Set enrolments = s.getEnrolments();
			Iterator iter = enrolments.iterator();
			System.out.println("=====��ȡ-" + s.getName()
				+ "-��ѡ�μ�¼=====");
			while(iter.hasNext())
			{
				Enrolment e = (Enrolment)iter.next();
				System.out.println(e.getCourse().getName());
			}
		}
		tx.commit();
		HibernateUtil.closeSession();
	}
	//ʾ�����ݹ���ʵ������Թ�������
	private void queryWithFecth()
	{
		//��Session������
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		//ʹ��createCriteria��ʼ������ѯ
		List l = session.createCriteria(Student.class)
			//�˴�������������������Student�Ѿ����ڵ�����
			.add( Restrictions.gt("studentNumber" , 20050231))
			.setFetchMode("enrolments", FetchMode.JOIN)
			.list();
		tx.commit();
		HibernateUtil.closeSession();
		Iterator it = l.iterator();
		System.out.println("=====����Ԥ��ʼ����������ѯ��ȡ����ѧ����¼=====");
		while (it.hasNext())
		{
			Student s = (Student)it.next();
			System.out.println(s.getName());
			//Session�رպ����Student�Ĺ���ʵ��
			Set enrolments = s.getEnrolments();
			System.out.println(enrolments);
		}
	}
}
