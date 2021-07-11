package com.luna.member;

import java.lang.reflect.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

import com.luna.member.dao.MemberDAO;
import com.luna.member.dto.MemberDTO;
import com.luna.member.service.MemberService;

public class MemberUI {

	ArrayList<MemberDTO> arr = new ArrayList<MemberDTO>();
	MemberService service = new MemberService();

	// ȸ������ ȣ��
	public void insert(Scanner scanner) {

		while (true) {
			System.out.println("���̵� �Է��ϼ���");
			String userid = scanner.nextLine();

			// ���̵�üũ
			boolean result = service.check(userid);

			if (result == false) {
				System.out.println("�н����带 �Է��ϼ���");
				String userpw = scanner.nextLine();
				System.out.println("�̸��� �Է��ϼ���");
				String name = scanner.nextLine();
				System.out.println("�̸����� �Է��ϼ���");
				String email = scanner.nextLine();

				MemberDTO dto = new MemberDTO(userid, userpw, name, email);
				service.insert(dto);
				arr.add(dto);

			} else {
				System.out.println("���̵� �����մϴ�");
			}

			System.out.println("����Ͻðڽ��ϱ�");
			String yn = scanner.nextLine();

			if ("n".equals(yn)) {
				break;
			}

		}

	}// insert

	public void modify(Scanner scanner) {

		while (true) {

			System.out.println("���� �� ���̵� �Է��ϼ���");
			String userid = scanner.nextLine();

			boolean result = service.check(userid);

			if (result == true) {
				System.out.println("���� �� ��й�ȣ�� �Է��ϼ���");
				String userpw = scanner.nextLine();
				System.out.println("���� �� �̸����� �Է��ϼ���");
				String email = scanner.nextLine();

				MemberDTO dto = new MemberDTO(userid, userpw, email);
				service.modify(dto);

				for (int i = 0; i < arr.size(); i++) {

					if (arr.get(i).getUserid() == userid) {

						arr.set(i, dto);

					} else {
						System.out.println("�������� �ʴ� ���̵� �Դϴ�");
					}
					System.out.println("��� �Է��Ͻðڽ��ϱ�");
					String yn = scanner.nextLine();

					if ("n".equals(yn)) {

						break;

					}
				}
			}

		}
	}// modify

	public void remove(Scanner scanner) {

		while (true) {
			System.out.println("���� �� ���̵� �Է��ϼ���");
			String userid = scanner.nextLine();

			boolean result = service.check(userid);

			if (result == true) {
				service.remove(userid);
				System.out.println("�����Ǿ����ϴ�");
			} else {
				System.out.println("�������� �ʴ� ���̵� �Դϴ�");
			}
			System.out.println("��� �Է��Ͻðڽ��ϱ�");
			String yn = scanner.nextLine();

			if ("n".equals(yn)) {
				break;
			}

		}
	}// remove

	public void selectOne(Scanner scanner) {

		while (true) {
			System.out.println("��ȸ �� ���̵� �Է��ϼ���");
			String userid = scanner.nextLine();

			boolean result = service.check(userid);

			if (result == true) {

				MemberDTO dto = service.selectOne(userid);
				System.out.println("userid : " + dto.getUserid());
				System.out.println("userpw : " + dto.getUserpw());
				System.out.println("name : " + dto.getName());
				System.out.println("email : " + dto.getEmail());

			} else {
				System.out.println("�������� �ʴ� ���̵� �Դϴ�");

			}
			System.out.println("��� �Է��Ͻðڽ��ϱ�");
			String yn = scanner.nextLine();

			if ("n".equals(yn)) {
				break;
			}

		}
	}//selectOne

	
	public void list(Scanner scanner) {
		while (true) {
			
		ArrayList<MemberDTO> arrlist = new ArrayList<MemberDTO>();
		arrlist = service.list();
		

			for(MemberDTO index:arrlist) {
				System.out.println("==============================");
				System.out.println("��ȣ : " + index.getMemberno());
				System.out.println("���̵� : " + index.getUserid());
				System.out.println("��й�ȣ : " + index.getUserpw());
				System.out.println("�̸� : " + index.getName());
				System.out.println("�̸��� : " + index.getEmail());
				System.out.println("�������� : " + index.getRedage());
			}
			
			System.out.println("��� �Է��Ͻðڽ��ϱ�");
			String yn = scanner.nextLine();

			if ("n".equals(yn)) {
				break;
			}

		}
	}

} //class
