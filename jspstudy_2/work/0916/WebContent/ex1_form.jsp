<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>�˻��� ��ȣ</h1>
	<article>
	
	
	
	
	
		<form action = "ex1_res.jsp" method = "post">
			<label for = "s_no">�����ȣ:</label>		
			<input id = "s_no" name = "s_no"/>
			
			<input type = "button" value = "�˻�" onclick = "exe(this.form)"/>
		</form>
	
	
	
	
	
	
	</article>
	
	<script>
		function exe(f){
			var s_no = f.s_no;
			if(s_no.value.trim().length<1){
				alert("�˻��� �̸��� �Է��ϼ���");
				s_no.value = "";
				s_no.focus();
				return;
				
				
			}
			f.submit();
			//�� ���ڶ� �־����� ��η� ������.
			
			
			
		}
		
		
	
	</script>
	
</body>
</html>