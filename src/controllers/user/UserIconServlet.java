package controllers.user;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

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
@MultipartConfig(location="/Applications/Eclipse_4.8.0.app/Contents/workspace/nyancogram/WebContent/icon/")
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
        Integer user_id = (Integer) request.getSession().getAttribute("user_id");
        if(part == null || part.equals("")) {
            response.sendRedirect(request.getContextPath() + "/users/edit?id=" +  user_id);
            return;
        }
        //現在時刻を取得し、指定したフォーマットの文字列に変換し、ファイル名に利用
        Timestamp curret_time = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String name = sdf.format(curret_time) + ".jpg";

        //ファイルの書き込み
        try {
            part.write(name);
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
