package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.User;


public class UserValidator {

    //名前、メールアドレス、パスワードをバリデーションした結果の
    //エラーメッセージのリストを得るメソッド
    public static List<String> validateUser(User user) {

        List<String> errors = new ArrayList<String>();

            //名前をバリデーション
            String name_error = _validateName(user.getName());
            if(!name_error.equals("")) {
                errors.add(name_error);
            }
            //メールアドレスをバリデーション
            String email_error = _validateEmail(user.getEmail());
            if(!email_error.equals("")) {
                errors.add(email_error);
            }
            //パスワードをバリデーション
            String password_error = _validatePassword(user.getPassword());
            if(!password_error.equals("")) {
                errors.add("password_error");
            }

            return errors;
    }

    //名前をバリデーションした結果のエラーメッセージを得るメソッド
    private static String _validateName(String name) {
        if(name == null || name.equals("")) {
            return "名前を入力してください";
        }
        return "";
    }
    //メールアドレスをバリデーションした結果のエラーメッセージを得るメソッド
    private static String _validateEmail(String email) {
        if(email == null || email.equals("")) {
            return "メールアドレスを入力してください";
        }
        return "";
    }
    //パスワードをバリデーションした結果のエラーメッセージを得るメソッド
    private static String _validatePassword(String password) {
        if(password == null || password.equals("")) {
            return "パスワードを入力してください";
        }
        return "";
    }
}
