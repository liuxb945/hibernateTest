package org.crazyit.app.domain;

import java.util.HashSet;
import java.util.Set;

public class Person2
{
	//定义标识属性
	private Integer id;
	//定义Person实例的name属性
	private String name;
	//定义Person实例的age属性
	private int age;
	//定义Person和MyEvent之间的关联关系
	private Set<MyEvent> myEvents = new HashSet<MyEvent>();
	//定义一个集合属性
	private Set<String> emails = new HashSet<String>();
	
	//无参数的构造器
	public Person2()
	{
	}
	//初始化全部属性的构造器
	public Person2(Integer id , String name , int age)
	{
		this.id = id;
		this.name = name;
		this.age = age;
	}
	
	//id属性的setter和getter方法
	public void setId(Integer id)
	{
		this.id = id;
	}
	public Integer getId()
	{
		return this.id;
	}
	
	//name属性的setter和getter方法
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}
	
	//age属性的setter和getter方法
	public void setAge(int age)
	{
		this.age = age;
	}
	public int getAge()
	{
		return this.age;
	}
	
	//myEvents属性的setter和getter方法
	public void setMyEvents(Set<MyEvent> myEvents)
	{
		this.myEvents = myEvents;
	}
	public Set<MyEvent> getMyEvents()
	{
		return this.myEvents;
	}
	
	//emails属性的setter和getter方法
	public void setEmails(Set<String> emails)
	{
		this.emails = emails;
	}
	public Set<String> getEmails()
	{
		return this.emails;
	}
}
