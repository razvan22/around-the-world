package rnp.aroundtheworld.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PostRating {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @ManyToOne
    private User author;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    private Post post;
    private Integer rating;

    public PostRating(User author, Post post, Integer rating) {
        this.author = author;
        this.post = post;
        this.rating = rating;
    }

    public PostRating() {
    }
}
