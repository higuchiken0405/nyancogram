package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Post;

public class PostValidator {

    //ポストをバリデーションした結果のエラーメッセージを取得するメソッド
    public static List<String> validate(Post post) {
        //エラーメッセージリストを定義
        List<String> errors = new ArrayList<String>();
        //タイトルをバリデーションした結果のエラーメッセージを取得
        String error_title = titleValidate(post.getTitle());
        if(error_title.length() > 0) {
            //エラーメッセージがある場合、リストに追加
            errors.add(error_title);
        }
        //内容をバリデーションした結果のエラーメッセージを取得
        String error_content = contentValidate(post.getContent());
        if(error_content.length() > 0) {
            //エラーメッセージがある場合、リストに追加
            errors.add(error_content);
        }
        //エラーメッセージリストを返す
        return errors;
    }

    //タイトルをバリデーションした結果のエラーメッセージを取得するメソッド
    private static String titleValidate(String title) {

        if(title == null || title.equals("")) {
            return "タイトルの入力してください";
        }
        return "";
    }

    //内容をバリデーションした結果のエラーメッセージを取得するメソッド
    private static String contentValidate(String content) {
        if(content == null || content.equals("")) {
            return "内容を入力してください";
        }
        return "";
    }
}
