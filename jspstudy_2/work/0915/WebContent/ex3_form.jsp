<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	
	<h1>�˻��� �̸�</h1>
	<article>
	
	
		<form action = "ex3_searchName.jsp" method = "post">
			<label for = "s_name">�̸�:</label>		
			<input id = "s_name" name = "s_name"/>
			
			<input type = "button" value = "�˻�" onclick = "exe(this.form)"/>
		</form>
	
	
	</article>
	
	<script>
		function exe(f){
			var name = f.s_name;
			if(name.value.trim().length<1){
				alert("�˻��� �̸��� �Է��ϼ���");
				name.value = "";
				name.focus();
				return;
				
				
			}
			f.submit();
			//�� ���ڶ� �־����� ��η� ������.
			
			
			
		}
		
		
	
	</script>
	

</body>
</html>