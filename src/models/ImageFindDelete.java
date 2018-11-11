package models;

import java.io.File;

public class ImageFindDelete {



    public static void imageFindDelte(String image) {

        String path = "/Applications/Eclipse_4.8.0.app/Contents/workspace/nyancogram/WebContent/uploaded/" + image;
        File file = new File(path);

        if(!file.exists()) {

            System.out.println("ファイルが見つかりません");
            return;
        }

        if(file.delete()) {

            System.out.println("ファイルの削除に成功しました");
            return;
        }

        System.out.println("ファイルの削除に失敗しました");

    }


}
