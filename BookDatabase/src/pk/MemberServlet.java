package pk;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member")
public class MemberServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("member호출");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		MemberDAO dao = new MemberDAO();
		String command = request.getParameter("command");
		List membersList =null;
		if(command!= null && command.equals("search")) {
			String keyword = request.getParameter("key");
			String target = request.getParameter("target");
			target = java.net.URLDecoder.decode( target , "UTF-8" );
			System.out.println(keyword);
			System.out.println(target);
			if(target.equals("@@@@")) {
				membersList = dao.listMembers(); // %%%%로 검색시 테이블값 전체보기
			}
			else {
				membersList=dao.searchMembers(keyword,target); // 해당 키워드 값 검색하기 
			}
			
		}
		else if(command!= null && command.equals("addMember")){
			 String _name=request.getParameter("name");
			 String _publisher=request.getParameter("publisher");
			 String _price=request.getParameter("price");
			 String _id=request.getParameter("id");
			 int id = Integer.parseInt(_id);
			 int price = Integer.parseInt(_price);
			 MemberVO vo=new MemberVO();
			 vo.setName(_name);
			 vo.setPublisher(_publisher);
			 vo.setPrice(price);
			 vo.setId(id);
			 dao.addMember(vo);
		     }
		else if(command!= null && command.equals("delete")){
			 String id_list = request.getParameter("id_list");
			 String[] bookids = id_list.split(",");
			 for(int i=0; i<bookids.length;i++) {
				 int id = Integer.parseInt(bookids[i]);
				 dao.delMember(id);
			 }
			 		     }
		
		else if(command!= null && command.equals("update")) {
			String id_list = request.getParameter("id_list");
			String name_list = request.getParameter("name_list");
			String publisher_list = request.getParameter("publisher_list");
			String price_list = request.getParameter("price_list");
			String[] bookids = id_list.split(",");
			String[]  names= name_list.split(",");
			String[] publishers = publisher_list.split(",");
			String[] prices = price_list.split(",");
			for(int i=0; i<bookids.length; i++) {
				int id = Integer.parseInt(bookids[i]);
				int price = Integer.parseInt(prices[i]);
				dao.delMember(id);
				MemberVO vo=new MemberVO();
				vo.setName(names[i]);
				vo.setPublisher(publishers[i]);
				vo.setPrice(price);
				vo.setId(id);
				dao.addMember(vo);
			}
		}
		
		request.setAttribute("membersList", membersList);
		RequestDispatcher dispatch = request.getRequestDispatcher("viewMembers");               //신규 등록하고 나면 add함수를 거친후 405번에러  HTTP 메소드인 POST는 이 URL에 의해 지원되지 않습니다.
		dispatch.forward(request, response);
		
		
		}
	}
