package spring.util;

import java.io.File;

import org.aspectj.runtime.internal.PerObjectMap;

public class FileUploadUtil {

	public static String checkSameFileName(String filename, String path) {
		//path가 굳이 필요하지는 않지만, 다른 곳에서 접근할 수 있으니 추가
		//인자인 filename에서 확장자를 뺀 파일명을 가려내자
		//파일명의 .의 위치를 알아야한다.
		
		int period = filename.lastIndexOf(".");//뒤에서 부터 찾는 게 빠르기 때문
		//파일명과 확장자를 분리한다.
		String f_name = filename.substring(0,period); //==파일명, 뒤에 숫자 붙이려고 한다.
		//begin index : 해당 자리부터 다자름 end 는 ~부터 ~까지 자른다 == 살린다
		
		//확장자는 어떻게 알아내는가
		String suffix = filename.substring(period); //==파일명
		
		//이제 전체 경로가 필요하다
		//servletContext가 없다. 얘가 component가 아닌 이상 오토와이어드 못 쓴다.
		//고로 컨트롤러나 컴포넌트에서 미리 만들어서 전달하면된다.
		//근데 패스의 upload 뒤에 파일명은 안온다.
		
		String saveFile = path + System.getProperty("file.separator") + filename;
		//path + "/" + f_name; \\이렇게해야한다. 시스템 구분자가 무조건 os마다 있다
		//고로 그걸 불러오는 게 좋다 이렇게 하면 어디서나 호환이 가능하다
		
		//위의 경로를 가지고 존재여부를 확인합니다. 이때 필요한 것이 java.io.file
		File f = new File(saveFile);//문서까지 합쳐서 여기에 전체 경로를 포함해준다
		//이 위치에 위 파일이 있을 수도 있고 없을 수도 있다. 지금은 있냐 없냐를 따진다
		
		//존재할 경우를 생각해서 이름을 변경시킬 때 사용하는 숫자를 하나 만들자
		int idx = 1;
		/* test1.txt 이렇게 이름이 바뀌어도 바뀐 게 또 있을 수 있으므로 while문으로 비교해야한다 */
		while(f.exists()) {
			//같은 이름으로 파일이 존재하는 경우
			//파일명 뒤에 숫자를 붙여 파일명을 변경합니다.
			
			//문자 변경시는 스프링 버퍼를 이용한다
			StringBuffer sb = new StringBuffer();
			sb.append(f_name);
			sb.append(idx++); //숫자 붙인 후 1 증가
			sb.append(suffix);
			
			//위도 존재할 수가 있다
			filename = sb.toString();//변경할 이름이다
			saveFile = path + System.getProperty("file.separator") +filename;
			//이제 경로가 바뀌었고 이제 파일을 다시 생성
			f= new File(saveFile);
			//다시 돌아가서 이 이름마저 있는 지 확인한다.
			
			
		}
		
		return filename; //중복되지 않는 파일명 반환 == defaultrename policy의 기능을 구현함.
		
	
	}
}
