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
	    EntityManager em = DBUtil.createEntityManger();
	    //パラメータから取得したIDの値で検索した結果を取得
	    User user = em.find(User.class, Integer.parseInt(request.getParameter("id")));
	    //エンティティマネージャを終了
	    em.close();
	    //Userインスタンスをリクエストオブジェクトに格納
	    request.setAttribute("user", user);
	    //edit.jspへ移動
	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/users/edit.jsp");
	    rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
