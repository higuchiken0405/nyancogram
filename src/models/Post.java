package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@NamedQueries({
    @NamedQuery(
            name = "getAllMyPosts",
            query = "SELECT p FROM Post AS p Where p.user.id = :user_id ORDER BY p.id DESC"
            ),
    @NamedQuery(
            name = "getAllPosts",
            query = "SELECT p FROM Post AS p ORDER BY p.id DESC"
            ),
})
@Table(name="posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="title", nullable=false)
    private String title;

    @Lob
    @Column(name="content", nullable=false)
    private String content;

    @Transient
    private String[] content_array;

    @Column(name="image")
    private String image;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name="created_at", nullable=false)
    private Timestamp created_at;

    @Column(name="updated_at", nullable=false)
    private Timestamp updated_at;

    @Transient
    private Long favorite_count;


    @Transient
    private String time_msg;


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getContent_array() {
        return content_array;
    }

    public void setContent_array(String[] content_array) {
        this.content_array = content_array;
    }

    public String getContent() {
        return content;
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

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getFavorite_count() {
        return favorite_count;
    }

    public void setFavorite_count(Long favorite_count) {
        this.favorite_count = favorite_count;
    }


    public String getTime_msg() {
        return time_msg;
    }


    public void setTime_msg(String time_msg) {
        this.time_msg = time_msg;
    }


}
