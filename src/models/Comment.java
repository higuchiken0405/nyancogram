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
@NamedQueries ({

    @NamedQuery(
            name="getMyPostComments",
            query="SELECT c FROM Comment AS c Where c.post.user.id = :user_id"
            ),
    @NamedQuery(
            name="getAllComments",
            query="SELECT c FROM Comment AS c ORDER BY c.id ASC"
            )
})
@Table(name="comments")
public class Comment {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Lob
    @Column(name="body", length=140, nullable=false)
    private String body;

    @Transient
    private String[] body_array;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="post_id", nullable=false)
    private Post post;

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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
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
    public String[] getBody_array() {
        return body_array;
    }

    public void setBody_array(String[] body_array) {
        this.body_array = body_array;
    }


}
