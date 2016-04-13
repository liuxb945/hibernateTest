package org.crazyit.app.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
public class Person {
	// ��ʶ����
	private Integer id;
	// Person��name����
	private String name;
	// ����Person��age����
	private int age;
	// ���ù���ʵ�������
	private Address address;

	// �������ԣ������ö��������ѧУ
	private Set<School> schools = new HashSet<School>();
	// ��Ȥ
	private Set<Interest> interests;

	// id���Ե�setter��getter����
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	// name���Ե�setter��getter����
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	// age���Ե�setter��getter����
	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return this.age;
	}

	// address���Ե�setter��getter����
	public void setAddress(Address address) {
		this.address = address;
	}

	public Address getAddress() {
		return this.address;
	}

	/*
	 * public List<String> getSchools() { return schools; } public void
	 * setSchools(List<String> schools) { this.schools = schools; }
	 */
	public Set<School> getSchools() {
		return schools;
	}

	public void setSchools(Set<School> schools) {
		this.schools = schools;
	}

	public Set<Interest> getInterests() {
		return interests;
	}

	public void setInterests(Set<Interest> interests) {
		this.interests = interests;
	}
}