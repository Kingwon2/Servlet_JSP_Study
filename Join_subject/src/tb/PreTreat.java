package tb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PreTreat
 */
@WebServlet("/PreTreat")
public class PreTreat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreTreat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				request.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=utf-8");
				List memberList = new ArrayList();
				MemberBean m = new MemberBean();
				
				String id_list = request.getParameter("id_list");
				String pwd_list = request.getParameter("pwd_list");
				String name_list = request.getParameter("name_list");
				
				String sex_list = request.getParameter("sex_list");
				String area_list = request.getParameter("area_list");
	
				String[] ids = id_list.split(",");
				String[] pwds= pwd_list.split(",");
				String[] names = name_list.split(",");
				String[] sexs = sex_list.split(",");
				String[] areas = area_list.split(",");
				
				
				
				for(int i=0; i<ids.length; i++) {
					m.setId(ids[i]);
					m.setPwd(pwds[i]);
					m.setName(names[i]);
					m.setSex(sexs[i]);
					m.setArea(areas[i]);		
					memberList.add(m);
					System.out.println("길이"+ids.length);
					
				}
				request.setAttribute("memberList", memberList);
				RequestDispatcher dispatch = request.getRequestDispatcher("EditTable.jsp");               //신규 등록하고 나면 add함수를 거친후 405번에러  HTTP 메소드인 POST는 이 URL에 의해 지원되지 않습니다.
				dispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		List memberList = new ArrayList();
		
		String id_list = request.getParameter("id_list");
		String pwd_list = request.getParameter("pwd_list");
		String name_list = request.getParameter("name_list");
		
		String sex_list = request.getParameter("sex_list");
		String area_list = request.getParameter("area_list");
		
		String[] ids = id_list.split(",");
		String[] pwds= pwd_list.split(",");
		String[] names = name_list.split(",");
		String[] sexs = sex_list.split(",");
		String[] areas = area_list.split(",");
		
		
		
		for(int i=0; i<ids.length; i++) {
			MemberBean m = new MemberBean();
			m.setId(ids[i]);
			m.setPwd(pwds[i]);
			m.setName(names[i]);
			m.setSex(sexs[i]);
			m.setArea(areas[i]);		
			memberList.add(m);
		}
	
		request.setAttribute("memberList", memberList);
		RequestDispatcher dispatch = request.getRequestDispatcher("EditTable.jsp");               //신규 등록하고 나면 add함수를 거친후 405번에러  HTTP 메소드인 POST는 이 URL에 의해 지원되지 않습니다.
		dispatch.forward(request, response);

	}

}