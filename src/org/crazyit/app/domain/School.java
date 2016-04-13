package org.crazyit.app.domain;

public class School {
	private Integer id;
	private Integer person_id;
	private String school_name;
	private Integer list_order;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPerson_id() {
		return person_id;
	}
	public void setPerson_id(Integer person_id) {
		this.person_id = person_id;
	}
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}
	public Integer getList_order() {
		return list_order;
	}
	public void setList_order(Integer list_order) {
		this.list_order = list_order;
	}

}
