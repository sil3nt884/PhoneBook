package com.phonebook.hackajob.pojo;

public class Contact {

	
	private String name;
	private String phonenumber;
	private String address;
	private int  id;
	
	
	public Contact(int id ,String name, String phonenumber, String address) {
		this.id= id;
		this.name=name;
		this.phonenumber=phonenumber;
		this.address=address;
	}
	
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
