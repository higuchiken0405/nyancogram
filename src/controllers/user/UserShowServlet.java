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
	    //パラメータから取得したidの値で検索した結果を取得
	    User user = em.find(User.class, Integer.parseInt(request.getParameter("id")));
	    //エンティティマネージャを終了
	    em.close();
	    //検索結果のuserをリクエストオブジェクトに格納
	    request.setAttribute("user", user);
	    //show.jspに移動
	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/users/show.jsp");
	    rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
