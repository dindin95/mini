package com.luna.member.service;

import java.util.ArrayList;
import java.util.Iterator;

import com.luna.member.dao.MemberDAO;
import com.luna.member.dto.MemberDTO;

public class MemberService {

	MemberDAO dao = new MemberDAO();
	ArrayList<MemberDTO> arr = new ArrayList<MemberDTO>();

	// ���̵�üũ
	public boolean check(String userid) {

		boolean result = dao.check(userid);

		return result;
	}

	// ȸ������ ȣ��
	public void insert(MemberDTO dto) {

		dao.insert(dto);

	}

	// ���� ����
	public void modify(MemberDTO dto) {

		dao.update(dto);

	}

	// ���� �˻�
	public MemberDTO selectOne(String userid) {

		MemberDTO dto = dao.selectOne(userid);

		return dto;

	}

	// ���̵� ����

	public void remove(String userid) {

		dao.remove(userid);

	}
	
	
	//��ü��ȸ
	
	public ArrayList<MemberDTO> list(){
		
		arr = dao.list();

		return arr;
	}

}
