package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Comment;
import models.Post;
import models.User;

public class CommentValidator {

    //コメントをバリデーションした結果のエラーメッセージを取得するメソッド
    public static List<String> validate(Comment comment) {
        //エラーメッセージリストを定義
        List<String> errors = new ArrayList<String>();
        //コメント内容をバリデーションした結果のエラーメッセージを取得
        String error_body = bodyValidate(comment.getBody());
        if(error_body.length() > 0) {
            //エラーメッセージがある場合、リストに追加
            errors.add(error_body);
        }
        //ポスト、ユーザーをバリデーションした結果のエラーメッセージを取得
        String error_post_user = postOrUserValidate(comment.getPost(), comment.getUser());
        if(error_post_user.length() > 0) {
            //エラーメッセージがある場合、リストに追加
            errors.add(error_post_user);
        }
        //エラーメッセージリストを返す
        return errors;
    }

    //コメント内容をバリデーションした結果のエラーメッセージを取得するメソッド
    private static String bodyValidate(String body) {
        if(body == null || body.equals("")) {
            //コメント内容がnull または　空の時
            //エラーメッセージを返す
            return "コメントを入力してください";
        }
        return "";
    }

    //ポストインスタンスをバリデーションした結果のエラーメッセージを取得するメソッド
    private static String postOrUserValidate(Post post, User user) {
        if(post == null || user == null) {
            //ポスト、またはユーザーがnullの時
            //エラーメッセージを返す
            return "コメント登録に失敗しました";
        }
        return "";
    }



}



