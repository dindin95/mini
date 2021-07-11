package com.luna.member.service;

import java.util.ArrayList;
import java.util.Iterator;

import com.luna.member.dao.MemberDAO;
import com.luna.member.dto.MemberDTO;

public class MemberService {

	MemberDAO dao = new MemberDAO();
	ArrayList<MemberDTO> arr = new ArrayList<MemberDTO>();

	// 아이디체크
	public boolean check(String userid) {

		boolean result = dao.check(userid);

		return result;
	}

	// 회원가입 호출
	public void insert(MemberDTO dto) {

		dao.insert(dto);

	}

	// 정보 수정
	public void modify(MemberDTO dto) {

		dao.update(dto);

	}

	// 조건 검색
	public MemberDTO selectOne(String userid) {

		MemberDTO dto = dao.selectOne(userid);

		return dto;

	}

	// 아이디 삭제

	public void remove(String userid) {

		dao.remove(userid);

	}
	
	
	//전체조회
	
	public ArrayList<MemberDTO> list(){
		
		arr = dao.list();

		return arr;
	}

}
