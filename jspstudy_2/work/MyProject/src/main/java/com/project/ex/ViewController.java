package com.project.ex;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import mybatis.dao.BbsDAO;
import mybatis.vo.BbsVO;
import mybatis.vo.MemberVO;
import spring.util.FileUploadUtil;

@Controller
public class ViewController {

	@Autowired
	private BbsDAO b_dao;
	
	//글을 보고난 뒤에 글 보기 화면에서 f5를 누르면 계속 reload가 되고
	//그때 hit수를 증가하게 되면 무한 증가가 가능 , 막자 => 세션에다가 저장하는 것
	//한 사용자가 게시물을 얼마나 볼지모르니 어레이리스트를 저장한다 bbsvo로
	
	//세션은 누구나 하나씩 있다
	@Autowired
	private HttpSession session;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ServletContext application;
	
	
	private String uploadPath = "/resources/upload";
	@RequestMapping("view")
	public ModelAndView view(String cPage,String b_idx) {
		
		//세션에서 한번 봤던 게시물들을 저장해두는 list<bbsvo>를 저장했다고 가정하자
		//그것을 view_list라는 이름으로 가져온다.
		Object obj = session.getAttribute("view_list");
		List<BbsVO> v_list = null;
		if(obj == null) {
			//저장한 적이 없는 경우는 새롭게 만들어서 저장한다
			v_list = new ArrayList<BbsVO>();
			
			
			//세션에 없는 상황이었으니 세션에 v_list를 저장한다
			session.setAttribute("view_list", v_list);
			
		}else {
			v_list = (List<BbsVO>) obj;
		}
		
		//v_list에 저장된 애들은 bbsvo이다. 이안에 b_idx있을 것이다.
		//인자로 넘어온 b_idx와 같은 것이 있다면 이미 봤던 게시물이다. 해당 session이 본 적이있다.
		//그러면 hit를 증가 시키지 않는다. 
		//bbsvo가 든 list가 긴 내용이기에 for문을 돌리다가 이미있으면 break를 돌린다
		
		boolean chk = false; //유무 확인
		for(BbsVO bvo : v_list) {//죄다 뽑을 것이다.
			if(bvo.getB_idx().equals(b_idx)){//스트링형이기 때문이다
				//같은 것이 있는 , 한번봤던 게시물
				chk = true;
				break;
			}
		}
		
		

		
		//이제 결국은 v_list를 세션에 저장한다 line42
		
		ModelAndView mv = new ModelAndView();
		//사용자가 선택한 게시물을 검색
		BbsVO vo = b_dao.getBbs(b_idx);
		vo.setcPage(cPage);
		
		if(!chk) {
			b_dao.updateHit(b_idx);
			vo.setHit(String.valueOf(Integer.parseInt(vo.getHit())+1)); //왼편이 String이라 31이되버린다.
			//이렇게 연산을 해야하면 정수형태로 선언하는 게 더 좋다
			//연산이 필요한것 까지 스트링을 쓰는 건 별로다.
			//하지만 String쓰면 몇자리수인지 알수있어 좋다.
			
			//심지어는 sethit에 string을 줘야하니까 또 변환 => 좋은 일이 아니다.
			
			v_list.add(vo); //전혀 없었던 내용이니까 vo를 저장해서 b_idx를 저장해주자.
		}
		
		mv.addObject("vo",vo);	
		session.setAttribute("bvo",vo); //수정을 선택했을 때 edit.jsp에서 기존 값을 표현하기 위함이다
		
		mv.setViewName("bbs/view");
		return mv;
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(BbsVO vo) throws Exception {//인자로 안받아서 cpage 안받았지만 파라미터 값으로는 url에 유지됨
		ModelAndView mv = new ModelAndView();
		
		BbsVO tmpvo = (BbsVO) session.getAttribute("bvo");
		vo.setB_idx(tmpvo.getB_idx());
		
		String c_type = request.getContentType();
		
		if(c_type !=null && c_type.startsWith("multipart")) {
			//get방식도 아니고 멀티파트도 request에 왔으면 실제 DB수정 작업
			mv.setViewName("redirect:/view?b_idx="+vo.getB_idx()+"&cPage="+vo.getcPage()); 
			//수정이 끝났으니까 view로 가자 갈때 줬던 인자가 2개이다.
			//getcPage로 하고 싶다 -> 만들어야한다
			
			
			
			
			//===처리
			MultipartFile mf = vo.getFile(); //그냥 통으로 뽑아 얻어낸다.
			//정상적인 접근에서 파일이 첨부되었다면 mf 가 null은 아니지만 용량이 0일 것이다.
			//그래서 파일 첨부에 대한 확인은 size로 비교한다. 혹시 모르니 널도 비교해준다.
			
			//비교, 파일 첨부를 했다면 이름도 있을 것이다. mf.getOriginalFilename().trim().length()>0 잘 안쓴다. getsize만 쓴다
			if(mf != null && mf.getSize()>0 && mf.getOriginalFilename().trim().length()>0){//아는 데 혹시나 nullpointexcoption뜰 수 있으니 null비교
				
				
				//업로드에 저장하기로 했으니 변수 선언해주고 , 이를 절대경로로 만들어준다. 그러려면 application도 필요하다
								
				//이러면 첨부파일이 있는 경우 -- 저장될 위치를 절대경로로 만든다.
				String path = application.getRealPath(uploadPath);
				//application get realpath가 필요한데 그것의 자료형이 서블릿 컨텍스트라고 말했다. 이를 오토와이어드로 선언
				//안에 업로드라는 폴더의 패스를 얻어올수 있다.
				
				//첨부 파일명얻기
				String f_name = mf.getOriginalFilename(); //첨부파일과 같은 이름 처리 -> 파일명 변경
				//동일 이름일때의 처리가 없다. cos는 있었지만, 디폴드파일 리네임폴리시의 수동코드를 내일 작성
			
				//동일한 파일명이 있다면 변경해야하한다 (내일 진행) 
				//처리 함수를 만들었다.
				f_name = FileUploadUtil.checkSameFileName(f_name, path);
				//뭐가 넘어왔다는 건은 바뀌었다는 의미 , 위 이름으로 파일을 전달할 계획이다.

				
				
				//업로드!!!!! - 예외처리를 해야한다. 눌어서 addthrow - exception 파일은 클라이언트거를 서버로 올리기에 외부파일을 저장하기에 stream을 쓰고 이를 쓸 때 예외처리 필요하다
				mf.transferTo(new File(path, f_name));//패런트에 패스 차일드에 파일을 넣자, 자동완성 인자에있다
				//자바는 파일을 처리할 때 io를 쓴다 고로 오류처리를 해야하는 빨간줄 눌러서 add throw로 처리하자 2개를 익센션으로
				// 하나로 처리하자 
				
				
				//db 작업을 위해 파일명을 vo 객체 저장한다
				vo.setFile_name(f_name);	
				
				//====
			}
			
			vo.setIp(request.getRemoteAddr());//ip 얻어오기 왜?????vo에 넣어줘서 쓰도록

			//이제 저정하자.
			MemberVO mvo = (MemberVO) session.getAttribute("mvo");
			vo.setWriter(mvo.getM_name());
			
			b_dao.update(vo);
			
			
			
			
		}else {
			//실제 수정 화면을 보여주시오
			mv.setViewName("bbs/edit");
		}
		
		
		
		
		
		return mv;
		
	}
	
	
	
	
	
	
	
}
