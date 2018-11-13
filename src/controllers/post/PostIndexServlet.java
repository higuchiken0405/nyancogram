package controllers.post;

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
import utils.DBUtil;
import utils.TimeCalc;

@WebServlet("/posts/index")
public class PostIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PostIndexServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	    //エンティティマネージャの生成
	    EntityManager em = DBUtil.createEntityManager();
	    //「全ての投稿を取得する」クエリを実行した結果を取得
	    List<Post> posts = em.createNamedQuery("getAllPosts", Post.class).getResultList();
	    //「全てのコメントを取得する」クエリを実行した結果を取得
	    List<Comment> comments = em.createNamedQuery("getAllComments", Comment.class).getResultList();

        if(posts != null) {
            for(Post post:posts) {
                //「ポストに対するお気に入りの数を取得する」クエリを実行した結果を格納
                Long favorite_count = em.createNamedQuery("getFavoriteCounts", Long.class)
                                                    .setParameter("post_id", post.getId())
                                                    .getSingleResult();
                //結果をポストにセット
                post.setFavorite_count(favorite_count);
                //文字列を改行で分けて配列に変換し、セットする
                post.setContent_array(StringToArray.contentToArray(post));
                //現在時刻と投稿時刻に差分によるメッセージを取得し、セットする
                post.setTime_msg(TimeCalc.returnMsg(post.getCreated_at()));
            }
        }

	    //エンティティマネージャを終了
	    em.close();

	    //リクエストオブジェクトに実行結果を格納
	    request.setAttribute("posts", posts);
	    request.setAttribute("comments", comments);

        //セッションオブジェクトからフラッシュ、エラーメッセージを取得し、リクエストオブジェクトに格納
        request.setAttribute("flush", request.getSession().getAttribute("flush"));
        request.setAttribute("errors", request.getSession().getAttribute("errors"));
        //セッションオブジェクトからフラッシュ、エラーメッセージを削除
        request.getSession().removeAttribute("flush");
        request.getSession().removeAttribute("errors");


	    //投稿一覧画面へ遷移
	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/posts/index.jsp");
	    rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
