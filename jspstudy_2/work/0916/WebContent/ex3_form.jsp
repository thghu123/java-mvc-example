<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<h1>��� ����</h1>

	<article>
	
		<form action="ex3_edit.jsp" method = "post">
		<label for = "s_no">�˻��� ���:</label>
		<input type = "text" id = "s_no" name = "s_no"><br/>
		
		<label for = "s_name">������ �̸�:</label>
		<input type = "text" id = "s_name" name = "s_name"><br/>
		
		<label for = "s_job">������ ����:</label>
		<input type = "text" id = "s_job" name = "s_job"><br/>
		
		<label for = "s_sal">������ �޿�:</label>
		<input type = "text" id = "s_sal" name = "s_sal"><br/>
		
		<input type = "button" value = "����" onclick = "exe(this.form)"/>
		
		
		
		</form>
	</article>

	<script type="text/javascript">
	
	function exe(f) {
		//����� ��ȿ�� �˻縦 ����
		var no = f.s_no;
		if(no.value.trim().length == 0){
			alert("����� �Է��ϼ���");
			no.value = "";
			no.focus();
			return;
		}
		
		f.submit();
		//jsp �ϳ��� ����� dao���� �ϳ��� �� �߰��ϸ� ��
	}
	
	</script>



</body>
</html>