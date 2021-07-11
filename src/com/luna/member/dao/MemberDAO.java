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
//����� �ϴ°Ŵϱ� DTO
//	//db���� �޼���
//	//db������ �ؼ� insert ��ɸ޼��忡�� ȣ���ؼ� �����ϱ� private
//	//�޼��忡�� ȣ���� �ϴ°Ŵϱ� �ܺο��� ȣ�� �� �ʿ䰡 ����
	private Connection getConnection() {
		String className = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "hr";
		String pwd = "hr";

		Connection conn = null; // ���ᰴü
		try {
			// conn��ü ����
			Class.forName(className);
			conn = DriverManager.getConnection(url, user, pwd);
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e);
		}

		return conn; // connction�� ���������� �����ϱ� ���� �����ߵ�

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

			// Ŀ���� �̵���Ű�� ���� ã�´�
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

	// insert ��ɸ޼���
	// ����� �����ߴٴ°��� �˷��� ���ϰ� �� executeUpdate�� intŸ���̿��� int�� ����
	public int insert(MemberDTO dto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		StringBuilder sql = new StringBuilder();
		sql.append("  insert  into   member(	memberno, userid, userpw, name, email, redate )");
		sql.append("  values (boardseq.nextval, ?,?,?,?,sysdate )                            ");

		int result = 0; // try�ȿ��� int result�� ����� ������ ������ ����

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

	// update ��ɸ޼���
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
	
	// delete ��ɸ޼���
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
	
	// select ��ɸ޼���
	public ArrayList<MemberDTO> list() {
		//db�����ؼ� �ڷḦ �޾ƾߵ�
		//�ڷᱸ�� list map set  => ArrayList : db���� �Ŀ� ArrayList�� ��Ƽ� ����
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

	// close ��� �޼���

	// pstmt conn close �ϴ� ���
	private void close(PreparedStatement pstmt, Connection conn) // pstmt, conn close �ϴ� ���
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
