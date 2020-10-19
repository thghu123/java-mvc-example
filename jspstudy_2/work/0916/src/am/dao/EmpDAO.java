package am.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import am.service.FactoryService;
import am.vo.EmpVO;

public class EmpDAO {

	
	
	public static List<EmpVO> getAll(){
		
		SqlSession ss = FactoryService.getFactory().openSession();
		
		List<EmpVO> list = ss.selectList("emp.All");
		
		ss.close();
		
		return list;
		
		
		
	}
	
	
	public static List<EmpVO> getSearchNo(String no){
		
		int s_no = Integer.parseInt(no);
		
		
		SqlSession ss = FactoryService.getFactory().openSession();
		
		
		List<EmpVO> list = ss.selectList("emp.searchNo",s_no);
		
		//�̰� �ļ���Ʈ�� �ް� empno ���� �翷�� ��ȣ ����.
		ss.close();
		
		return list;
	}
	
	
	//���� ������ Ȱ���ϴ� �޼��� 

		public static List<EmpVO> search(String type, String value){ 
			
		SqlSession ss = FactoryService.getFactory().openSession();
		//���� ��Ƽ�� ȯ�濡�� ���ڸ� ���� ���� �ѹ��� �������ϴ� �� �̶� ���̴� ���� map�����̴�
		//key,value�� ��� ������ �� �ִ�.
		Map<String, String> map = new HashMap<String, String>();
		//hash table�� new�ص� �ȴ�
		//key�� value�� �ڷ����� �����Ѵ�
		
		map.put("searchType", type); //���� ������
		map.put("searchValue", value);
		//�̰� my batis �� ������ �ȴ�. 2�� �ڷḦ �ش�
		
		List<EmpVO> list = ss.selectList("emp.search",map);
		//���� �θ� �� Map�� ���. �ѹ��� 2���� ���� �����ϴ� ��
		
		//������ search�� �߿��ϴ�. �̰� mapper���� ��� ó���ϴ� ��?
		
		ss.close();
		
		return list;
		
	}
	
		//��ȯ���� ���� int�� �����Ѵ�. ������ �����ߴ� ���ߴ� �ǹ̷� true, false�� 
		//��ȯ�� �ϱ� ���� boolean�� ���� ���ȯ��
		public static boolean updat(String no, String name, String job, String sal){ 
			SqlSession ss = FactoryService.getFactory().openSession();
			//������ ����Ʈ������ �̷��� ����.
			
			
			
			//���� ��Ƽ�� ȯ�濡�� ���ڸ� ���� ���� �ѹ��� �������ϴ� �� �̶� ���̴� ���� map�����̴�
			//key,value�� ��� ������ �� �ִ�.
			
			//�ʱ����� �غ��Ѵ�. �˾Ƽ� id������ ȣȯ���ش�.
			Map<String, String> map = new HashMap<String, String>();
			//�ڵ��ϼ����� ����, hash table�� new�ص� �ȴ�
			//key�� value�� �ڷ����� �����Ѵ�, Ű�� ����� �߷��� ��
			//�������� ���� String ���� ���鵵 String 
			
			//������ ������ map, list, empVO ��� ��ü, �����ϰ� ���� ��ü�� ���� �� �ִ�.
			
			//����Ŭ�� �ϳ��� �ϳ��� ���� �� �ִ�. mysql�� values�� ,�� �־
			//for���� �Ἥ �ѹ��� ������ insert�� �� �ִ� �׷��� ArrayList�� map value�� ����.
			int tmp = 0; //���� �����϶��� ���.
			//�ش� key���� �ݵ�� mapper.xml���ϰ� ������ ���������Ѵ�. �������� key�ϱ�
			if(no != null || no.trim().length()>0) {
				//������ �ȵǴ� ��
				map.put("sempno", no);
				
		
			}
			if(name != null || name.trim().length()>0) {
			map.put("sename", name); //���� ������
			tmp++;
			}
			if(job != null || job.trim().length()>0) {
			map.put("sjob", job);
			tmp++;
			}
			if(sal != null || sal.trim().length()>0) {
			map.put("ssal", sal);
			tmp++;
			}
			//������ ȯ�濡�� ó���ϴ� �� ����. jsp ���ܰ�
		
			//���� ���� �ϳ��� ���ٸ�? map�� ����Ǿ��� ���� mybatis�� ���۸� ȣ���� ���̴�.
			boolean value = false;
			if(tmp>0) {

			//�̰� my batis �� ������ �ȴ�. 2�� �ڷḦ �ش�.
			//�̾ȿ� ���� �Է� ���ϸ� ���鰪�� ����. ��� �̰� ������ emp.xml���� ó�������� �ʾƵ��ȴ�
			//�ش� sename ��ü�� ����� null�̴�.
			
			int cnt = ss.update("emp.edit", map); 
			//update�� ��ȯ���� int ��� ���� �ٲ����� �ǹ��Ѵ�., 1�� ��ȯ�� ���̴�.
			/* ss.insert("emp.edit",map); */
			//���� �θ� �� Map�� ���. �ѹ��� 2���� ���� �����ϴ� ��
			
			//update�� ���� insert�� �μ�Ʈ ����� ��
			
			
			if(cnt > 0) {
				//db�� ������ �����ϰ� ���ο� Ʈ�������� �����ض�
				ss.commit();
				ss.close();
				 value = true; 
				return true;
			}
			else {
				ss.close();
				//return false;
			}
			}
			
			//else {//return false;}
			
			return value;
			
			//������ search�� �߿��ϴ�. �̰� mapper���� ��� ó���ϴ� ��?
			//ss.commit();

			
		}
		
		public static boolean add(EmpVO vo){ 
			SqlSession ss = FactoryService.getFactory().openSession();
			
			boolean value = false;
			
			int cnt = ss.insert("emp.add", vo); 
			
			if(cnt > 0) {
				//db�� ������ �����ϰ� ���ο� Ʈ�������� �����ض�
				ss.commit();
				
				 value = true; 
				return true;
			}
			else {
				ss.rollback(); //db������ ���� ���� ����� �� Ʈ������� ���Ӱ� �����ض�.
				//Ʈ����� �� ������� ����. db ���� �Ͼ ���� ����ϰ� commit�ϸ� ������ �Ѵ�.
				//�� ó�������� ���ο� Ʈ����� ������� ������. rollback�� ������ ���� ����� ������.
				
				//return false;
				//���ݱ����� ������ ��������� �����ؾ� �ߺ��� �ȵȴ�.
			}
			ss.close();
			return value;
			}
			
			//else {//return false;}
			
			
			
			//������ search�� �߿��ϴ�. �̰� mapper���� ��� ó���ϴ� ��?
			//ss.commit();

			
		
		
		
}
	
	
	
	
	

