package controllers.post;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import models.Post;
import models.User;
import utils.DBUtil;

@WebServlet("/posts/create")
@MultipartConfig(location="/Applications/Eclipse_4.8.0.app/Contents/workspace/nyancogram/WebContent/uploaded/")
public class PostCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PostCreateServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	    //アップロードファイルの取得
        Part part = request.getPart("file");
        //現在時刻を取得
        Timestamp current_time = new Timestamp(System.currentTimeMillis());
        //ファイル名を定義
        String file_name = null;
        if(part.getSubmittedFileName() != null && !part.getSubmittedFileName().equals("")) {

            //現在時刻の取得を取得し、文字列化してファイル名に利用
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            file_name = sdf.format(current_time) + ".jpg";
            //ファイルをlocationで設定したディレクトリに保存
            part.write(file_name);

        }


        //パラメータの取得(enctype="multipart/form-data"があることによる文字化け対策)
        String title = new String(request.getParameter("title").getBytes("UTF-8"));
        String content = new String(request.getParameter("content").getBytes("UTF-8"));

        //セッションオブジェクトにあるログインユーザー情報からIDを取得
        User login_user = (User) request.getSession().getAttribute("login_user");
        Integer user_id = login_user.getId();

        //Postクラスをインスタンス化し、値をセット
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        if(file_name != null) { post.setImage(file_name); }
        post.setUser_id(user_id);
        post.setCreated_at(current_time);
        post.setUpdated_at(current_time);

        //エンティティマネージャの生成
        EntityManager em = DBUtil.createEntityManger();

        //
        em.getTransaction().begin();
        em.persist(post);
        em.getTransaction().commit();
        em.close();


        //ユーザーのshow.jspへ移動
        response.sendRedirect(request.getContextPath() + "/");

	}

}
