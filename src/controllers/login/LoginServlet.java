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
import models.validators.LoginValidator;
import utils.DBUtil;
import utils.EncryptUtil;

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


	    //パラメータを取得
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        //仮のユーザーを定義し、パラメータの値をセット
        User tentative_user = new User();
        tentative_user.setEmail(email);
        tentative_user.setPassword(password);

        String[] errors = LoginValidator.validate(tentative_user);
        if(errors[0].length() > 0 || errors[1].length() >0) {
            //エラーメッセージがある場合
            //リクエストオブジェクトにエラーメッセージを格納
            request.setAttribute("errors", errors);
            //ログイン画面へフォワード
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/topPage/login.jsp");
            rd.forward(request, response);
        }

        //userを定義(初期化)
        User user = null;
        if(email != null && !email.equals("") && password != null && !password.equals("")) {
            //パラメータがnullか空ではない時
            //エンティティマネージャの生成
            EntityManager em = DBUtil.createEntityManager();

            try {
                //「セットした値のメールアドレスとパスワードを持つユーザーを取得」クエリを実行した結果を格納
                user = em.createNamedQuery("checkLoginMailAndPass", User.class)
                                            .setParameter("email", email)
                                            .setParameter("password", EncryptUtil.getPasswordEncrypt(
                                                    password,
                                                    (String) this.getServletContext().getAttribute("salt")
                                                    ))
                                            .getSingleResult();

            }catch(NoResultException e) {}
            //エンティティマネージャを終了
            em.close();

            if(user == null) {
                //検索結果がnullの時
                //エラーメッセージを配列に格納
                errors[0] = "メールアドレスかパスワードが間違っています";
                //リクエストオブジェクトにエラーメッセージを格納
                request.setAttribute("errors", errors);
                //ログイン画面へフォワード
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/topPage/login.jsp");
                rd.forward(request, response);

            } else {
                //セッションオブジェクトにメールアドレスとパスワードが一致するUserインスタンスを
                //ログインユーザーとして格納
                request.getSession().setAttribute("login_user", user);
                //フレッシュメッセージをセッションオブジェクトに格納
                request.getSession().setAttribute("flush", "ログインに成功しました");
                //メインページへリダイレクト
                response.sendRedirect(request.getContextPath() + "/");
            }

        }

	}

}
