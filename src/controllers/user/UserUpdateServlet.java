package controllers.user;

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
	    //パラメータを取得
	    String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        //名前・メールアドレス・パスワードをセット
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        //現在時刻を取得し、更新日時にセット
        user.setUpdated_at(new Timestamp(System.currentTimeMillis()));

        //バリデーションした結果のエラーメッセージリストを取得
        List<String> errors = UserValidator.validateUser(user);
        if(errors.size() > 0) {
            //エラーメッセージがある場合
            //エンティティマネージャを取得
            em.close();
            //リクエストオブジェジェクトにUserインスタンスを格納
            request.setAttribute("user", user);
            //usersのedit.jspに戻る
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/users/edit.jsp");
            rd.forward(request, response);
        } else {
            //エラーメッセージがない場合
            //トランザクションを開始し、コミットする。
            em.getTransaction().begin();
            em.getTransaction().commit();

            //コメットした後、 ユーザー情報を再取得する
            User login_user = em.find(User.class, user.getId());

            //エンティティマネージャを終了
            em.close();
            //セッションに格納されているユーザーのidを削除
            request.getSession().removeAttribute("user_id");
            //セッションオブジェクトのログインユーザー情報を再格納
            request.getSession().setAttribute("login_user", login_user);

            //ユーザーの詳細ページへ移動
            response.sendRedirect(request.getContextPath() + "/users/show?id=" + user.getId());
        }

	}

}
