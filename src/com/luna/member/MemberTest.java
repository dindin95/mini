package com.luna.member;

import java.util.Scanner;

import com.luna.member.service.MemberService;

public class MemberTest {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		MemberUI ui = new MemberUI();

		while (true) {

			System.out.println("====================================================");
			System.out.println("1.ȸ������ |2.ȸ������ |3.ȸ������ |4.ȸ������ |5.��ü��ȸ |6.����");
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
