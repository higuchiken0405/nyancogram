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

import models.User;
import utils.DBUtil;

@WebServlet("/users/index")
public class UserIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserIndexServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	    //エンティティマネージャの生成
	    EntityManager em = DBUtil.createEntityManager();
	    //「全ユーザー情報を取得する」クエリを実行した結果を取得
	    List<User> users = em.createNamedQuery("getAllUsers", User.class).getResultList();
	    //エンティティマネージャの終了
	    em.close();
	    //全ユーザー情報をリクエストオブジェクトに格納
	    request.setAttribute("users", users);
	    //index.jspへ移動
	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/users/index.jsp");
	    rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
