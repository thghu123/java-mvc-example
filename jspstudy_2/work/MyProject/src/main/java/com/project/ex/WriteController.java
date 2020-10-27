package com.project.ex;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import mybatis.dao.BbsDAO;
import mybatis.vo.BbsVO;
import mybatis.vo.MemberVO;

@Controller
public class WriteController {

		@Autowired
		private BbsDAO b_dao;
		
		//인자로 받을 수 있지만 그냥 오토와이어드 변수 선언을 하자
		@Autowired
		private HttpSession session;
		
		@Autowired
		private ServletContext application;
		
		@Autowired
		private HttpServletRequest request;
		
		//첨부파일이 저장될 위치
		private String uploadPath = "/resources/upload";
		//얘는 resources에 저장해두어야한다. webapp안의 resources인데 여기가 webapp이니까 /
		//없는 upload 폴더 만들어주자
		
		@RequestMapping("/write")
		@ResponseBody //잭슨 라이브러리도 반드시 씌워야한다. 만약 됐으면 servelt.xml에 잭슨이 들어가있는 지 확인한다. 안그러면 porm.xml에 추가
		public Map<String, String> write() {
			
			Map<String, String> map = new HashMap<String, String>();
					
			//로그인이 되었다면 글쓰기 화면으로 이동합니다.
			
			//그렇지 않다면 로그인이 필요하다는 신호를 줍니다. 그러면 여기서 세션이 필요하다
			//인자로 받을 수 있지만 그냥 오토와이어드 변수 선언을 하자, 심지어는 request도 선언해서 쓸 수 있다.
			
			MemberVO mvo = (MemberVO) session.getAttribute("mvo");//object타입이라 캐스팅해준다
			
			if(mvo != null) {
				map.put("chk", "1");
				map.put("url", "writeForm");
				
				
			}else {
				map.put("chk", "0");
			}
			
			
			
			return map;//비동기식 통신이니까
		}
		
	@RequestMapping("/writeForm")
	public String writeForm() {
		//누군가 위 주소를 알면 그냥 올수도있다. 그래서 로그인 체크를 해야한다.
		//번거로우니까 하나의 객체로 뽑아서 인터셉터 기능을 써도된다 -> 내일 할예정
		MemberVO mvo = (MemberVO) session.getAttribute("mvo");
		String viewPath = null;
		if(mvo != null) 
		viewPath = "bbs/write";
		else
		viewPath = "/login"	; //몰래 들어왔으면 로그인으로 보내버리자.
		
		return viewPath;
		
	}
	@RequestMapping("/write_ok")//post 방식으로 넘어오지만 다른데서 안부르면 문제없으니 그냥하자
	//원래는 value 랑 method 지정해줘야한ㄷ
	public ModelAndView writeOK(BbsVO vo) throws Exception{
		
		//subject는 이름이 상이하니까 데이터 안들어오니 jsp 내용을 고치는 게 좋다.
		ModelAndView mv = new ModelAndView();
		/*
		 * System.out.println(vo.getSubject()); System.out.println(vo.getContent());
		 * System.out.println(vo.getBname());
		 * System.out.println(vo.getFile().getName());
		 성공*/
		
		//이거는 getname은 안된다 이거는 뽑아내야 name도 쓸 수 있다.
		//아직 첨부는 안했다.
		
		//멤버 변수를 하나 지정하겠다. 맨위에 파일이 업로드될 곳을 하나 두겠다.
		
		//첨부된 파일이 있는 지 없는지 확인한다 이때 필요한게
		MultipartFile mf = vo.getFile(); //그냥 통으로 뽑아 얻어낸다.
		
		if(mf != null && mf.getSize()>0){//아는 데 혹시나 nullpointexcoption뜰 수 있으니 null비교
			 //이러면 첨부파일이 있는 경우 -- 저장될 위치를 절대경로로 만든다.
			String path = application.getRealPath(uploadPath);
//application get realpath가 필요한데 그것의 자료형이 서블릿 컨텍스트라고 말했다. 이를 오토와이어드로 선언
			//안에 업로드라는 폴더의 패스를 얻어올수 있다.
			
			//파일명얻기
			String f_name = mf.getOriginalFilename();
			//동일이름일때의 처리가 없다. cos는 있었지만, 디폴드파일 리네임폴리시의 수동코드를 내일 작성
			
			//동일한 파일명이 있다면 변경해야하한다 (내일 진행)
			
			//업로드를 여기서 진행한다.
			
			mf.transferTo(new File(path, f_name));//패런트에 패스 차일드에 파일을 넣자, 자동완성 인자에있다
			//자바는 파일을 처리할 때 io를 쓴다 고로 오류처리를 해야하는 빨간줄 눌러서 add throw로 처리하자 2개를 익센션으로
			// 하나로 처리하자 
			
			//db 작업을 위해 파일명을 vo 객체 저장한다
			vo.setFile_name(f_name);	
		}
		
		vo.setIp(request.getRemoteAddr());//ip 얻어오기
		
		b_dao.add(vo);
		//자동완성을 하면 많은 인자들이 있는 데 이를 그냥 vo로 넘기고 싶다.
		//여기서 ctrl클릭하면 add로 가는 데 아래에 그대로 내용을 복붙하고 bbsvo를 인자를 받도록
		//오버라이드한다.
		
		MemberVO mvo = (MemberVO) session.getAttribute("mvo");
		vo.setWriter(mvo.getM_name());
		
		mv.setViewName("redirect:/bbs");
		//하지만 지금 넣어둔 정보가 없다. 바로 list로 가면 안된다 거기로 갱신정보가 안가기 때문이다.
		//bbs를 호출해야 처음부터와서 다 넣어서 list로 가니까
		//쓰레기 데이터 없이 갈 수 있다. 고로 리다이렉트로 가야만하는 데 이를 제공해준다
		
	
		return mv;
		
	}
	
	
	
}
