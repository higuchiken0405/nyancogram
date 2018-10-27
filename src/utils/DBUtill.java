package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtill {

    //永続化ユニット名の設定
    private static final String PERSISTENCE_UNIT_NAME = "nyancogram";
    //エンティティマネージャファクトリの定義
    private static EntityManagerFactory emf = null;

    //エンティティマネージャを生成するメソッド
    public static EntityManager createEntityManger() {

        return _getEntityManagerFactory().createEntityManager();

    }

    //エンティティマネージャファクトリを得るするメソッド
    private static EntityManagerFactory _getEntityManagerFactory() {

        if(emf == null) {
            //ファクトリがない場合、設定したユニット名を元に生成
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return emf;




    }



}
