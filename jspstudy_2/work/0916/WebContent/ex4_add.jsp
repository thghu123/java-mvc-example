<%@page import="am.dao.EmpDAO"%>
<%@page import="am.vo.EmpVO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<%
	//��û�� �ѱ�ó��
	request.setCharacterEncoding("utf-8");
	
	String s_no = request.getParameter("s_no");
	String s_sal = request.getParameter("s_sal");
	String s_name = request.getParameter("s_name");
	String s_job = request.getParameter("s_job");
	
	//�Ѿ�� �Ķ���͵��� ������ VO��ü�� �����Ѵ�
	
	EmpVO vo = new EmpVO();
	//�� �Ķ���� ������ ������ �����ϰ� ���̰� 0���� ū ��쿡�� �����մϴ�.
	
	if(s_no.trim().length()>0)
		vo.setEmpno(s_no);
	
	if(s_name.trim().length()>0)
		vo.setEname(s_name);
	
	if(s_job.trim().length()>0)
		vo.setJob(s_job);
		
	if(s_sal.trim().length()>0)
		vo.setSal(s_sal);
	
	//��ȿ�� �˻� DAO���� �� �۾��� jsp���� �Ѱ�
	
	boolean ch = EmpDAO.add(vo);
	
//	boolean ch = EmpDAO.addr(vo); //�ϴ� �����ΰ� ���� Ŭ���ϸ� ������ٱ��Ѵ�.
	
	if(ch){
		out.println("���� �Ϸ�");
	}else
		out.println("���� ����");
		
	

	
	
%>


</body>
</html>