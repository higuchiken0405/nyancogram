package controllers.user;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import utils.DBUtil;

@WebServlet("/users/show")
public class UserShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserShowServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    //EntityManagerを生成
	    EntityManager em = DBUtil.createEntityManger();
	    //パラメータからユーザーIDを取得
	    Integer user_id = Integer.parseInt(request.getParameter("id"));
	    //セッションオブジェクトからログインユーザーのIDを取得
	    User login_user = (User) request.getSession().getAttribute("login_user");
	    Integer login_user_id = login_user.getId();
	    if(user_id == login_user_id) {
	        //パラーメータのIDとログインユーザーのIDが一致する時
	        //メイン画面へリダイレクト
            response.sendRedirect(request.getContextPath() + "/");
            return;
	    }
	    //パラメータから取得したIDで検索した結果を格納
	    User user = em.find(User.class, user_id);
	    //エンティティマネージャを終了
	    em.close();

	    if(user == null) {
	        //検索結果がnullの時
            //ユーザー一覧へリダイレクト
            response.sendRedirect(request.getContextPath() + "/users/index");
            return;

	    } else if (!user.equals(request.getSession().getAttribute("login_user"))) {
	        //取得したuserがログインユーザーと一致しない場合
	        //検索結果のuserをリクエストオブジェクトに格納
	        request.setAttribute("user", user);
	        //show.jspに移動
	        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/users/show.jsp");
	        rd.forward(request, response);
	    }
	}
}
