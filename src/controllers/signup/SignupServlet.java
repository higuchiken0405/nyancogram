package controllers.signup;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import models.validators.SignupValidator;
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
	    //パラメータから値を取得
	    String name = request.getParameter("name");
	    String gender = request.getParameter("gender");
	    String area_str = request.getParameter("area");
	    String area = area_str.substring(area_str.indexOf("　") + 1);
	    String email = request.getParameter("email");
	    String password = request.getParameter("password");
	    String password_confirmation = request.getParameter("password_confirmation");


	    //Userクラスのインスタンス化
	    User user = new User();
	    //パラメータから取得した値を、名前・性別・生息地域・メールアドレス・パスワードにセット
	    user.setName(name);
	    user.setGender(gender);
	    user.setArea(area);
	    user.setEmail(email);
	    user.setPassword(password);
	    user.setPassword_confirmation(password_confirmation);
	    if(gender.equals("♂")) {
	        //性別が♂だった場合、アイコンにdefault_male.pngをセット
	        user.setIcon("default_male.png");
	    } else {
	        //性別が♀だった場合、アイコンにdefault_femail.pngをセット
            user.setIcon("default_female.png");
	    }
	    //現在時刻を取得し、作成日時・更新日時にセット
	    Timestamp current_time = new Timestamp(System.currentTimeMillis());
	    user.setCreated_at(current_time);
        user.setUpdated_at(current_time);

        //バリデーション
        String[] errors = SignupValidator.Validate(user);

        if(errors[0].length() > 0 || errors[1].length() > 0
                || errors[2].length() > 0 || errors[3].length() > 0) {

            //エラーメッセージがある場合
            //エンティティマネージャを終了
            em.close();
            //ユーザーをリクエストオブジェクトに格納
            request.setAttribute("user", user);
            //エラーメッセージをリクエストオブジェクトに格納
            request.setAttribute("errors", errors);
            //signup.jspに戻る
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/topPage/signup.jsp");
            rd.forward(request, response);
        } else {
            //トランザクションを開始し、DBへUユーザーを登録した後、
            //登録を確定し、エンティティマネージャを終了させる
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            em.close();
            //ユーザーをログインユーザーとして、セッションオブジェクトに格納
            request.getSession().setAttribute("login_user", user);
            //メイン画面へリダイレクト
            response.sendRedirect(request.getContextPath() + "/");
        }
	}

}
