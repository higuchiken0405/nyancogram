package models.validators;

import javax.persistence.EntityManager;

import models.User;
import utils.DBUtil;

public class UserEditValidator {

    //ユーザーをバリエーションした結果のエラーメッセージを取得するメソッド
    public static String[] userEditValidate(User user, Boolean password_check_flag, Integer login_user_id) {

        //エラーメッセージの配列を定義
        String[] errors = new String[3];
        //名前、メールアドレス、パスワードをバリデーションした結果のエラーメッセージを格納
        errors[0] = nameValidate(user.getName());
        errors[1] = emailValidate(user.getEmail(), login_user_id);
        errors[2] = passwordValidate(user.getPassword(), password_check_flag);
        //エラーメッセージの配列を返す
        return errors;
    }


    //名前をバリデーションした結果のエラーメッセージを取得するメソッド
    private static String nameValidate(String name) {

        if(name == null || name.equals("")) {
            return "名前を入力してください";
        }
        return "";
    }
    //メールアドレスをバリデーションした結果のエラーメッセージを取得するメソッド
    private static String emailValidate(String email, Integer login_user_id) {
        if(email == null || email.equals("")) {
        return "メールアドレスを入力してください";
        } else {
            //エンティティマネージャを生成
            EntityManager em = DBUtil.createEntityManager();

            //
            User user = em.find(User.class, login_user_id);
            String user_email = user.getEmail();

            if(!user_email.equals(email)) {
                //ログインユーザーのメールアドレスとパラーメータから取得したメールアドレスが一致しない場合
                //「入力されたメールアドレスで登録されているユーザー数を取得する」クエリを実行し、結果を格納
                Long email_count = em.createNamedQuery("checkRegisteredMail", Long.class)
                                                .setParameter("email", email)
                                                .getSingleResult();
                if(email_count > 0) {
                    //入力されたメールアドレスが登録済みだった場合
                    //入力されたメールアドレスはすでに登録されています
                    return "入力されたメールアドレスはすでに登録されています";
                }

            }

        }
        return "";
    }
    //パスワードをバリデーションした結果のエラーメッセージを取得するメソッド
    private static String passwordValidate(String password, Boolean password_check_flag) {

        if(password_check_flag) {
            if(password == null || password.equals("name")) {
                return "メールアドレスを入力してください";
            }
        }
        return "";
    }


}
