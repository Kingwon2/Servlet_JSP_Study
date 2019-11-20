package pk;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// MemberDAO 는 DB연결을 위한 클래스입니다. 
// MemberBean은 DB데이터를 담는 클래스입니다.
// MemberServlet.java에서 가공한 DB정보를 ViewServlet.java에 가져와서 최종적으로 출력을 합니다.
// 체크박스를 통해 원하는 데이터를 선택해서 데이터를 한꺼번에 처리 할 수 있습니다.
// 자바스크립트 문은 여러 데이터를 한꺼번에 삭제와, 수정작업하기 위해 작성했습니다. 
@WebServlet("/viewMembers")
public class ViewServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
		                                            throws ServletException, IOException {
	      request.setCharacterEncoding("utf-8");		
	      response.setContentType("text/html;charset=utf-8");
	      PrintWriter out=response.getWriter();
		List membersList = (List) request.getAttribute("membersList");
		out.print("<head>\r\n" +  
				"		<script language=\"javascript\">\r\n" + 
				"		function search(){\r\n" + 
				"var keword = document.getElementById(\"keyword\");\r\n" +  
				"var searchkey = keyword.options[keyword.selectedIndex].value;"+
				"var target = document.getElementById(\"target\").value;\r\n" +
				"		window.location.href=\"member?command=search&key=\" + searchkey + \"&target=\" + encodeURI(target,\"UTF-8\");}"+ 
				"function check(box){\r\n" + 
				"		if(box.checked == true) { var index = box.value; var array_inputs = document.getElementsByName(index); for (var i = 1; i < array_inputs.length; i++) {\r\n" + 
				"				array_inputs[i].readOnly = false;}}\r\n" + 
				"		else {if(box.checked == false) { var index = box.value; var array_inputs = document.getElementsByName(index); for (var i = 0; i < array_inputs.length; i++) {\r\n" + 
				"				array_inputs[i].readOnly = true;}}}}"+
				"function edit(){\r\n" + 
				"		 \r\n" + 
				"		var chboxs=document.getElementsByName('chbox');\r\n" + 
				"		var bookid_arr = new Array();\r\n" + 
				"		var bookname_arr = new Array();\r\n" + 
				"		var publisher_arr = new Array();\r\n" + 
				"		var price_arr = new Array();\r\n" + 
				"		\r\n" + 
				"		for(var i=0; i<chboxs.length; i++){               \r\n" + 
				"		if(chboxs[i].checked == true){\r\n" + 
				"			var index = chboxs[i].value;\r\n" + 
				"			var array_inputs = document.getElementsByName(index);\r\n" + 
				"			bookid_arr.push(array_inputs[0].value);\r\n" + 
				"			bookname_arr.push(array_inputs[1].value);\r\n" + 
				"			publisher_arr.push(array_inputs[2].value);\r\n" + 
				"			price_arr.push(array_inputs[3].value);\r\n" + 
				"		}}\r\n" + 
				"		\r\n" + 
				"		var frm = document.getElementById(\"frm\");\r\n" + 
				"		document.getElementById(\"id_list\").value = bookid_arr;\r\n" + 
				"		document.getElementById(\"name_list\").value = bookname_arr;\r\n" + 
				"		document.getElementById(\"publisher_list\").value = publisher_arr;\r\n" + 
				"		document.getElementById(\"price_list\").value = price_arr;\r\n" + 
				"		document.getElementById(\"cmd\").value = \"update\";  \r\n" + 
				"		frm.method = \"get\";\r\n" + 
				"		frm.action = \"member\"\r\n" + 
				"		frm.submit();	\r\n" + 
				"		}"+
				"function del(){\r\n" + 
				"		 \r\n" + 
				"		var chboxs=document.getElementsByName('chbox');\r\n" + 
				"		var bookid_arr = new Array();\r\n" + 
				"		var bookname_arr = new Array();\r\n" + 
				"		var publisher_arr = new Array();\r\n" + 
				"		var price_arr = new Array();\r\n" + 
				"		\r\n" + 
				"		for(var i=0; i<chboxs.length; i++){               \r\n" + 
				"		if(chboxs[i].checked == true){\r\n" + 
				"			var index = chboxs[i].value;\r\n" + 
				"			var array_inputs = document.getElementsByName(index);\r\n" + 
				"			bookid_arr.push(array_inputs[0].value);\r\n" + 
				"			bookname_arr.push(array_inputs[1].value);\r\n" + 
				"			publisher_arr.push(array_inputs[2].value);\r\n" + 
				"			price_arr.push(array_inputs[3].value);\r\n" + 
				"		}}\r\n" + 
				"		\r\n" + 
				"		var frm = document.getElementById(\"frm\");\r\n" + 
				"		document.getElementById(\"id_list\").value = bookid_arr;\r\n" + 
				"		document.getElementById(\"name_list\").value = bookname_arr;\r\n" + 
				"		document.getElementById(\"publisher_list\").value = publisher_arr;\r\n" + 
				"		document.getElementById(\"price_list\").value = price_arr;\r\n" + 
				"		document.getElementById(\"cmd\").value = \"delete\";  \r\n" + 
				"		frm.method = \"get\";\r\n" + 
				"		frm.action = \"member\"\r\n" + 
				"		frm.submit();	\r\n" + 
				"		}"+
				"</script>");		
		out.print("<body>");
		out.print("<select id=\"keyword\">\r\n" + 
				"    <option value=\"bookname\">책 이름</option>\r\n" + 
				"    <option value=\"publisher\">출판사</option>\r\n" + 
				"</select>");
		out.print("<input type=\"text\" id=\"target\">");
		out.print("<input type=\"button\" name=\"search\" value=\"검색\"  onclick=\"search()\"> 전체검색시 @@@@ 입력하세요.");
		out.print("<table border=1><tr align='center' bgcolor='lightgreen'>");
		out.print("<td>책번호</td><td>이름</td><td>출판사</td><td>가격</td></tr>");
		for (int i = 0; i < membersList.size(); i++) {
			MemberVO memberVO = (MemberVO) membersList.get(i);
			int id = memberVO.getId();
			String name = memberVO.getName();
			String publisher = memberVO.getPublisher();
			int price = memberVO.getPrice();
			out.print("<tr><td><input type=\"text\" name=" + "\"" + i + "\" value=" + "\"" + id + "\""  + " readonly=\"true\"></td>" + "<td><input type=\"text\" name=" + "\"" + i + "\" value=" + "\"" + name + "\""  + " readonly=\"true\"></td>" + "<td><input type=\"text\" name=" + "\"" + i + "\" value=" + "\"" + publisher + "\""  + " readonly=\"true\"></td>" + "<td><input type=\"text\" name=" + "\"" + i + "\" value=" + "\"" + price + "\""  + " readonly=\"true\"></td>" + "<td><input type=\"checkbox\" name=\"chbox\" value=" + "\"" + i + "\" onClick=\"check(this)\"></td></tr>");
		}
		out.print("</table></body></html>");
		out.print("<br>" + 
				"		<input type=\"button\" value=\"수정\" onclick='edit()'>\r\n" + 
				"		<input type=\"button\" value=\"삭제\" onclick='del()'>\r\n" + 
				"		<form id=\"frm\" encType=\"UTF-8\">\r\n" + 
				"		<input type=\"hidden\" id=\"cmd\" name=\"command\" >\r\n" + 
				"		<input type=\"hidden\" id=\"id_list\" name=\"id_list\" >\r\n" + 
				"		<input type=\"hidden\" id=\"name_list\" name=\"name_list\" >\r\n" + 
				"		<input type=\"hidden\" id=\"publisher_list\" name=\"publisher_list\" >\r\n" + 
				"		<input type=\"hidden\" id=\"price_list\" name=\"price_list\" >\r\n" + 
				"		</form>");
		out.print("<a href='memberForm.html'>새 도서 등록하기</a");
	}
}
