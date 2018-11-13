package utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeCalc {

    //現在時刻と投稿時刻の差分によって取得する文字列を変えるメソッド
    public static String returnMsg(Timestamp created_at) {

        //TimeStamp型の投稿時刻をLong型に変換
        long created_time = created_at.getTime();
        //日付表示のフォーマットを指定
        SimpleDateFormat sdf = new SimpleDateFormat("yy年MM月dd日");

        //現在時刻をDate型で取得
        Date now = new Date();
        //Date型の現在時刻をLong型に変換
        Long current_time = now.getTime();
        //現在時刻と差分の時間を算出
        long timeDiff = (current_time - created_time) / (1000 * 60);

        if(timeDiff < 59) {
            //時間差が59以内ならnewを返す
            return "new";
        } else if(timeDiff <= 1440 && timeDiff >= 60) {
            //時間差が1時間以上24時間以内なら〜時間前を返す
            return (timeDiff / 60) + "時間前";
        } else {
            //それ以外ならyy年MM月dd日を返す
            return sdf.format(created_time);
        }

    }

}
