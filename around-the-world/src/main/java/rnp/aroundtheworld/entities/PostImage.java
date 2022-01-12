package rnp.aroundtheworld.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PostImage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;
    private String url;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    private Post post;

    public PostImage() {
    }

    public PostImage(String url, Post post) {
        this.url = url;
        this.post = post;
    }

    public Integer getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public Post getPost() {
        return post;
    }
}

