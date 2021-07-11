package com.luna.member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.luna.member.dto.MemberDTO;

public class MemberDAO {
//출력을 하는거니깐 DTO
//	//db연결 메서드
//	//db연결을 해서 insert 기능메서드에서 호출해서 쓸꺼니깐 private
//	//메서드에서 호출을 하는거니깐 외부에서 호출 할 필요가 없음
	private Connection getConnection() {
		String className = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "hr";
		String pwd = "hr";

		Connection conn = null; // 연결객체
		try {
			// conn객체 만듬
			Class.forName(className);
			conn = DriverManager.getConnection(url, user, pwd);
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e);
		}

		return conn; // connction을 지역변수로 썼으니깐 값을 보내야됨

	}

	public boolean check(String userid) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		StringBuilder sql = new StringBuilder();
		sql.append(" select userid from member where userid = ? ");
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();

			// 커서를 이동시키면 값을 찾는다
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			close(pstmt, conn);
		}

		return false;

	}

	// insert 기능메서드
	// 제대로 동작했다는것을 알려고 리턴값 줌 executeUpdate가 int타입이여서 int로 리턴
	public int insert(MemberDTO dto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		StringBuilder sql = new StringBuilder();
		sql.append("  insert  into   member(	memberno, userid, userpw, name, email, redate )");
		sql.append("  values (boardseq.nextval, ?,?,?,?,sysdate )                            ");

		int result = 0; // try안에서 int result를 만들면 마지막 리턴을 못함

		try {

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getUserid());
			pstmt.setString(2, dto.getUserpw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getEmail());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			close(pstmt, conn);
		}
		return result;

	}

	// update 기능메서드
	public void update(MemberDTO dto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		StringBuilder sql = new StringBuilder();
		sql.append(" update board set               ");
		sql.append("                   userid = ?   ");
		sql.append("                   email = ?    ");
		sql.append(" where bno = ?                  ");

		try {
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getUserpw());
			pstmt.setString(2, dto.getEmail());
			pstmt.setInt(1, dto.getMemberno());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	public MemberDTO selectOne(String userid) {

		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from member where userid = ? ");

		ResultSet rs = null;
		MemberDTO dto = new MemberDTO();
		try {
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userid);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setUserid(rs.getString("userid"));
				dto.setUserpw(rs.getString("userpw"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setRedage(rs.getDate("redate"));
				
				return dto;

			}

		} catch (SQLException e) {
			System.out.println(e);
		}

		return dto;
	}
	
	// delete 기능메서드
	public void remove(String userid) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		StringBuilder sql= new StringBuilder();
		sql.append("delete from member where userid = ? ");

		int result =0;
		
		try {
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userid);
			
			pstmt.executeUpdate();
		
		}catch (SQLException e) {
			System.out.println(e);
		}

	}
	
	// select 기능메서드
	public ArrayList<MemberDTO> list() {
		//db연결해서 자료를 받아야됨
		//자료구조 list map set  => ArrayList : db연결 후에 ArrayList에 담아서 리턴
		ArrayList<MemberDTO> arr = new ArrayList<MemberDTO>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select memberno, userid, userpw, name, email, redate  from member ");
		
		ResultSet rs = null;
		
		try {
			pstmt =conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				
				dto.setMemberno(rs.getInt("memberno"));
				dto.setUserid(rs.getString("userid"));
				dto.setUserpw(rs.getString("userpw"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setRedage(rs.getDate("redate"));
				
				arr.add(dto);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		
		return arr;
	}

	// close 기능 메서드

	// pstmt conn close 하는 기능
	private void close(PreparedStatement pstmt, Connection conn) // pstmt, conn close 하는 기능
	{
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException e) {
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
			}
	}

}
