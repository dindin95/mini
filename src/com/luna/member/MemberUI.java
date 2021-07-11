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

	// 회원가입 호출
	public void insert(Scanner scanner) {

		while (true) {
			System.out.println("아이디를 입력하세요");
			String userid = scanner.nextLine();

			// 아이디체크
			boolean result = service.check(userid);

			if (result == false) {
				System.out.println("패스워드를 입력하세요");
				String userpw = scanner.nextLine();
				System.out.println("이름을 입력하세요");
				String name = scanner.nextLine();
				System.out.println("이메일을 입력하세요");
				String email = scanner.nextLine();

				MemberDTO dto = new MemberDTO(userid, userpw, name, email);
				service.insert(dto);
				arr.add(dto);

			} else {
				System.out.println("아이디가 존재합니다");
			}

			System.out.println("계속하시겠습니까");
			String yn = scanner.nextLine();

			if ("n".equals(yn)) {
				break;
			}

		}

	}// insert

	public void modify(Scanner scanner) {

		while (true) {

			System.out.println("수정 할 아이디를 입력하세요");
			String userid = scanner.nextLine();

			boolean result = service.check(userid);

			if (result == true) {
				System.out.println("변경 할 비밀번호를 입력하세요");
				String userpw = scanner.nextLine();
				System.out.println("변경 할 이메일을 입력하세요");
				String email = scanner.nextLine();

				MemberDTO dto = new MemberDTO(userid, userpw, email);
				service.modify(dto);

				for (int i = 0; i < arr.size(); i++) {

					if (arr.get(i).getUserid() == userid) {

						arr.set(i, dto);

					} else {
						System.out.println("존재하지 않는 아이디 입니다");
					}
					System.out.println("계속 입력하시겠습니까");
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
			System.out.println("삭제 할 아이디를 입력하세요");
			String userid = scanner.nextLine();

			boolean result = service.check(userid);

			if (result == true) {
				service.remove(userid);
				System.out.println("삭제되었습니다");
			} else {
				System.out.println("존재하지 않는 아이디 입니다");
			}
			System.out.println("계속 입력하시겠습니까");
			String yn = scanner.nextLine();

			if ("n".equals(yn)) {
				break;
			}

		}
	}// remove

	public void selectOne(Scanner scanner) {

		while (true) {
			System.out.println("조회 할 아이디를 입력하세요");
			String userid = scanner.nextLine();

			boolean result = service.check(userid);

			if (result == true) {

				MemberDTO dto = service.selectOne(userid);
				System.out.println("userid : " + dto.getUserid());
				System.out.println("userpw : " + dto.getUserpw());
				System.out.println("name : " + dto.getName());
				System.out.println("email : " + dto.getEmail());

			} else {
				System.out.println("존재하지 않는 아이디 입니다");

			}
			System.out.println("계속 입력하시겠습니까");
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
				System.out.println("번호 : " + index.getMemberno());
				System.out.println("아이디 : " + index.getUserid());
				System.out.println("비밀번호 : " + index.getUserpw());
				System.out.println("이름 : " + index.getName());
				System.out.println("이메일 : " + index.getEmail());
				System.out.println("가입일자 : " + index.getRedage());
			}
			
			System.out.println("계속 입력하시겠습니까");
			String yn = scanner.nextLine();

			if ("n".equals(yn)) {
				break;
			}

		}
	}

} //class
