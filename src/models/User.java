package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({

    @NamedQuery(
            //全てのユーザー情報を取得
            name="getAllUsers",
            query="SELECT u FROM User AS u ORDER BY u.name ASC"
    ),
    @NamedQuery(
            //ログイン時にメールアドレスとパスワードをチェック
            name="checkLoginMailAndPass",
            query="SELECT u FROM User AS u WHERE u.email = :email AND u.password = :password"
    )
})
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    //名前
    @Column(name="name", nullable=false)
    private String name;

    //性別　0:♂　1:♀
    @Column(name="gender", nullable=false)
    private int gender;

    //生息地域
    @Column(name="area", nullable=false)
    private String area;

    //アイコン画像名
    @Column(name="icon", nullable=false)
    private String icon;

    //メールアドレス
    @Column(name="email", nullable=false, unique=true)
    private String email;

    //パスワード
    @Column(name="password", nullable=false, length=64)
    private String password;

    //生成日時
    @Column(name="created_at", nullable=false)
    private Timestamp created_at;

    //更新日時
    @Column(name="updated_at", nullable=false)
    private Timestamp updated_at;

    //ゲッター・セッター
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }
    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }

    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }
    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }
    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

}
