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


	<h1>��� ���� ���</h1>
	
	<article>
	<table border = "1" style = "width:250px">
	
		<colgroup>
			<col width = "80px"/>
			<col width = "*"/>
			<col width = "100px"/>
		</colgroup>
		
		<thead>
			<th>���</th>
			<th>�̸�</th>
			<th>����</th>
		</thead>
		
		<tbody>
		<%
		request.setCharacterEncoding("utf-8"); //��û�� �ѱ�ó��
		//�����ؿ��� �Ķ���� �ޱ�
		String s_no = request.getParameter("s_no");
		String s_name = request.getParameter("s_name");
		String s_job = request.getParameter("s_job");
		String s_sal = request.getParameter("s_sal");
		//type = "text"���� ��Ҹ� �Է����� �ʾƵ� ""������ ���´�.
		//input type = "text" ������ value = ""�� ���´�.
		out.println(s_no+", "+s_name+", "+s_job+", "+s_sal);
		
		//���� �޾ƿ� �� Ȯ���غ���
		
		/* out.println(value+", "+type); �Ķ���� �� �Ѿ���� Ȯ��*/
		//out ���� ��ü 
		
		
		
	/* 	List<EmpVO> list = EmpDAO.search("0",s_no); */
		//���������� ó���� �Ǹ� �˻� ����� ������ list�� null�� ������ �����Ƿ� list.size()�� ���Ѵ�
		
		if(((EmpDAO.search("0",s_no)).size())>0){
			//list != null &&
					
			boolean ch = EmpDAO.updat(s_no, s_name, s_job, s_sal); //update�� �ϴ� ��Ʈ
			//������ �״�� ����.
			
			if(ch){
				out.println("�����Ϸ�");
			}else{
				out.println("��������");
			}
			
			//�ٽ� �ް� �� ����� ���ȭ������ ��Ÿ�� ��
			List<EmpVO> list = EmpDAO.search("0",s_no);
			
			for(EmpVO vo : list){
			%>
			<tr>
				<td><%=vo.getEmpno() %></td>
				<td><%=vo.getEname() %></td>
				<td><%=vo.getJob() %></td>
			</tr>
			<%
			}
		}else{
			%>
			<tr>
				<td colspan = "3">�׷��� ����� �����ϴ�.</td>
			</tr>
			<%
		}
		
		
		
		%>
		
		
		</tbody>		
				
	</table>
	
	</article>




</body>
</html>