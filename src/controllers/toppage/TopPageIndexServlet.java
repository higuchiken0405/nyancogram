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

import models.Comment;
import models.Post;
import models.StringToArray;
import models.User;
import utils.DBUtil;
import utils.TimeCalc;

@WebServlet("/index.html")
public class TopPageIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TopPageIndexServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	    //セッションオブジェクトから、ログインユーザー情報を取得
	    User login_user = (User) request.getSession().getAttribute("login_user");
	    if(login_user != null) {
	        //ログインユーザーがnullではない時
	        //エンティティマネージャの生成
	        EntityManager em = DBUtil.createEntityManager();
	        //「自分が投稿したポストを全て取得する」クエリを実行した結果を格納
	        List<Post> posts = em.createNamedQuery("getAllMyPosts", Post.class)
	                                                    .setParameter("user_id", login_user.getId())
	                                                    .getResultList();
	        //「ログインユーザーが投稿したポストのコメントを全て取得する」クエリを実行した結果を格納
	        List<Comment> comments = em.createNamedQuery("getMyPostComments", Comment.class)
	                                                    .setParameter("user_id", login_user.getId())
	                                                    .getResultList();

	        if(posts != null) {
	            //ポストを検索した結果がnullではない時
	            for(Post post:posts) {
	                //「ポストに対するお気に入りの数を取得する」クエリを実行した結果を格納
	                Long favorite_count = em.createNamedQuery("getFavoriteCounts", Long.class)
	                                                    .setParameter("post_id", post.getId())
	                                                    .getSingleResult();
	                //結果をポストにセット
	                post.setFavorite_count(favorite_count);
	                //内容を改行で分けて配列に変換し、セットする
	                post.setContent_array(StringToArray.contentToArray(post));
	                //現在時刻と投稿時刻に差分によるメッセージを取得し、セットする
	                post.setTime_msg(TimeCalc.returnMsg(post.getCreated_at()));
	            }
	        }

	        //エンティティマネージャを終了
	        em.close();


	        //検索結果postsをリクエストオブジェクトに格納
	        request.setAttribute("posts", posts);
	        request.setAttribute("comments", comments);

	        //セッションオブジェクトからフラッシュ、エラーメッセージを取得し、リクエストオブジェクトに格納
	        request.setAttribute("flush", request.getSession().getAttribute("flush"));
	        request.setAttribute("errors", request.getSession().getAttribute("errors"));
	        //セッションオブジェクトからフラッシュ、エラーメッセージを削除
	        request.getSession().removeAttribute("flush");
            request.getSession().removeAttribute("errors");

	        //toppageのindex.jspへ移動
	        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/topPage/index.jsp");
	        rd.forward(request, response);

	    } else {
	        //ログインユーザーがnullの時
	        //セッションオブジェクトからフラッシュメッセージを取得し、リクエストオブジェクトに格納
	        request.setAttribute("flush", request.getSession().getAttribute("flush"));
	        //セッションオブジェクトからフラッシュ、エラーメッセージを削除
            request.getSession().removeAttribute("flush");
            request.getSession().removeAttribute("errors");
	        //toppageのindex.jspへ移動
	        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/topPage/index.jsp");
	        rd.forward(request, response);
	    }

	}
}
