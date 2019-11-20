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
	String origin_Location = "C:/test/test.jpg";  // ���� ���� ������ ���
	String target_Location = "C:/test/changed_image.jpg";  // ������ ���� ������ ���
    public main() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig config) throws ServletException {
		System.out.print("init �޼ҵ� ȣ��");
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
	        System.out.print("�����Ϸ�");
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
		
		int width = Integer.parseInt(request.getParameter("width"));    //width ����
		int height = Integer.parseInt(request.getParameter("height"));  //height ����
		boolean is_Gray = (request.getParameter("box") != null)? true : false;  // üũ�ڽ� Ȯ��

		Part part = request.getPart("image");           //�̹��� ������ part ������ ����
		String fileName = part.getSubmittedFileName();
		String format = (fileName.split("[.]"))[1];          // ���� ���� ����


		part.write("test.jpg");    // Ŭ���̾�Ʈ�� ���� ������ ����
		System.out.println("�����Ϸ�");
		editImage(width,height,is_Gray, format);         // ���� ���� 
		
		
		
		String data = "<html>";
		data += "<head>";
		data += "<meta charset=\"UTF-8\">";
		data += "<title>�̹��� ����</title>";
		data += "<style>";
		data += "h2 { text-align : center;}";
		data += ".reset_button { width : 100px; height : 20px;}";
		data += "</style>";
		data += "</head>";
		data += "<body>";
		data += "<h2> ������ �̹��� </h2> <hr>";
		data += "<img src=\"C:/test/changed_image.jpg\"><br><br>";
		data += "<button type=\"button\" class=\"reset_button\" onclick=\"location.href='main.html' \">ó������</button>";
		data += "</body>";
		out.print(data);
		
		
		
		
	}

}