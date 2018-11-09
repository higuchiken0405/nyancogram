package controllers.toppage;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Post;
import models.User;
import utils.DBUtil;

@WebServlet("/index.html")
public class TopPageIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TopPageIndexServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    //セッションオブジェクトから、ログインユーザー情報を取得
	    User login_user = (User) request.getSession().getAttribute("login_user");
	    System.out.println(login_user);
	    if(login_user != null) {
	        //ログインユーザーがnullでない時
	        //エンティティマネージャの生成
	        EntityManager em = DBUtil.createEntityManger();

	        System.out.println("DBに接続");
	        //「自分が投稿したポストを全て取得する」クエリを実行した結果を格納
	        List<Post> posts = em.createNamedQuery("getAllMyPosts", Post.class)
	                                                    .setParameter("user_id", login_user.getId())
	                                                    .getResultList();

	        //エンティティマネージャを終了
	        em.close();
	        //検索結果postsをリクエストオブジェクトに格納
	        request.setAttribute("posts", posts);

	    //toppageのindex.jspへ移動
	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/topPage/index.jsp");
	    rd.forward(request, response);

	    } else {
            System.out.println("DBに接続できていない");

	        //toppageのindex.jspへ移動
	        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/topPage/index.jsp");
	        rd.forward(request, response);
	    }

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
