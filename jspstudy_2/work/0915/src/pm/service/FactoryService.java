package pm.service;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class FactoryService {

	//���� ��ü�� �ƹ��� ���� ��������� ���丮�� ���� �ϳ��� �����ž��Ѵ�
	private static SqlSessionFactory factory;
	
	//static �ʱ�ȭ�� ���� -> �����ں��� �켱 ����
	static { //���Ⱑ �����ں��� �ٶ����ϴ� static���� ����� ��������鿡 ���� �ʱ�ȭ �۾�
		try {
			Reader r = Resources.getResourceAsReader("pm/config/config.xml");
			factory = new SqlSessionFactoryBuilder().build(r);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		
	}
	
	
	//�ܺ� �����ϵ��� �޼��� ����
	public static SqlSessionFactory getFactory() {
		return factory;
	}
	//static Ư¡
	//static �� �ϳ��� �����ȴ�. ��ü ������ ���ϴ��� static ���� ������ �� �ִ�
	
	
	
	
	
	
}
