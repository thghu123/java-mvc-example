package com.project.ex;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import bbs.util.Paging;
import mybatis.dao.MemDAO;
import mybatis.vo.MemberVO;

@Controller
public class MemberController {
	@Autowired
	private MemDAO m_dao;
	//오토 와이어드 하려면 이미 만들어져있어야한다.
	
	@Autowired
	private HttpSession session; //자동으로 저장이 된다.
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(String m_id, String m_pw){
		//인자 두개를 로그인 함수를 호출하면서 전달하면 정확한 정보일 때 memvo 한개를 받게 된다
		//인젝션으로 dao를 넣을 수 있지만 오토 와이어드가 가장 편하다
		
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		MemberVO mvo = m_dao.getInfo(m_id, m_pw);
		//mvo가 널이면 없는 회원정보 값 널이 아니면 값을 제대로 가져온 것을 의미한다.
		if(mvo != null) {
			//세션에 mvo라는 이름으로 mvo를 저장한다. 
			//세션 얻는 없 1)인자에서 세션을 얻어와도 되고, 2)가장 좋은 건 오토와이어드로 하면 들어온다.
			//왜냐면 불려지기전에 이미 만들어져있는 것이기 때문이다.
			
			//세션이 이미 드렁와있으니까 그냥 써도된다
			session.setAttribute("mvo", mvo);	
			map.put("res", "1");
			map.put("mvo", mvo);//이렇게 하고 싶으면 위에를 object로 바꿔서 보낸다. 이제 건너가면 오브젝트이기에 부담이 될 것 같지만 자바 스크립트라 변환 필요없다.
			}else {
			map.put("res","0");
		
		}
		return map;
		//이를 제이슨 형태로 보내고 싶다면 위에 달러ResponseBody를 표시해준다.
		
	}
	/*
	@RequestMapping("/logout")
	public ModelAndView logout() { 
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("mvo",null);
		mv.addObject("res","0");
		mv.setViewName("index");
		return mv;
	}
	*/
	/*
	 * @RequestMapping("/logout") public String logout() { //세션은 이미 받아 두었다
	 * session.removeAttribute("mvo"); return "index";
	 * 
	 * 
	 * }
	 */
	
	@RequestMapping("/logout")
	@ResponseBody
	public Map<String, String> logout(){
		session.removeAttribute("mvo");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("res", "0");
		return map;
	}
	
	
	
}
