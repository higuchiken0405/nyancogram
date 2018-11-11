package models.validators;

import models.User;

public class LoginValidator {

    public static String[] validate(User user) {

        String[] errors = new String[4];

        errors[0] = emailValidate(user.getEmail());
        errors[1] = passwordValidate(user.getPassword());

        return errors;

    }

    //メールアドレスをバリデーションするメソッド
    private static String emailValidate(String email) {
        if(email == null || email.equals("")) {
            //メールアドレスがnullか空だった時
            //エラーメッセージを返す
            return "メールアドレスを入力してください";
        }
        return "";
    }
    //パスワードをバリデーションするメソッド
    private static String passwordValidate(String password) {

        if(password == null || password.equals("")) {
            //パスワードがnullか空だった時
            //エラーメッセージを返す
            return "パスワードを入力してください";
        }
        return "";
    }

}
