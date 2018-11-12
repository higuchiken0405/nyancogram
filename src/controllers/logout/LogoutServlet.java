package controllers.logout;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LogoutServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    //セッションからログインユーザーを削除
	    request.getSession().removeAttribute("login_user");
	    //セッションオブジェクトごと除去
	    //request.getSession().invalidate();
	    //フラッシュメッセージをセッションオブジェクトに格納
	    request.getSession().setAttribute("flush", "ログアウトしました");
	    //トップページへリダイレクト
	    response.sendRedirect(request.getContextPath() + "/");

	}


}
