package com.luna.member.dto;

import java.sql.Date;

public class MemberDTO {

	private int memberno;
	private String userid;
	private String userpw;
	private String name;
	private String email;
	private Date redage;



	public MemberDTO(int memberno, String userid, String userpw, String name, String email, Date redage) {
		super();
		this.memberno = memberno;
		this.userid = userid;
		this.userpw = userpw;
		this.name = name;
		this.email = email;
		this.redage = redage;
	}


	public MemberDTO(String userid, String userpw, String name, String email) {
		this.userid=userid;
		this.userpw=userpw;
		this.name=name;
		this.email=email;
	}
	
	public MemberDTO(String userid, String userpw,  String email) {
		this.userid=userid;
		this.userpw=userpw;
		this.email=email;
	}

	
	public MemberDTO() {
		// TODO Auto-generated constructor stub
	}


	public int getMemberno() {
		return memberno;
	}

	public void setMemberno(int memberno) {
		this.memberno = memberno;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpw() {
		return userpw;
	}

	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRedage() {
		return redage;
	}

	public void setRedage(Date redage) {
		this.redage = redage;
	}

}
