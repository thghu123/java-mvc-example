<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>

<!-- ������ %@�� ������ ������ �±׷� page �������� page ������, �� �������� ��ſ���
��� ������ִ� �ϳ��� ���� , �� �ڹ� ����̴�. ����� �ѱ�ó���� �ڵ����� �Ǿ��ִ�. -->
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

 <!-- html �ּ� -->
 <%-- jsp �ּ�, f11 �ҽ����⸦ �ϸ� jsp �ּ��� ������ �ʴ´�. 
 	�⺻ ����
 	��ũ��Ʈ ��� , ��ũ��Ʈ��/���/����
 	1) ��ũ��Ʈ�� : �ڹ��ڵ带 ���� �� �� ��� (���� ��������, ���, ��...)
 		<% String msg = "TEST";
 		if(msg.equals("ABC"))...;
 		%>�̷��� �� ��
 		
 	2) ��¹� : ������ out.print�� �������Ҷ�
 		<%= msg%> ---> out.println(msg);
 	
 	3) ���� : ��������� �����ϰų�, ��� �޼��带 ������ �� ���
 		<%! String msg;
 		private void test(){
 			System.out.println(msg);
 		}
 		%>
 	
 	
 --%>
 
 
 <%!
 	int cnt = 100;//������� ����
 %>
 
 <h1>script factor Practice</h1>
 
 	<article>
 	<table border = "1" style = "width:260px">
 		<colgroup>
 			<col width = "80px"/>
 			<col width = "180px"/>
 		</colgroup>
 		
 		<thead>
 			<tr>
 				<th>��ȣ</th>
 				<th>�׸�</th>
 			</tr>
 		
 		</thead>
 		<tbody>
 		<%
 		for(int i=0; i<cnt; i++){
 		%>
 		<!-- for�� �ȿ� �����ϴ� html ���� -->
 			<tr>
 				<td><%=i+1%></td>
 				<td>�׸�<%=100-i%></td>
 			</tr>
 		<%	
 		}
 		%>
 		</tbody>
 		
 	</table>
 	</article>
 
 
</body>
</html>