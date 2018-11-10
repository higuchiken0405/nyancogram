package controllers.signup;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import models.validators.UserValidator;
import utils.DBUtil;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SignupServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    //signup.jspへ移動
	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/topPage/signup.jsp");
	    rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    //EntityMangerの生成
	    EntityManager em = DBUtil.createEntityManger();
	    //パラメータの値を取得
	    String name = request.getParameter("name");
	    String gender = request.getParameter("gender");
	    String area_str = request.getParameter("area");
	    String area = area_str.substring(area_str.indexOf("　") + 1);
	    String email = request.getParameter("email");
	    String password = request.getParameter("password");

	    //Userクラスのインスタンス化
	    User user = new User();
	    //パラメータから取得した値を、名前・性別・生息地域・メールアドレス・パスワードにセット
	    user.setName(name);
	    user.setGender(gender);
	    user.setArea(area);
	    user.setEmail(email);
	    user.setPassword(password);

	    if(gender.equals("♂")) {
	        user.setIcon("default_male.png");
	    } else {
            user.setIcon("default_female.png");
	    }

	    //現在時刻を取得し、作成日時・更新日時にセット
	    Timestamp current_time = new Timestamp(System.currentTimeMillis());
	    user.setCreated_at(current_time);
        user.setUpdated_at(current_time);

        //バリデーション
        List<String> errors = UserValidator.validateUser(user);

        if(errors.size() > 0) {
            //エンティティマネージャを終了
            em.close();
            //Userインスタンスをリクエストオブジェクトに格納
            request.setAttribute("user", user);
            //signup.jspに戻る
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/topPage/signup.jsp");
            rd.forward(request, response);
        } else {
            //トランザクションを開始し、DBへUserインスタンスを登録した後、
            //登録を確定し、エンティティマネージャを終了させる
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            em.close();
            request.getSession().setAttribute("login_user", user);
            //ユーザー詳細ページへ移動
            response.sendRedirect(request.getContextPath() + "/");
        }
	}

}
