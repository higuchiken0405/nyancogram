package models.validators;

import javax.persistence.EntityManager;

import models.User;
import utils.DBUtil;

public class SignupValidator {

    //サインアップ時に入力したユーザーをバリデーションした結果の
    //エラーメッセージを取得するメソッド
    public static String[] validate(User user) {

        String[] errors = new String[4];

        errors[0] = nameValidate(user.getName());
        errors[1] = emailValidate(user.getEmail());
        errors[2] = passwordValidate(user.getPassword());
        errors[3] = passwordConfirmationValidate(user.getPassword(), user.getPassword_confirmation());

        return errors;

    }

    //名前をバリデーションした結果のエラーメッセージを取得するメソッド
    private static String nameValidate(String name) {
        if(name == null || name.equals("")) {
            //名前がnullか空だった時
            //エラーメッセージを返す
            return "名前を入力してください";
        }
        return "";
    }

    //メールアドレスをバリデーションした結果のエラーメッセージを取得するメソッド
    private static String emailValidate(String email) {
        if(email == null || email.equals("")) {
            //メールアドレスがnullか空だった時
            //エラーメッセージを返す
            return "メールアドレスを入力してください";
        } else {
            //メールアドレスが入力されていた時
            //エンティティマネージャを生成
            EntityManager em = DBUtil.createEntityManager();
            //「入力したメールアドレスが登録されている数を取得する」クエリを実行し、結果を格納
            Long mail_count = em.createNamedQuery("checkLoginMail", Long.class)
                                                        .setParameter("email", email)
                                                        .getSingleResult();
            if(mail_count > 0) {
                //すでにメールアドレスが登録されていた場合
                //エラーメッセージを返す
                return "入力されたメールアドレスはすでに登録されています";
            }
        }
        return "";
    }

    //パスワードをバリデーションした結果のエラーメッセージを取得するメソッド
    private static String passwordValidate(String password) {

        if(password == null || password.equals("")) {
            //パスワードがnullか空だった時
            //エラーメッセージを返す
            return "パスワードを入力してください";
        }
        return "";
    }

    //パスワードとパスワード確認が同じが確認するメソッド
    private static String passwordConfirmationValidate(String password, String password_confirmation) {
        if(password != null && !password.equals("")) {
            //パスワードが入力された時
            if(password_confirmation == null || password_confirmation.equals("")) {
                //パスワード確認がnull または　空の時
                //エラーメッセージを返す
                return "同じパスワードを入力してください";
            }
            if(!password.equals(password_confirmation)) {
                //パスワードとパスワード確認が異なる時
                //エラーメッセージを返す
                return "同じパスワードを入力してください";
            }
        }
        return "";
    }
}
