package pm.servlet;

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
 * Servlet implementation class SearchName
 */
@WebServlet("/SearchName")
public class SearchName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	//�ϳ��� �����ϱ� ���� ��� ������ �����Ѵ�.
	SqlSessionFactory factory; 	
    public SearchName() {
    	
    	//�ѹ��� �����Ͽ� ���丮�� ��´�.
        //��ü�� ������ �� �ѹ��� �����ϴ� ������ ����
      	try {
      		  			
      		Reader r = Resources.getResourceAsReader("am/config/config.xml");
			/*
			 * SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder(); factory =
			 * builder.build(r);
			 */
      		factory = new SqlSessionFactoryBuilder().build(r);
      		r.close();
      		
      		
  		} catch (Exception e) {
  			e.printStackTrace(); //���� ���� ���� ���
  		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset = utf-8");
		
		//��û�� �Ķ���� �ޱ� s_name
		String s_name = request.getParameter("s_name");
		//������ ���� ����, �Ķ���͸� mybatis �� mapper�� ȣ���ϸ鼭 ���ڷ� �����մϴ�.
		SqlSession ss = factory.openSession();
		List<EmpVO> list = ss.selectList("emp.search_name", s_name); //��Ʈ���� �ϳ� �־�����Ѵ�. s_name Ȩ���������� �޾��־����ϱ�
		//���ڰ� 2-3���϶� EmpVO�� �ᵵ ������ ���� ���̴� ���� map�����̴�.
		
		//�����غ�
		PrintWriter out = response.getWriter();
		
		out.println("<table border = '1'>");
		out.println("<tbody>");
		for(int i = 0; i<list.size(); i++) {
			EmpVO vo = list.get(i); //������� �ϳ��� ����
//			out.println("<tr>");
//			out.println("<td>"+vo.getEmpno()+"</td>");
//			out.println("<td>"+vo.getEname()+"</td>");
//			out.println("<td>"+vo.getJob()+"</td>");
//			
//			out.println("</tr>");
			out.println(vo.printAll()); //html�� ��ȯ�� ��.
			
		} //������ out.println�̷��� ���ϰ� ���� ���۷� �޾ƿ��� ���� �񱳸� �ϰ�, ����� ���Ѵ�.
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
