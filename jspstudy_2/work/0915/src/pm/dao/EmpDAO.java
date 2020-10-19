package pm.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import pm.service.FactoryService;
import pm.vo.EmpVO;

public class EmpDAO {
	//���ϴ� mapper�� ȣ���ϰ� �����ϴ� �޼��带 �����Ѵ�
	//mapper�� �ϳ� sql �������� ���� �ϳ� ������.
	//�� id�� �����ؾ� ���������� ���ϴ�.
	public static List<EmpVO> getTotal(){
		SqlSession ss = FactoryService.getFactory().openSession();
		//�켱 ���ϴ� ���۸� ȣ���ϱ� ���ؼ� sql session�� ���丮�� ���� ���´�
		
		List<EmpVO> list = ss.selectList("emp.total");
		
		ss.close();//�̰͵� ������ �ݾ�����Ѵ�.
		
		return list;
		
		//�̳��� jsp���� ȣ���Ѵ�.
	}

	public static List<EmpVO> getSearchName(String name){
		
		SqlSession ss = FactoryService.getFactory().openSession();
		
		List<EmpVO> list = ss.selectList("emp.searchName",name);
		
		ss.close();//�̰͵� ������ �ݾ�����Ѵ�.
		return list;
		
		
		
	}



}
