<%@page import="am.dao.EmpDAO"%>
<%@page import="am.vo.EmpVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<h1>����̸� �˻� ���</h1>
<article>
		<table border = "1" style = "width : 250px">
			<colgroup>
				<col width = "80px"/>
				<col width = "*"/>
			</colgroup>
			
			<thead>
			
				<th>���</th>
				<th>�̸�</th>
				<th>����</th>
				
			</thead>	
			<tbody>
				<%
				
				//��û�� �ѱ�ó��
				/* request.setCharacterEncoding("utfr-8"); */
				String no = request.getParameter("s_no");
				
				
				
				//empDAO�� ���Ͽ� ǥ���� ������ list<EmpVO>�� �޾Ƴ���.
				List<EmpVO> list = EmpDAO.getSearchNo(no);//static�̴ϱ� ������ �ʿ�� ����
				
				if(list != null && list.size() != 0)
					//null�� �ȿ��� ������ �ü��� �ִ�. �� EmpVO vo �� null�� �´�.
					//��� list�϶��� ������� ���� ��
				 {
					 for(EmpVO vo : list){
				%>
						<tr>
					
							<td><%=vo.getEmpno() %></td>
							<td><%=vo.getEname() %></td>
							<td><%=vo.getJob() %></td>
						</tr>
						 
				<%
					 }//for��
					 
					 
				 }else{
					 
					 //if��
				 
				 %>
				 			<tr>
									<td colspan = "3">�׷� ��� �����ϴ�.</td>	
							</tr>
				<%
				} 
				%> 


			</tbody>	
			
			
		</table>
	</article>


</body>
</html>