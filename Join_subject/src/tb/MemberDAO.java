package tb;

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
			con = dataFactory.getConnection();
			String query = "select * from k_member order by name desc ";
			System.out.println("prepareStatememt: " + query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String sex = rs.getString("sex");
				String area = rs.getString("area");
				MemberBean vo = new MemberBean();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setSex(sex);
				vo.setArea(area);
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
		if(value.equals("@@@@")) {
			return this.listMembers();
		}
		else {
			try {
				// connDB();
				con = dataFactory.getConnection();
				String query = "select * from k_member " + "where " + key + " like " + "'%" +value + "%'" ;
				System.out.println("prepareStatememt: " + query);
				pstmt = con.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					String id = rs.getString("id");
					String pwd = rs.getString("pwd");
					String name = rs.getString("name");
					String sex = rs.getString("sex");
					String area = rs.getString("area");
					MemberBean bean = new MemberBean();
					bean.setId(id);
					bean.setPwd(pwd);
					bean.setName(name);
					bean.setSex(sex);
					bean.setArea(area);
					list.add(bean);
				}
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
	}
	

	public void addMember(MemberBean memberBean) {
		try {
			Connection con = dataFactory.getConnection();
			String query = "insert into k_member";
			query += " (id,pwd,name,sex,area)";
			query += " values(?,?,?,?,?)";
			System.out.println("prepareStatememt: " + query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberBean.getId());
			pstmt.setString(2, memberBean.getPwd());
			pstmt.setString(3, memberBean.getName());
			pstmt.setString(4, memberBean.getSex());
			pstmt.setString(5, memberBean.getArea());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delMember(MemberBean memberBean) {
		try {
			Connection con = dataFactory.getConnection();
			Statement stmt = con.createStatement();
			String query = "delete from k_member" + " where id=?";
			System.out.println("prepareStatememt:" + query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberBean.getId());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateMember(MemberBean memberBean) {
		try {
			Connection con = dataFactory.getConnection();
			Statement stmt = con.createStatement();
			String query = "update k_member set pwd=?, name=?, sex=?, area=? where id=?";
			System.out.println("prepareStatememt:" + query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(5, memberBean.getId());
			pstmt.setString(1, memberBean.getPwd());
			pstmt.setString(2, memberBean.getName());
			pstmt.setString(3, memberBean.getSex());
			pstmt.setString(4, memberBean.getArea());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
