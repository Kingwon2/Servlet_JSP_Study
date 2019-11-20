package Subject;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(location = "C:/test")
@WebServlet("/input")
public class main extends HttpServlet {
	String origin_Location = "C:/test/test.jpg";  // 원본 파일 저장할 장소
	String target_Location = "C:/test/changed_image.jpg";  // 편집한 파일 저장할 장소
    public main() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig config) throws ServletException {
		System.out.print("init 메소드 호출");
	}


	
	
	protected void editImage(int width, int height, boolean is_Gray,String format) {
		try {
			Image image = ImageIO.read(new File(origin_Location));
			Image resizeImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			BufferedImage newImage = null;
			if(is_Gray) {
				newImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
			}
			else {
				newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			}
	        Graphics g = newImage.getGraphics();
	        g.drawImage(resizeImage, 0, 0, null);
	        g.dispose();
	        ImageIO.write(newImage, format, new File(target_Location));	
	        System.out.print("수정완료");
		}
		catch (Exception e){
            e.printStackTrace();
        }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("euc-kr"); //
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		int width = Integer.parseInt(request.getParameter("width"));    //width 추출
		int height = Integer.parseInt(request.getParameter("height"));  //height 추출
		boolean is_Gray = (request.getParameter("box") != null)? true : false;  // 체크박스 확인

		Part part = request.getPart("image");           //이미지 파일을 part 변수에 담음
		String fileName = part.getSubmittedFileName();
		String format = (fileName.split("[.]"))[1];          // 포멧 형식 추출


		part.write("test.jpg");    // 클라이언트가 보낸 파일을 저장
		System.out.println("복제완료");
		editImage(width,height,is_Gray, format);         // 편집 실행 
		
		
		
		String data = "<html>";
		data += "<head>";
		data += "<meta charset=\"UTF-8\">";
		data += "<title>이미지 편집</title>";
		data += "<style>";
		data += "h2 { text-align : center;}";
		data += ".reset_button { width : 100px; height : 20px;}";
		data += "</style>";
		data += "</head>";
		data += "<body>";
		data += "<h2> 수정된 이미지 </h2> <hr>";
		data += "<img src=\"C:/test/changed_image.jpg\"><br><br>";
		data += "<button type=\"button\" class=\"reset_button\" onclick=\"location.href='main.html' \">처음으로</button>";
		data += "</body>";
		out.print(data);
		
		
		
		
	}

}