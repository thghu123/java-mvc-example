package am.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import am.vo.EmpVO;

/**
 * Servlet implementation class EmpAll
 */
@WebServlet("/EmpAll")
public class EmpAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	SqlSessionFactory factory; //������� ����
	
    public EmpAll() {
      //��ü�� ������ �� �ѹ��� �����ϴ� ������ ����
    	try {
			
    		Reader r = Resources.getResourceAsReader("am/config/config.xml");
    		//r�� ��Ʈ���̱⿡ try ����� ������ �Ȼ������. ��� close�� �ϴ� ���̴�. ������ ����� ���״´�.
    		//�̷��� ���̸� ������ db������ ����. 
    		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
    		factory = builder.build(r);
    		//���丮�� ����������. ��� �Ʒ����� ��� ����.
    		//�����������⿡ ��������� ������ش�. static�̳�, member�� �����ϳ��̴�.
    		//
    		r.close();
    		
    		
		} catch (Exception e) {
			e.printStackTrace(); //���� ���� ���� ���
		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��û�� �ѱ�ó��
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset = utf-8");
		//��û�� �߻��� �� ����ȴ�
		
		//���丮�� ���� ������ ��� ��
		SqlSession ss = factory.openSession();
		
		//sqlsession�� ���ؼ� ���ϴ� sql ������ ȣ���Ѵ�
		List<EmpVO> list = ss.selectList("emp.total");
		//���⼭ �߻��ϴ� ����� ������ �ϳ��� �ƴ϶� �������� ���� �߻��Ѵ�.
		//�׷��� �� �ึ�� �ϳ��� EmpVO�� �����Ǿ� �������� EmpVO�� ArrayList�� ������� �Ѿ���Եȴ�
		
		PrintWriter out = response.getWriter();
		out.println("<table border = '1'>");
		out.println("<tbody>");
		for(int i = 0; i<list.size(); i++) {
			EmpVO vo = list.get(i); //������� �ϳ��� ����
			out.println("<tr>");
			out.println("<td>"+vo.getEmpno()+"</td>");
			out.println("<td>"+vo.getEname()+"</td>");
			out.println("<td>"+vo.getJob()+"</td>");
			
			out.println("</tr>");
			
		}
		out.println("</tbody");
		out.println("</table>");
		
		out.close(); //�ϳ��� ��Ʈ���̴� �ݾ��ֱ�
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
