package controllers.favorite;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Favorite;
import models.Post;
import models.User;
import utils.DBUtil;

@WebServlet("/favorites/create")
public class FavoriteCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FavoriteCreateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //パラメータからポストのidを受け取る
        Integer post_id = Integer.parseInt(request.getParameter("post_id"));
        //エンティティマネージャを生成
        EntityManager em = DBUtil.createEntityManger();
        //パラーメータから取得したポストidで検索した結果を格納
        Post post = em.find(Post.class, post_id);
        //セッションオブジェクトからログインユーザーを取得
        User login_user = (User) request.getSession().getAttribute("login_user");


        //フェイバレットクラスをインスタンス化
        Favorite favorite = new Favorite();
        //ポスト、ユーザーの値をセット
        favorite.setPost(post);
        favorite.setUser(login_user);

        Long favorite_counts = em.createNamedQuery("checkRegisteredFavorite", Long.class)
                                                        .setParameter("post_id", post.getId())
                                                        .setParameter("user_id", login_user.getId())
                                                        .getSingleResult();

        if(favorite_counts > 0) {
            //お気に入り済みであった時
            //エンティティマネージャを終了
            em.close();

        } else {
            //お気に入り済みでない場合
            //トランザクション開始
            em.getTransaction().begin();
            //DBに登録
            em.persist(favorite);
            //登録をコミット
            em.getTransaction().commit();
            //エンティティマネージャを終了
            em.close();
        }


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
