package com.project.ex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import bbs.util.Paging;
import mybatis.dao.BbsDAO;
import mybatis.vo.BbsVO;

@Controller
public class BbsController {

	@Autowired
	private BbsDAO b_dao;//알아서 들어올 것이다
	private int blockList = 10;//posts for 1page
	private int blockPage = 5; //page blcok

	@RequestMapping("/bbs")
	public ModelAndView bbsList(String bname, String cPage) {
		//DB 내용 가져가서 쓸거니까 String 주소반환 할 때보다는 모델앤 뷰가 좋다.
		ModelAndView mv = new ModelAndView(); //기본 생성자 있어서 편하다
		int p = 1;

		
		if( bname == null )
			bname = "BBS";
		
		if(cPage != null) {//뭔가가 넘어왔다는 의미 , 현재 페이지값, 안넘어오면 1페이지를 보게돼있다.
			p = Integer.parseInt(cPage);
		}

		int rowTotal = b_dao.totalCount(bname);//bbs의 전체 게시물수를 반환
		
		Paging page = new Paging(p, rowTotal, blockList, blockPage);
		
		//이쯤에서 일반 게시물의 목록을 얻어낸다.
		/* BbsVO[] ar = b_dao.getList(1, 10, bname); */
		 BbsVO[] ar = b_dao.getList(page.getBegin(), page.getEnd(), bname); 
		//현재 페이지값에 의해서 begin,end가 설정된다. 1페이지면 1-10페이지 설정될 것이다
		//원래는 pageing이 알아서 가져와줬을 것이다. 일단은 1,10으로 테스팅만 나중에 페이징 쓰자
		
		//저장할 떄 ar로 하자 예전에 el을 ar로 해서 출력했었따.
		mv.addObject("ar",ar);		
		mv.addObject("rowTotal",rowTotal); 
		mv.addObject("p_code",page.getSb().toString());//jsp에서는 p_code라고 써있었다.
		mv.addObject("blockList",blockList); 
		mv.addObject("nowPage",p); 
		

		//이작업을 하면 아래 페이지 블록 버튼이 나오게 된다
		
		//다시 가서 list.jsp 안에 가서 requestScope.이있는 데 
		//이는 안바꿔도 된다 위 mv는 request에 있는 애이다. 오히려 이게 맞는 말이다.
		//안써도 상관없다. 명확히 지정하면 속도는 더 빠를 것이다.
				//setting view page for display
		mv.setViewName("bbs/list");
		//원래 list만 쓰면 list.jsp였는 데 지금은 views 안에 bbs 안에 list.jsp로 가라는 의미
		//그렇게 파일도 생성하자.
		
		
		return mv;
		
	}
	/*
	 * @RequestMapping("/write") public ModelAndView bbsWrite() {
	 * 
	 * ModelAndView mv = new ModelAndView(); mv.setViewName("bbs/write");
	 * 
	 * return mv;
	 * 
	 * }
	 */
	
	
	
	
}
