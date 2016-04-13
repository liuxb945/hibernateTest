package org.crazyit.app.domain;

import java.util.HashSet;
import java.util.Set;

public class Interest {
	private Integer interest_id;
	private String interest_content;
	private Set<Person> persons=new HashSet<Person>();
	public Integer getInterest_id() {
		return interest_id;
	}
	public void setInterest_id(Integer interest_id) {
		this.interest_id = interest_id;
	}
	public String getInterest_content() {
		return interest_content;
	}
	public void setInterest_content(String interest_content) {
		this.interest_content = interest_content;
	}
	public Set<Person> getPersons() {
		return persons;
	}
	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

}
