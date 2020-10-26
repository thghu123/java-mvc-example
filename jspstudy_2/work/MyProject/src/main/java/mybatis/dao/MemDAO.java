package mybatis.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import mybatis.vo.MemberVO;

public class MemDAO {
	@Autowired
	private SqlSessionTemplate sst;

	//회원 목록
	public MemberVO[] getAll() {
		MemberVO[] ar = null;
		//mapper을 호출해서 받아오는 과정 selectOne처럼 jsp와 똑같다.
		List<MemberVO> list = sst.selectList("mem.all");
		if(list != null && list.size()>0) {
			ar = new MemberVO[list.size()];//memVo 생성이 아니라 공간만 만든 것
			//리스트에 복사하기 좋은 공간을 확보
			
			//이제 list의 각요소들을 ar에 복사
			list.toArray(ar);			
		}
		
		//여기서 ss를 닫았는 데 이를 닫으면 안된다. 그 당시에는 지역 변수였다.
		//지금은 멤버 변수로 선언돼있다. 이 DAO는 한번만 생성되기에, 2번째 호출 시부터 사용 못한다.
		
		return ar;
	}
	

	public MemberVO getInfo(String m_id, String m_pw){
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("m_id", m_id);
		map.put("m_pw", m_pw);
		MemberVO vo = sst.selectOne("mem.login_ok",map);
		if(vo != null) {
			return vo;
		}

		return null;
	}
	
	
			
}