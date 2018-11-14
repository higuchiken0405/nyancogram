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

@WebServlet("/users/edit")
public class UserEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserEditServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            //エンティティマネージャを生成
            EntityManager em = DBUtil.createEntityManager();
            //パラメータからユーザーIDを取得
            Integer user_id = Integer.parseInt(request.getParameter("id"));
            //セッションオブジェクトからログインユーザーのIDを取得
            User login_user = (User) request.getSession().getAttribute("login_user");
            Integer login_user_id = login_user.getId();

            if(user_id != login_user_id) {
                //パラメータのIDとログインユーザーのIDが一致しない時
                //ログインユーザーの編集ページへリダイレクト
                response.sendRedirect(request.getContextPath() + "/users/edit?id=" + login_user_id);
                return;
            }

            //パラメータから取得したIDで検索した結果を格納
            User user = em.find(User.class, user_id);
            //エンティティマネージャを終了
            em.close();

            if(user == null) {
                //検索結果のuserがnullの時
                //ログインユーザーの編集ページへリダイレクト
                response.sendRedirect(request.getContextPath() + "/users/edit?id=" + login_user_id);
                return;
            }
            //検索結果のユーザーをリクエストオブジェクトに格納
            request.setAttribute("user", user);
            //検索結果のユーザーのidをセッションオブジェクトに格納
            request.getSession().setAttribute("user_id", user.getId());
            //セッションIDをトークンとしてリクエストオブジェクトに格納
            request.setAttribute("_token", request.getSession().getId());
            //edit.jspへ移動
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/users/edit.jsp");
            rd.forward(request, response);
        }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
