<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" 
href="css/common.css"/>

<link type="text/css" rel="stylesheet" 
href="css/bbs.css"/>

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
		</ul>	</div>
	<!-- 상단 영역 끝 -->
	<!-- 콘텐츠 영역 -->
	<div id="contents_sub">
		<h1 style="font-size: 30px; color : #000; margin : 30px;">SKT와 사회공헌</h1>
		<div class="bbs_area" id="bbs">
		<form method="post" name = "frm">
			<input type = "hidden" name = "cPage" value = "${param.cPage }"/>
		<table summary="게시판 글쓰기">
			<caption>게시판 글쓰기</caption>
			<tbody>
				<tr>
					<th>제목:</th>
					<td>${vo.subject}</td>
				</tr>

				<tr>
					<th>첨부파일:</th>
					
					<td>
					
					<c:if test='${vo.file_name ne null }'>
					<a href="javascript:fDown('${vo.file_name}')"> <!-- 이렇게 주고 다운로드 할 수 있게 연결해주면됐다 예전에 했었다. -->
						${vo.file_name}
					</a>
					</c:if>
					
					</td>
				</tr>
				
				<tr>
					<th>이름:</th>
					<td>${vo.writer }</td>
				</tr>
				<tr>
					<th>내용:</th>
					<td>${vo.content }</td>
				</tr>
				
				<tr>
					<td colspan="2"> <!-- onclick 시 이벤트 발생시키면 되겠다 -->
						<c:if test = "${vo.writer  eq mvo.m_name }">
						<input type="button" value="수정" onclick = "editBbs()"/>
						<input type="button" value="삭제" onclick = "delBbs()"/>
						</c:if>
						
						<input type="button" value="목록" onclick = "goBack()"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	<form method="post" action="ans_write.jsp">
		이름:<input type="text" name="writer"/><br/>
		내용:<textarea rows="4" cols="55" name="comm"></textarea><br/>
		비밀번호:<input type="password" name="pwd"/><br/>
		
		
		<input type="hidden" name="b_idx" value="${vo.b_idx }">
		<input type="hidden" name="index" value=""/>
		<input type="submit" value="저장하기"/> 
	</form>
	
	댓글들<hr/><!-- 있으면 반복문으로 출력해주자 -->
	
	<c:forEach var = "cvo" items = "${vo.c_list}">
	
		<div>
			이름:${cvo.writer } &nbsp;&nbsp;
			날짜:${cvo.write_date }<br/>
			내용:${cvo.content }
		</div>
	</c:forEach>
	<!-- 콘텐츠 영역 끝-->
	<!-- 하단영역 -->
	<div id="footer">
		<div class="footer_area">
			<ul class="foot_guide">
				<li><a href="">개인정보취급방침</a></li>
				<li><a href="">회원이용약관</a></li>
				<li><a href="">책임한계와 법적고지</a></li>
				<li><a href="">이메일 무단수집 거부</a></li>
			</ul>
			<address>
				서울특별시 무슨구 아무길 100번지
				대표이사: 마루치
				고객상담: 국번없이 004 혹은 02-1234-1234 (평일 09:30~17:00)
			</address>
			<p class="copyright">
				COPYRIGHT (c) 2020 SK Telecom. All rights reserved.
			</p>
		</div>
	</div>
	<!-- 하단영역 끝-->
</div>

	<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
	<script>
	
	function goBack(){//cpage에 안담았네, write컨트롤러에서 보낼때 그냥 같이 보내도된다.
		//포워드 형태로 보냈다. 리다이렉트로 보낸 게 아니기에 리퀘스트 인자 다 있다. 고로 아래처럼 하면된다.
		location.href = "bbs?cPage=${param.cPage}"
		//location.href = "bbs?cPage=${vo.cPage}"
	}

	
	function editBbs(){
		document.frm.action = "edit";
		document.frm.submit(); //이때 요청이 일어난다.
	}
	function fDown(fname){
		//filedownload라는 서블릿을 호출
		location.href = "FileDownload?dir=/resources/upload&filename="+encodeURIComponent(fname)
		//dir은 서블릿에서 직접구현해도되지만 유동성을 부여한 것 뿐 . 한글을 받을 수 있으니 encode선언ㅇ
		//일단 진행
		
		//get방식 한글처리를 하기 위해서는 톰캣의 server.xml에 
		//connector port = "8080" protocol = "http/1.1"이런 태그가 있다.
		//URIEncoding = "UTF-8"을 추가해야한다.
		
	}

	</script>





