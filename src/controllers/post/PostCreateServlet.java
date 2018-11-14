package controllers.post;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

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
import models.validators.PostValidator;
import utils.DBUtil;

@WebServlet("/posts/create")
@MultipartConfig()
public class PostCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PostCreateServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    //トークンをパラメータから取得(クロスサイトリクエストフォージェリ対策)
	    String _token = request.getParameter("_token");

	    if(_token != null && _token.equals(request.getSession().getId())) {
	        //トークンがnullではない　かつ　セッションIDと等しい時

	        //アップロードファイルの取得
	        Part part = request.getPart("file");
	        //現在時刻を取得
	        Timestamp current_time = new Timestamp(System.currentTimeMillis());
	        //ファイル名を定義
	        String file_name = null;
	        if(part.getSubmittedFileName() != null && !part.getSubmittedFileName().equals("")) {
	            //フォームでファイルが送られた時、
	            //現在時刻の取得を取得し、文字列化してファイル名に利用
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	            //ファイル名を設定(jpg形式)
	            file_name = sdf.format(current_time) + ".jpg";
	            //保存場所を設定
	            String path = getServletContext().getRealPath("/uploaded") + "/" + file_name;
	            //ファイルの書き込み
	            try {
	                part.write(path);
	            } catch(FileNotFoundException e) {
	                e.printStackTrace();
	                System.out.println("失敗しました");
	            }
	        }

	        //パラメータの取得(enctype="multipart/form-data"があることによる文字化け対策)
	        String title = new String(request.getParameter("title").getBytes("UTF-8"));
	        String content = new String(request.getParameter("content").getBytes("UTF-8"));
	        //セッションオブジェクトにあるログインユーザー情報からIDを取得
	        User loginUser = (User) request.getSession().getAttribute("login_user");

	        //Postクラスをインスタンス化し、値をセット
	        Post post = new Post();
	        post.setTitle(title);
	        post.setContent(content);
	        if(file_name != null) { post.setImage(file_name); }
	        post.setUser(loginUser);
	        post.setCreated_at(current_time);
	        post.setUpdated_at(current_time);

	        //ポストをバリデーションした結果のエラーメッセージを取得
	        List<String> errors = PostValidator.validate(post);
	        if(errors.size() > 0) {

	            //エラーメッセージがある場合
	            //エラーメッセージリストをセッションオブジェクトに格納
	            request.getSession().setAttribute("errors", errors);
	            //トップページへ移動
	            response.sendRedirect(request.getContextPath() + "/");
	        } else {

	            //エンティティマネージャの生成
	            EntityManager em = DBUtil.createEntityManager();

	            //トランザクション開始
	            em.getTransaction().begin();
	            //投稿をDBに登録
	            em.persist(post);
	            //登録をコミット
	            em.getTransaction().commit();
	            //エンティティマネージャを終了
	            em.close();
	            //フラッシュメッセージをセッションオブジェクトに格納
	            request.getSession().setAttribute("flush", "投稿に成功しました");
	            //トップページへ移動
	            response.sendRedirect(request.getContextPath() + "/");

	        }

	    }


	}

}
