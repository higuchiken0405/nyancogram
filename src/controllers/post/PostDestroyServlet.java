package controllers.post;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Comment;
import models.Favorite;
import models.ImageFindDelete;
import models.Post;
import utils.DBUtil;

@WebServlet("/posts/destroy")
public class PostDestroyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PostDestroyServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    //エンティティマネージャを生成
	    EntityManager em = DBUtil.createEntityManger();

	    //パラメータからポストのIDを取得
	    Integer post_id =  Integer.parseInt(request.getParameter("post_id"));
	    //取得したIDで検索した結果のポストを格納
	    Post post = em.find(Post.class, post_id);

	    //検索したポストに対するコメント一覧を取得
	    List<Comment> comments = em.createNamedQuery("getPostComments", Comment.class)
	                                                        .setParameter("post_id", post.getId())
	                                                        .getResultList();

	    //検索したポストに対するいいね一覧を取得
        List<Favorite> favorites = em.createNamedQuery("getPostFavorites", Favorite.class)
                                                            .setParameter("post_id", post.getId())
                                                            .getResultList();
        //
	    em.getTransaction().begin();
	    //いいねをDBから除去
	    for(Favorite favorite:favorites) {

	            em.remove(favorite);
	     }
	    //コメントをDBから除去
	    for(Comment comment:comments) {

	        em.remove(comment);
	    }
	    //ポストの画像を削除
	    ImageFindDelete.imageFindDelte(post.getImage());
	    //ポストをDBから除去
	    em.remove(post);
	    //除去を確定
	    em.getTransaction().commit();
	    //エンティティマネージャを終了
	    em.close();

        response.sendRedirect(request.getContextPath() + "/");

	}

}
