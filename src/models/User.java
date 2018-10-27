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


    @Column(name="name", nullable=false)
    private String name;

    @Column(name="password", nullable=false, length=64)
    private String password;

    @Column(name="email", nullable=false, unique=true)
    private String email;

    @Column(name="icon_url")
    private String icon_url;

    @Column(name="created_at", nullable=false)
    private Timestamp created_at;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
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
