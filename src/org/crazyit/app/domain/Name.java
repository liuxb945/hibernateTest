package org.crazyit.app.domain;

import javax.persistence.Embeddable;

//修饰组件属性类
@Embeddable
public class Name
	implements java.io.Serializable
{
	private String first;
	private String last;
	//无参数的构造器
	public Name()
	{
	}
	//初始化全部属性的构造器
	public Name(String first , String last)
	{
		this.first = first;
		this.last = last;
	}
	
	//first属性的setter和getter方法
	public void setFirst(String first)
	{
		this.first = first;
	}
	public String getFirst()
	{
		return this.first;
	}
	
	//last属性的setter和getter方法
	public void setLast(String last)
	{
		this.last = last;
	}
	public String getLast()
	{
		return this.last;
	}

	//提供重写的equals方法
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj.getClass() == Name.class)
		{
			Name target = (Name)obj;
			if (target.getFirst().equals(first)
				&& target.getLast().equals(last))
			{
				return true;
			}
		}
		return false;
	}

	//提供重写的hashCode方法
	public int hashCode()
	{
		return first.hashCode() + last.hashCode() * 17;
	}
}
