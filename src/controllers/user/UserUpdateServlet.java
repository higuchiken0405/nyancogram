package controllers.user;

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
import models.validators.UserEditValidator;
import utils.DBUtil;

@WebServlet("/users/update")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserUpdateServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    //エンティティマネージャの生成
	    EntityManager em = DBUtil.createEntityManager();
	    //セッションから取得したユーザーidの値で検索した結果を取得
	    User user = em.find(User.class, (Integer)(request.getSession().getAttribute("user_id")));
	    //パラメータから名前、メールアドレス、パスワードの値を取得
	    String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        //パスワードをチェックするフラグを定義(false)
        Boolean password_check_flag = false;
        if(password == null || password.equals("")) {
            //パラメータから取得したパスワードがnull　または空の時
            //名前・メールアドレスをセット
            user.setName(name);
            user.setEmail(email);
        } else {
            //パスワードがnullか空ではない時
            //パスワードチェックフラグを立てる
            password_check_flag = true;
            //名前・メールアドレス・パスワードをセット
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
        }
        //現在時刻を取得し、更新日時にセット
        user.setUpdated_at(new Timestamp(System.currentTimeMillis()));

        //セッションオブジェクトからログインユーザー情報を取得
        User login_user = (User) request.getSession().getAttribute("login_user");

        //バリデーションした結果のエラーメッセージリストを取得
        String[] errors = UserEditValidator.userEditValidate(user, password_check_flag, login_user.getId());
        if(errors[0].length() > 0 || errors[1].length() > 0 || errors[2].length() > 0) {
            //エラーメッセージがある場合
            //エンティティマネージャを終了
            em.close();
            //リクエストオブジェジェクトにUserインスタンス、エラーメッセージを格納
            request.getSession().setAttribute("user", login_user);
            request.getSession().setAttribute("errors", errors);
            //usersのedit.jspに戻る
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/users/edit.jsp");
            rd.forward(request, response);
        } else {
            //エラーメッセージがない場合
            //トランザクションを開始し、コミットする。
            em.getTransaction().begin();
            em.getTransaction().commit();

            //コミットした後、 ユーザー情報を再取得し、ログインユーザーとする
            login_user = em.find(User.class, user.getId());

            //エンティティマネージャを終了
            em.close();
            //セッションオブジェクトに格納されているユーザーのidを削除
            request.getSession().removeAttribute("user_id");
            //セッションオブジェクトに格納されているエラーメッセージを削除
            request.getSession().removeAttribute("errors");
            //セッションオブジェクトのログインユーザー情報を再格納
            request.getSession().setAttribute("login_user", login_user);
            //セッションオブジェクトにフラッシュメッセージを格納
            request.getSession().setAttribute("flush", "ユーザー情報の編集に成功しました");
            //ユーザーの詳細ページへ移動
            response.sendRedirect(request.getContextPath() + "/users/show?id=" + user.getId());
        }

	}

}
