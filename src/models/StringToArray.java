package models;

public class StringToArray {

    //ポストのコンテンツを\nで改行して分割した文字列の配列を取得するメソッド
    public static String[] contentToArray(Post post) {
        //ポストからコンテンツを取得し\n(改行)で分割し、配列に格納
        String[] content = post.getContent().split("\n");
        //文字列の配列を返す
        return content;
    }

}
