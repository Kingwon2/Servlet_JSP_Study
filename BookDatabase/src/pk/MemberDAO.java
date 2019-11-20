package pk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;

	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List listMembers() {
		List list = new ArrayList();
		try {
			// connDB();
			con = dataFactory.getConnection();
			String query = "select * from book ";
			System.out.println("prepareStatememt: " + query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("bookid");
				String name = rs.getString("bookname");
				String publisher = rs.getString("publisher");
				int price = rs.getInt("price");
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setName(name);
				vo.setPublisher(publisher);
				vo.setPrice(price);
				list.add(vo);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public List searchMembers(String key,String value) {
		List list = new ArrayList();
		try {
			// connDB();
			con = dataFactory.getConnection();
			String query = "select * from book " + "where " + key + " like " + "'%" +value + "%'" ;
			System.out.println("prepareStatememt: " + query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("bookid");
				String name = rs.getString("bookname");
				String publisher = rs.getString("publisher");
				int price = rs.getInt("price");
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setName(name);
				vo.setPublisher(publisher);
				vo.setPrice(price);
				list.add(vo);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void addMember(MemberVO memberVO) {
		try {
			Connection con = dataFactory.getConnection();
			int id = memberVO.getId();
			String name = memberVO.getName();
			String publisher = memberVO.getPublisher();
			int price = memberVO.getPrice();
			String query = "insert into book";
			query += " (bookid,bookname,publisher,price)";
			query += " values(?,?,?,?)";
			System.out.println("prepareStatememt: " + query);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, publisher);
			pstmt.setInt(4, price);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delMember(int id) {
		try {
			Connection con = dataFactory.getConnection();
			Statement stmt = con.createStatement();
			String query = "delete from book" + " where bookid=?";
			System.out.println("prepareStatememt:" + query);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
