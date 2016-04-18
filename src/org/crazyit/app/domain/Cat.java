package org.crazyit.app.domain;

import javax.persistence.Embeddable;

//修饰组件属性类
@Embeddable
public class Cat
{
	private String name;
	private String color;
	
	//无参数的构造器
	public Cat()
	{
	}
	//初始化全部属性的构造器
	public Cat(String name , String color)
	{
		this.name = name;
		this.color = color;
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
	
	//color属性的setter和getter方法
	public void setColor(String color)
	{
		this.color = color;
	}
	public String getColor()
	{
		return this.color;
	}
}
