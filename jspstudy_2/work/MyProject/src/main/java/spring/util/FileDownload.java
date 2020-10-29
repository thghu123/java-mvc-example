package spring.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileDownload
 @webservlet("/FileDownload")
 */
public class FileDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileDownload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		//파일을 누르고 요청할 때 수행되는 애니까 아래 파라미터를 요청해야한다
		
		//파라미터 값 받기 (dir,filename) spring이 아닌 서블릿이기에 직접 받아야해요
		String dir = request.getParameter("dir");
		String filename = request.getParameter("filename");
		
		//dir을 절대 경로로 만듭니다.
		//내장 객체 ServletConext application이 필요합니다.
		//서블릿은 거의 다가지고 있기에 servlet스스로 context를 가져와 application역할을 할 수 있다.
		String path = getServletContext().getRealPath(dir);
		//web 하드 기능에서는 위치가 막 바뀌기도 하니까 위처럼 dir을 사용하였다 ???
		
		//앞서 얻어낸 upload의 절대경로와 파일명을 연결 -> 전체경로
		String fullPath = path + System.getProperty("file.separator") + filename;
		
		//이걸 가지고 파일 객체를 생성합니다.
		File f = new File(fullPath);
		//위 파일 객체를 클라이언트로 보내고 싶어하는 것
		
		//바구니 역할 생성
		byte[] buf = new byte[2048]; 
		//byte[] buf = new byte[(int)f.length()]; //더블형으로 나와서 캐스팅
		//위 배열은 크기가 적을 때 한번만 담을 때 딱맞게 사용한다. 원래는 파일크기보다 클 수 있기에 round 사용
		
		//전송 데이터 스트림 처리될 때 문자셋 지정 , 서버입장에서 클라이언트로 보냄
		response.setContentType("application/octet-stream;charset=8859_1");
		//파일 첨부시에 문자 셋을 반드시 표현해줘야만하낟. 데이터들의 한글 처리도 된다. http 프로토콜로
		//데이터 보내니까 파일이름 , 데이터 전반적으로 모두 한글 처리한다
		
		//어디 경로에 넣을 지 지정. 다운로드 대화상자 처리
		response.setHeader("Content-Disposition", 
				"attachment;filename="+ new String(filename.getBytes("utf-8"),"8859_1"));
			//"attachment;filename="+ new String(filename.getBytes("8859_1"),"euc-kr"));
		//java 안에서는 유니코드를 쓰기에 한글이 안깨지려면 utf8이 맞다. 하지만 스트링 객체 만들때는
		//filename.getbyte하면 바이트단위로 바귄다. 전송하려면 패킷 단위로 쪼개야한다
		//반대편에서 조합이 일어날때 깨지면 안된다. 한글 파일이 filename에 들어왔다면 쪼개고
		//그 형식을 넣어줘야 조합이 가능하다, 이놈을 클라이언트 환경에서 쓰려면 8859 룰 써야해서 
		//utf-8형태로 지정이돼서 다져졌다. 이걸 파일로 쓰기 위해 보낼 때는 8859_1로 변환해준다
		//httpfile이 8859를 이용하기 위해. 클라이언트에서는 역순으로 재조합
		//	위처럼 명시 안하면 대화상자 안나오고 한글 깨진다.
		
	
		
		//전송타입이 이진 데이터 binary , 쪼개보내는 http protocol을 쓰는 형식을 따라가야한다 
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		//이제 http header에 파일을 보내겠다는 설정의 끝
		
		if(f.isFile()) {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
					//file을 보내려고한다. 파일이 어디있는 걸 읽어서 보내야하니까 inputstream이 인자로 필요
					//버퍼드 스트림은 버퍼로 인해 유실이 적어져서. 
					//인자에는 fileinputstream을 만들어서 넣으면 파일을 읽는 데 쓰겠다는 의미이다.
					//서버에 위치하고 있는 파일을 읽을 준비가 된 것. 읽은 건 아니다
			
			//이제는 서버입장에서, 클라이언트한테 보내는 스트림이 필요하다
			//요청한 곳으로 보내기 위해라는 의미는 응답을 의미한다. 그 응답을 Stream을 준다
			//stream을 응답객체, response 로 부터 스트림 얻어내면 됩니다.
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
			
			int size = -1;
			try {
				//읽어서 보내기
				while((size = bis.read(buf)) != -1) {
					//더이상 읽을값이 없을 때를 의미한다, 그리고 이미 위 코드로 읽어왔다
					bos.write(buf,0,size); //읽은 만큼 쓰게하라
					bos.flush(); //초기화한다
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();			
			
			}finally {
			
				if(bos!=null) {
					bos.close();
				}
				if(bis!=null) {
					bis.close();
				}
			}
			//파일인풋스트림은 bos가 닫히면 참조된 거니까 자동으로 닫힌다.
			
			
			
		}
		
		//이제 web.xml에 등록하러가야한다. 
		
		
		
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
