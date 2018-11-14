package controllers.user;

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

@WebServlet("/users/show")
public class UserShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserShowServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	    //EntityManagerを生成
	    EntityManager em = DBUtil.createEntityManager();
	    //ユーザーIDを定義
	    Integer user_id = 0;
	    //セッションオブジェクトからユーザーを取得
	    User show_user = (User) request.getSession().getAttribute("user");

	    if(show_user != null) {
	        //セッションオブジェクトから取得したユーザーのIDを取得
	        user_id = show_user.getId();
	        //セッションオブジェクトからユーザー情報を削除
	        request.getSession().removeAttribute("user");
	    } else {
	        //セッションオブジェクトにユーザーがない時
	        //パラメータからユーザーIDを取得
	        user_id = Integer.parseInt(request.getParameter("id"));
	    }

	    //セッションオブジェクトからログインユーザーのIDを取得
	    User login_user = (User) request.getSession().getAttribute("login_user");
	    Integer login_user_id = login_user.getId();

	    if(user_id == login_user_id) {
	        //パラーメータのIDとログインユーザーのIDが一致する時
	        //エンティティマネージャを終了
	        em.close();
	        //メイン画面へリダイレクト
            response.sendRedirect(request.getContextPath() + "/");
            return;
	    }


	    //セッションオブジェクトから取り出したユーザーIDとログインユーザーIDが一致しない場合
	    //パラメータから取得したIDで検索した結果を格納
	    User user = em.find(User.class, user_id);

	    if(user == null) {
	        //検索結果がnullの時
	        //エンティティマネージャを終了
	        em.close();
            //ユーザー一覧へリダイレクト
            response.sendRedirect(request.getContextPath() + "/users/index");
            return;

	    } else if (!user.equals(request.getSession().getAttribute("login_user"))) {
	        //取得したuserがログインユーザーと一致しない場合

            //「詳細ページのユーザーが投稿したポストを全て取得する」クエリを実行した結果を格納
            List<Post> posts = em.createNamedQuery("getAllMyPosts", Post.class)
                                                        .setParameter("user_id", user.getId())
                                                        .getResultList();
            //「詳細ページのユーザーが投稿したポストのコメントを全て取得する」クエリを実行した結果を格納
            List<Comment> comments = em.createNamedQuery("getMyPostComments", Comment.class)
                                                        .setParameter("user_id", user.getId())
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
                    //投稿内容を改行で分けて配列に変換し、セットする
                    post.setContent_array(StringToArray.contentToArray(post));
                    //現在時刻と投稿時刻に差分によるメッセージを取得し、セットする
                    post.setTime_msg(TimeCalc.returnMsg(post.getCreated_at()));
                }
            }

	        //エンティティマネージャを終了
	        em.close();

	        //検索結果のuserをセッションオブジェクトに、posts、commentsをリクエストオブジェクトに格納
	        //request.getSession().setAttribute("user", user);
	        request.setAttribute("user", user);
            request.setAttribute("posts", posts);
            request.setAttribute("comments", comments);

            //セッションオブジェクトからフラッシュ、エラーメッセージを取得し、リクエストオブジェクトに格納
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.setAttribute("errors", request.getSession().getAttribute("errors"));
            //セッションオブジェクトからフラッシュ、エラーメッセージを削除
            request.getSession().removeAttribute("flush");
            request.getSession().removeAttribute("errors");

	        //show.jspに移動
	        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/users/show.jsp");
	        rd.forward(request, response);
	    }
	}
}
