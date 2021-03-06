package rnp.aroundtheworld.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class PostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotNull
    private String authorName;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date commentDate;
    @NotNull
    private String comment;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    private Post post;

    public PostComment() {
    }

    public PostComment(String authorName, Date commentDate, String comment, Post post) {
        this.authorName = authorName;
        this.commentDate = commentDate;
        this.comment = comment;
        this.post = post;
    }

    public PostComment(String comment, Post post) {
        this.comment = comment;
        this.post = post;
    }

    public Integer getId() {
        return id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public String getComment() {
        return comment;
    }

    public Post getPost() {
        return post;
    }
}

