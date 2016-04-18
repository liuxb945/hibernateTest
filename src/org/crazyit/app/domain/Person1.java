package org.crazyit.app.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="person1")
public class Person1
{
	/* 指定使用复合主键类是Name */
	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name="first"
			, column=@Column(name="person_first")),
		@AttributeOverride(name="last"
			, column=@Column(name="person_last" , length=20))
	})
	private Name name;
	//普通属性
	@Column(name="person_email")
	private String email;
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="name" 
			, column=@Column(name="cat_name" , length=35)),
		@AttributeOverride(name="color" 
			, column=@Column(name="cat_color"))
	})
	//组件属性，代表此人拥有的宠物
	private Cat pet;

	//name属性的setter和getter方法
	public void setName(Name name)
	{
		this.name = name;
	}
	public Name getName()
	{
		return this.name;
	}
	
	//email属性的setter和getter方法
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getEmail()
	{
		return this.email;
	}
	
	//pet属性的setter和getter方法
	public void setPet(Cat pet)
	{
		this.pet = pet;
	}
	public Cat getPet()
	{
		return this.pet;
	}
}
