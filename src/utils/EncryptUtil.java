package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class EncryptUtil {

    //フォームに入力されたパスワードを暗号化するメソッド
    public static String getPasswordEncrypt(String plain_p, String salt) {

        //文字列retを定義
        String ret = "";

        if(plain_p != null && !plain_p.equals("")) {
            //入力されたパスワードがnull、または空ではない時
            //バイト配列を定義
            byte[] bytes;
            //入力されたパスワードとソルトを結合
            String password = plain_p + salt;
            try {
                //文字列からハッシュダイジェストを求める
                bytes = MessageDigest.getInstance("SHA-256").digest(password.getBytes());
                //バイト配列から16進数文字列を取得
                ret = DatatypeConverter.printHexBinary(bytes);
            } catch(NoSuchAlgorithmException ex) {}
        }
        //暗号化されたパスワードを返す
        return ret;
    }
}
