<%@ page language="java"   contentType="text/html; charset=UTF-8"
     import="java.util.*,tb.*" 
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:useBean id="m" class="tb.MemberBean"/>

<script language="javascript">
				function search(){
				var keyword = document.getElementById("keyword"); 
				var searchkey = keyword.options[keyword.selectedIndex].value;
				var target = document.getElementById("target").value;
				window.location.href="EditTable.jsp?command=search&key=" + searchkey+"&target="+ encodeURI(target,'UTF-8');
				}
		
				function check(box){
						var index = box.value; var array_inputs = document.getElementsByName(index);
						if(box.checked == true) {  
													for (var i = 1; i < array_inputs.length; i++) {
														array_inputs[i].readOnly = false;
														}
												}	 
						else{  
							for (var i = 1; i < array_inputs.length; i++) {
								array_inputs[i].readOnly = true;}}}

 
				function format(){
						var chboxs=document.getElementsByName('chbox');
						var id_arr = new Array();
						var pwd_arr = new Array();
						var name_arr = new Array(); 
						var sex_arr = new Array();
						var area_arr = new Array();
						for(var i=0; i<chboxs.length; i++){
						if(chboxs[i].checked == true){ 
							var index = chboxs[i].value; 
							var array_inputs = document.getElementsByName(index);
							id_arr.push(array_inputs[0].value);
							pwd_arr.push(array_inputs[1].value);
							name_arr.push(array_inputs[2].value);
							sex_arr.push(array_inputs[3].value);
							area_arr.push(array_inputs[4].value);}
						} 
						var frm = document.getElementById("frm");
						document.getElementById("id_list").value = id_arr;
						document.getElementById("pwd_list").value = pwd_arr;
						document.getElementById("name_list").value = name_arr;
						document.getElementById("sex_list").value = sex_arr;
						document.getElementById("area_list").value = area_arr;	
						}
				
				function del(){
					format();
					document.getElementById("cmd").value = "delete";  
					frm.method = "post";
					frm.action = "PreTreat";
					frm.submit();
				}
				
				function update(){
					format();
					document.getElementById("cmd").value = "update";  
					frm.method = "post";
					frm.action = "PreTreat";
					frm.submit();
				}
				
		
</script>		
</head>
<body>

		<%
		List memberList= (List) request.getAttribute("viewMember");
		%>
		<select id="keyword">
				<option value="id">아이디</option>
		</select>
		<input type="text" id="target">
		<input type="button" name="search" value="검색"  onclick="search()"> 전체검색시 @@@@
		<table border=1><tr align='center' bgcolor='lightgreen'>
		<tr><td> 아이디 </td><td> 비밀번호 </td><td> 이름 </td><td> 성별 </td><td> 지역 </td></tr>
		<%
		for (int i = 0; i < memberList.size(); i++) {
			MemberBean bean = (MemberBean) memberList.get(i);
		%>
		<tr><td><input type="text" name=<%=""+i %> value=<%=bean.getId() %> readonly="true"></td>
			<td><input type="text" name=<%=""+i %> value=<%=bean.getPwd() %> readonly="true"></td>
			<td><input type="text" name=<%=""+i %> value=<%=bean.getName() %> readonly="true"></td>
			<td><input type="text" name=<%=""+i %> value=<%=bean.getSex() %> readonly="true"></td>
			<td><input type="text" name=<%=""+i %> value=<%=bean.getArea() %> readonly="true"></td>
			<td><input type="checkbox" name="chbox" value=<%=""+i %> onClick="check(this)"></td>
		</tr>
		<%
		}
		%>
		</table>
		<br>
				<input type="button" value="수정" onclick='update()'>
				<input type="button" value="삭제" onclick='del()'>
				<form id="frm" encType="UTF-8">
				<input type="hidden" id="cmd" name="command" >
				<input type="hidden" id="id_list" name="id_list" >
				<input type="hidden" id="pwd_list" name="pwd_list" >
				<input type="hidden" id="name_list" name="name_list" >
				<input type="hidden" id="sex_list" name="sex_list" >
				<input type="hidden" id="area_list" name="area_list" >
				</form>
		<a href='join.html'>새 회원 등록하기</a>

</body>
</html>