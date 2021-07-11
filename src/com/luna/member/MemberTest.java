package com.luna.member;

import java.util.Scanner;

import com.luna.member.service.MemberService;

public class MemberTest {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		MemberUI ui = new MemberUI();

		while (true) {

			System.out.println("====================================================");
			System.out.println("1.회원가입 |2.회원수정 |3.회원삭제 |4.회원보기 |5.전체조회 |6.종료");
			System.out.println("====================================================");
			int choice = Integer.parseInt(scanner.nextLine());
			switch (choice) {
			case 1:
				ui.insert(scanner);
				break;
			case 2:
				ui.modify(scanner);
				break;
			case 3:
				ui.remove(scanner);
				break;
			case 4:
				ui.selectOne(scanner);
				break;
			case 5:
				ui.list(scanner);
				break;
			case 6:
				break;
			default:
				break;

			}
		}

	}// main

}
