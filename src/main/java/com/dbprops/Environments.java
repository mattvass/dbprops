package com.dbprops;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Environments")
public class Environments {
	private int id;
	private String env;
	private String name;
	private String value;	
	
	public Environments(){}
	
	public Environments(int id, String env, String name, String value){
		this.id = id;
		this.env = env;
		this.name = name;
		this.value = value;
	}
	
	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
