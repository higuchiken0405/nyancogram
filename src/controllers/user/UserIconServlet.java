package controllers.user;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import models.User;
import utils.DBUtil;

@WebServlet("/users/icon")
@MultipartConfig()
public class UserIconServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserIconServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    //フォームで送られたファイルを取得
        Part part = request.getPart("icon");
        //セッションオブジェクトからユーザーID取得
        Integer user_id = (Integer) request.getSession().getAttribute("user_id");
        if(part.getSubmittedFileName() == null || part.getSubmittedFileName().equals("")) {
            //送られたファイル名がnullか空の時、ユーザー情報編集画面へリダイレクト
            response.sendRedirect(request.getContextPath() + "/users/edit?id=" +  user_id);
            return;
        }
        //セッションオブジェクトからログインユーザー情報を取得し、ユーザー名を画像名に使用
        User loginUser = (User) request.getSession().getAttribute("login_user");
        String userName = loginUser.getName();
        String name = userName + ".jpg";

        String path = getServletContext().getRealPath("/icon") + "/" + name;
        //ファイルの書き込み
        try {
            part.write(path);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("失敗しました");
        }

        //エンティティを生成
        EntityManager em = DBUtil.createEntityManger();
        //セッションオブジェクから取得したのユーザーID番号で検索した結果を格納
        User user = em.find(User.class, user_id);

        if(user == null) {
            //検索結果がnullだったり、ログインユーザーと一致しない時
            //ユーザー編集ページへリダイレクト
            response.sendRedirect(request.getContextPath() + "/edit?id=" + user_id);
            return;
        }
        //ユーザーのアイコン画像名をセット
        user.setIcon(name);

            em.getTransaction().begin();
            em.getTransaction().commit();

            response.sendRedirect(request.getContextPath() + "/users/edit?id=" + user_id);

	}

}
