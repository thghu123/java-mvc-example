<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" 
href="css/common.css"/>
<link type="text/css" rel="stylesheet" 
href="css/login2.css"/>


</head>
<body>
<div id="wrap">
	<!-- 상단 영역 -->
	<div id="header">
		<h1>SK Together</h1>
		<ul class="gn">
			<li><a href=""><span class="menu m1">기브유</span></a></li>
			<li><a href=""><span class="menu m2">위드유</span></a></li>
			<li><a href=""><span class="menu m3">스마트 전통시장</span></a></li>
			<li><a href=""><span class="menu m4">BRAVO!</span></a></li>
			<li><a href=""><span class="menu m5">SKT와 사회공헌</span></a></li>
		</ul>
	</div>
	<!-- 상단 영역 끝 -->
	<!-- 콘텐츠 영역 -->
	<div id="contents_sub">
		<h1 class="sub_title tit02">회원 로그인</h1>
		<div class="login_area">
		
		
		<!-- 일반개인회원 -->
			<div class="person_login">
				<h2 class="sub_title title01">일반 개인회원</h2>
				
				<div class="login">
				
				  <form action="login" method="post">
				  	<input type = "hidden" name = "type" value = "login"/>
					<div class="input_area">
						<p>
						 <label for="s_id">아이디</label>
						 <input type="text" name="m_id" id="s_id"/>
						</p>
						
						<p>
						 <label for="s_pw">비밀번호</label>
						 <input type="password" name="m_pw" id="s_pw"/>
						</p>
					</div>
					
					<div class="btnArea_right">
						<span class="btn b_login">
						<a id = "login_btn">로그인</a>
							<!--  <a href="login_ok">로그인</a> -->
						</span>
					</div>
					<div class="fclear"></div>
					<p class="login_search">
						<input type="checkbox" name="chk" 
						 id="ch01"/><label for="ch01">
						 아이디저장</label>
						<span class="btn b_search">
						  <a href="">아이디/비밀번호찾기</a>
						</span>
					</p>
				  </form>						
				</div>
				
				
			</div>
		<!-- 일반개인회원 끝-->
		
		
		<!-- 기관단체회원 -->
			<div class="group_login">
				<h2 class="sub_title title02">일반 개인회원</h2>
				<div class="login">
				  <form action="" method="post">
					<div class="input_area">
						<p>
						 <label for="s_id">아이디</label>
						 <input type="text" name="id" id="s_id2"/>
						</p>
						<p>
						 <label for="s_pw">비밀번호</label>
						 <input type="password" name="pw" id="s_pw2"/>
						</p>
					</div>
					<div class="btnArea_right">
						<span class="btn b_login">
						 <a >로그인</a><!-- id = "login_btn" -->
						</span>
					</div>
					<div class="fclear"></div>
					<p class="login_search">
						<input type="checkbox" name="chk" 
						 id="ch01"/><label for="ch01">
						 아이디저장</label>
						<span class="btn b_search">
						  <a href="">아이디/비밀번호찾기</a>
						</span>
					</p>
				  </form>						
				</div>
			</div>
		<!-- 기관단체회원 끝-->
		</div>
	</div>
	<!-- 콘텐츠 영역 끝-->
	<!-- 하단 영역 -->
	<div id="footer">
		<div class="footer_area">
			<ul class="foot_guide">
				<li><a href="">개인정보취급방침</a></li>
				<li><a href="">웹회원 이용약관</a></li>
				<li><a href="">책임한계와 법적고지</a></li>
				<li><a href="">이메일 무단수집 거부</a></li>
			</ul>
			<address>
			 서울시 중구 을지로 몇가 번지
			 대표이사: 마루치 
			 고객상담: 국번없이 114 혹은 
			 02-1234-1234 (평일 09:00~17:00)
			</address> 
			<p class="copyright">
				Copyright (c) 2012 SK Teleccom.
				All rights reserved.
			</p>
		</div>
	</div>
	<!-- 하단 영역 끝 -->
</div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>


<script>

	$(function(){
		//아이디가 login_btn인 요소에 클릭 이벤트 등록을 진행한다.
		$("#login_btn").bind("click", function(){
			//현재 문서에서 아이디가 s_id와 s_pw인 요소의 값을 얻어냅니다.
			var id = $("#s_id").val();
			var pw = $("#s_pw").val();
			//이러면 받아온 것
			
			if(id.trim().length < 1){//한자도 입력안한 경우, 공백만 입력한 경우
				alert("아이디를 입력하세요");
				$("#s_id").val(""); //val안에 무언가 있으면 값을 넣어라, 아니면 가지고 와라의 의미
				$("#s_id").focus();
				return;
			}
			if(pw.trim().length < 1){//한자도 입력안한 경우, 공백만 입력한 경우
				alert("아이디를 입력하세요");
				$("#s_pw").val(""); //val안에 무언가 있으면 값을 넣어라, 아니면 가지고 와라의 의미
				$("#s_pw").focus();
				return;
			}
			
			//이제는 둘다 입력을 했다는 의미이다.
			
			//console.log(id+"/"+pw);
			
			//map구조가 비동기식 통신에 좋다.
			//이때 비동기 식 통신을 수행하면된다
			
			//reload 가 일어나서 찍히는 데 껌벅이고 안나올 것이다. a 태그에서 "" 두면 현페이지 재업로드하니까 그냥 href 없엔다.
			$.ajax({
				url :"login",
				type : "post",
				data: "m_id="+encodeURIComponent(id)+"&m_pw="+encodeURIComponent(pw),//파라미터를 이렇게 보내도 된다 ,post 방식일 때는 이를 씌우라고 권장한다.
				dataType : "json"
			}).done(function(data){//data가 정당적으로 들어왔다. 400번대는 경로오류, 500대는 nullpoint 프로그래머 신텍스 오류
				//400번대가 경로를 다 파악해야하니 어렵다. 200번대는 정상적으로 수행됐을 떄, 서버오류나도 통신이 정상적으로 됐으면 200대, 이를 전부 done으로 한다
				//alert(data.res);//정상 출력된다.
			//결과를 보내줘서 반대편에서 컨트롤러 함수가 호출되어 map으로 잘 넘어왔다면 0,1 중 하나가 들어와 알람으로 뜰 것이다. 성공하면 1, 실패하면 0이 나올 것이다.
				if(data.res == "1"){
					alert(data.mvo.m_name+"님 환영합니다. 처음 화면으로 이동합니다");
					location.href="/"
				} //이름을 찍으려했는 데 이름을 안보냈으니 컨트롤러가서 mvo를 주자
				else{
					alert("아이디 또는 비밀번호가 다릅니다. 확인 후 다시 시도하세요")
				}
				
			
			}); 
			
			
			
			
			
			
			
		});
	});
	
	
	function exe(){
		/* 1번방법 var id = document.forms[0].id.value; */
		
		var id = document.getElementById("s_id").value; //2번째 방법, 이게 가장 확실한 방법
		/* 3번 javascript:exe(this.form) - 안통한다 input type은 폼의 요소지만
		a태그는 어디에든 있을 수 있기에 this.form을 form 태그가 이해하지 못한다. 그래서 안된다*/
		//3번째 방법 제이쿼리 스크립트 받아오고" id".
		var pw = document.getElementById("s_pw").value; //위에 제이쿼리 값 쓰니 주석이여도 에러가 나온다.
		
		if(id.length < 2){ //최소 4자 이상이여야한다
			alert("아이디를 입력하세요");
			/* id.focus(); */
			return;
		}
		if(pw.length < 2){ //최소 4자 이상이여야한다
			alert("비밀번호를 입력하세요");
			/* pw.focus(); */
			return;
		}
		document.forms[0].submit(); //던져지게된다.
	} 

</script>

</body>
</html>