package model;

import java.util.ArrayList;

public class Student {
	private String name;
	private ArrayList<Student> zone;
	public ArrayList getZone() {
		return zone;
	}
	public void setZone(ArrayList zone) {
		this.zone = zone;
	}
	public Student(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
