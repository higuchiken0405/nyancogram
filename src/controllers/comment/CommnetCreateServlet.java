package controllers.comment;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Comment;
import models.Post;
import models.User;
import utils.DBUtil;

@WebServlet("/comments/create")
public class CommnetCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CommnetCreateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    //パラメータからコメント内容、投稿のIDを取得
	    String body = request.getParameter("body");
	    Integer post_id = Integer.parseInt(request.getParameter("post_id"));
	    //エンティティマネージャを生成
	    EntityManager em = DBUtil.createEntityManger();
	    //パラメータから取得した投稿のIDで検索した結果を格納
	    Post post = em.find(Post.class, post_id);
	    //セッションからログインユーザー情報を取得
	    User user = (User) request.getSession().getAttribute("login_user");
	    //現在時刻を取得
	    Timestamp current_time = new Timestamp(System.currentTimeMillis());

	    //コメントクラスをインスタンス化し、値をセット
	    Comment comment = new Comment();
	    comment.setBody(body);
	    comment.setPost(post);
	    comment.setUser(user);
	    comment.setCreated_at(current_time);
	    comment.setUpdated_at(current_time);

	    //トランザクション開始
	    em.getTransaction().begin();
	    //DBに登録
	    em.persist(comment);
	    //登録をコミット
	    em.getTransaction().commit();
	    //エンティティマネージャを終了
	    em.close();

        //トップページへ移動
        try {
            //詳細ページから送られてきたユーザーIDをセッションオブジェクトに保存
            request.getSession().setAttribute("user_id",request.getParameter("user_id"));
            //元のページにリダイレクト
            response.sendRedirect(new URI(request.getHeader("referer")).getPath());
        } catch (URISyntaxException e) {
            //エラー詳細表示
            e.printStackTrace();
            //トップページに戻る
            response.sendRedirect(request.getContextPath() + "/");
        }
	}
}
