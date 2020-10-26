package com.project.ex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mybatis.dao.MemDAO;

import mybatis.vo.MemberVO;

@Controller
public class IndexController {

	@Autowired
	private MemDAO m_dao;
	
	@RequestMapping("/")
	public String index() {
		
		return "index"; //views/index.jsp를 의미
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	

	@RequestMapping(value = "/login", method=RequestMethod.POST)//포스트 방식으로 했을 경우에만 여기 들어온다
	public ModelAndView login(MemberVO vo) { //인자를 넣으면 중복되도된다. 오버라이딩
		//이름 명칭 중복이 가능하다.
		ModelAndView mv = new ModelAndView();
		//많이 쓰는 반환 타입 modelandview가 request에 저장되는 애여서 받을 필요없다
		
		//System.out.print(vo.getM_id()+" "+vo.getM_pw());
		
		MemberVO mvo = m_dao.getInfo(vo.getM_id(), vo.getM_pw());
		
		if(mvo != null) {
			//mv.addObject("uvo",mvo);
			//System.out.println(mvo);
			mv.addObject("login_chk",true);
			mv.setViewName("index");
			
			
			return mv;
			
		}
		//System.out.println(mvo);
		mv.addObject("login_chk",false);
		/* mv.addObject("uvo",null); */
		mv.setViewName("index");
		//System.out.println(mvo);
		
		return mv;
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout() { 
		ModelAndView mv = new ModelAndView();
		mv.addObject("login_chk",false);
		mv.setViewName("index");
		return mv;
	}
	
}