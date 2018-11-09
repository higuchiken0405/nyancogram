package controllers.login;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import utils.DBUtil;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    //topPgeのlogin.jspに移動
	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/topPage/login.jsp");
	    rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //チェック結果を定義(初期値false)
        Boolean check_result = false;

	    //パラメータを取得
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        //userを初期化
        User user = null;

        if(email != null && !email.equals("") && password != null && !password.equals("")) {
            //パラメータがnullか空ではない時
            //エンティティマネージャの生成
            EntityManager em = DBUtil.createEntityManger();

            try {
                //「セットした値のメールアドレスとパスワードを持つユーザーを取得」クエリを実行した結果を格納
                user = em.createNamedQuery("checkLoginMailAndPass", User.class)
                                            .setParameter("email", email)
                                            .setParameter("password", password)
                                            .getSingleResult();
            }catch(NoResultException e) {}

            //エンティティマネージャを終了
            em.close();

            if(user != null) {
                //Userインスタンスがnullではない時
                //チェック結果をtrue
                check_result = true;
            }
        }

        if(!check_result) {
            //チェック結果がtrueではない時(Userインスタンスがnullの時)
            //login.jspに戻る
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/login.jsp");
            rd.forward(request, response);
        } else {
            //チェック結果がtrueの時
            //セッションオブジェクトにメールアドレスとパスワードが一致するUserインスタンスを
            //ログインユーザーとして格納
            request.getSession().setAttribute("login_user", user);
            //メインページへリダイレクト
            response.sendRedirect(request.getContextPath() + "/");
        }

	}

}
