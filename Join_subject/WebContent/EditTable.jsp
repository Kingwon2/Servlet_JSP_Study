
<%@ page language="java"   contentType="text/html; charset=UTF-8"
     import="java.util.*,tb.*" 
    pageEncoding="UTF-8"
    isELIgnored="false"%>
    <%!
    static String key;
    static String value;
    List memberList = null;
    MemberBean bean = null;
    %>
    
    <%
    request.setCharacterEncoding("UTF-8");
    String command = request.getParameter("command");
    System.out.println(command);
    MemberDAO dao = new MemberDAO();
    %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>  </title>
</head>
<body>
	<%
		if(command==null){
			key=""; value="";
		}
		else if(command.equals("search")){
			key = request.getParameter("key");
			value = request.getParameter("target");
		}
		else if(command.equals("insert")){
			key=""; value="";
			System.out.println(request.getParameter("area"));
	%>
		<jsp:useBean id="bean" class="tb.MemberBean" />
    	<jsp:setProperty name="bean" property="*" />
    <%
    	System.out.println(bean.getArea());
    	dao.addMember(bean);
    	}
		else if(command.equals("delete")){
    		memberList = (ArrayList) request.getAttribute("memberList"); //??????????????
    		for(int i=0; i<memberList.size();i++){
    		MemberBean bean = (MemberBean) memberList.get(i); 	
    		dao.delMember(bean);
    		}
		}
		else if(command.equals("update")){
			memberList = (ArrayList) request.getAttribute("memberList"); //??????????????
	    	for(int i=0; i<memberList.size();i++){
	    		MemberBean bean = (MemberBean) memberList.get(i);
	    		System.out.println(bean.getId());
	    		System.out.println(bean.getPwd());
	    		System.out.println(bean.getName());
	    		System.out.println(bean.getSex());
	    		System.out.println(bean.getArea());
	    		dao.updateMember(bean);
			}}
		
	memberList = dao.searchMembers(key, value);
	
	
	request.setAttribute("viewMember", memberList);
	RequestDispatcher dispatch = request.getRequestDispatcher("ViewTable.jsp");               //신규 등록하고 나면 add함수를 거친후 405번에러  HTTP 메소드인 POST는 이 URL에 의해 지원되지 않습니다.
	dispatch.forward(request, response);	
	%>
	
	    
    
</body>
</html>