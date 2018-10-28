package controllers.post;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/posts/create")
public class PostCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PostCreateServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	       //アップロードファイルの取得
        Part part = request.getPart("file");
        //現在時刻の取得を取得し、文字列化してファイル名に利用
        Timestamp current_time = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String file_name = sdf.format(current_time) + ".jpg";
        //ファイルをlocationで設定したディレクトリに保存
        part.write(file_name);

        //パラメータの取得(enctype="multipart/form-data"があることによる文字化け対策)
        String title = new String(request.getParameter("title").getBytes("UTF-8"));
        String content = new String(request.getParameter("content").getBytes("UTF-8"));


        //ユーザーのshow.jspへ移動
        response.sendRedirect(request.getContextPath() + "/users/show");

	}

}
