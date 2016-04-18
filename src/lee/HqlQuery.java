package lee;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.crazyit.app.domain.*;
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
public class HqlQuery
{
	public static void main(String[] args)
		throws Exception
	{
		HqlQuery mgr = new HqlQuery();
		//���ò�ѯ����
//		mgr.findPersons();
		//���õڶ�����ѯ����
//		mgr.findPersonsByHappenDate();
		//���õڶ�����ѯ����
		mgr.findPersonProperty();
	}
	//��һ����ѯ����
	private void findPersons()
	{
		//���Hibernate Session
		Session sess = HibernateUtil.currentSession();
		//��ʼ����
		Transaction tx = sess.beginTransaction();
		//��HQL��䴴��Query����.
		//ִ��setString����ΪHQL���Ĳ�����ֵ
		//Query����list�������ʲ�ѯ��ȫ��ʵ��
		List pl = sess.createQuery("select distinct p from Person2 p "
			+ "join p.myEvents where title = :eventTitle")
			.setString("eventTitle","����ͨ����")
			.list();
		//������ѯ��ȫ�����
		for (Iterator pit = pl.iterator() ; pit.hasNext(); )
		{
			Person2 p = (Person2)pit.next();
			System.out.println(p.getName());
		}
		//�ύ����
		tx.commit();
		HibernateUtil.closeSession();
	}
	//�ڶ�����ѯ����
	private void findPersonsByHappenDate()throws Exception
	{
		//���Hibernate Session����
		Session sess = HibernateUtil.currentSession();
		Transaction tx = sess.beginTransaction();
		//������Date����
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date start = sdf.parse("2005-01-01");
		System.out.println("ϵͳ��ʼͨ�����ڲ�����" + start);
		//ͨ��Session��createQuery��������Query����
		List pl = sess.createQuery("select distinct p from Person2 p "
			+ "inner join p.myEvents event where event.happenDate "
			+ "between :firstDate and :endDate")
			//���ò���
			.setDate("firstDate",start)
			.setDate("endDate",new Date())
			//���ؽ����
			.list();
		//���������
		for (Iterator pit = pl.iterator() ; pit.hasNext(); )
		{
			Person2 p = ( Person2 )pit.next();
			System.out.println(p.getName());
		}
		tx.commit();
		HibernateUtil.closeSession();
	}
	//��������ѯ��������ѯ����
	private void findPersonProperty()
	{
		//���Hibernate Session
		Session sess = HibernateUtil.currentSession();
		//��ʼ����
		Transaction tx = sess.beginTransaction();
		//��HQL��䴴��Query����.
		//ִ��setString����ΪHQL���Ĳ�����ֵ
		//Query����list�������ʲ�ѯ��ȫ������
		List pl = sess.createQuery("select distinct p.id,  p.name , p.age "
			+ "from Person2 p join "
			+ "p.myEvents")
			.list();
		//������ѯ��ȫ�����
		for (Iterator pit = pl.iterator() ; pit.hasNext(); )
		{
			Object[] objs = (Object[])pit.next();
			System.out.println(java.util.Arrays.toString(objs));
		}
		//�ύ����
		tx.commit();
		HibernateUtil.closeSession();
	}
	
	@Test
	public void findPersonsSchoolJoin()throws Exception
	{
		//���Hibernate Session����
		Session sess = HibernateUtil.currentSession();
		Transaction tx = sess.beginTransaction();
		System.out.println("====ϵͳ����fetch��ѯ====");
		//��HQL��䴴��Query����,HQL���ʹ����ʽ����	
		//��Ϊʹ����fetch�ؼ��֣����Է��ؽ����û�й���ʵ��
		List pl = sess.createQuery("from Person p left join fetch p.school school where "
			+ "school.id = :id")
			.setInteger("id", 3)
			.list();
		tx.commit();
		HibernateUtil.closeSession();
		//���������
		for (Iterator pit = pl.iterator() ; pit.hasNext(); )
		{
			Person p = (Person)pit.next();
			System.out.println(p.getName());
			//��ΪHQL�����ʹ����fecth�ؼ���
			//���Կ�����Session�رպ����Person����ʵ�������
			System.out.println(p.getSchool().getSchool_name());
		}
	}
}