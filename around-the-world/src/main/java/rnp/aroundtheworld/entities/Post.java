package rnp.aroundtheworld.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Post {
    @Id
    private String id;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date postDate;
    @ManyToOne
    private User author;
    @OneToMany(mappedBy = "post")
    private List<PostComment> comments;
    @ManyToOne
    private Location location;
    private String title;
    private String description;
    private double rating = 0.0;
    @OneToMany(mappedBy = "post")
    private List<PostImage> imageList;

    @OneToMany(mappedBy = "post")
    private List<PostRating> ratings;

    public Post(String id, Date postDate, User author, List<PostComment> comments, Location location, String title, String description, List<PostImage> imageList) {
        this.id = id;
        this.postDate = postDate;
        this.author = author;
        this.comments = comments;
        this.location = location;
        this.title = title;
        this.description = description;
        this.imageList = imageList;
    }

    public Post(String id, Date postDate, User author, Location location, String title, String description, List<PostImage> imageList) {
        this.id = id;
        this.postDate = postDate;
        this.author = author;
        this.location = location;
        this.title = title;
        this.description = description;
        this.imageList = imageList;
    }


    public Post(String id) {
        this.id = id;
    }


    public Post() {
    }

    public Double calculateRating() {

         Double total  =  getRatings().stream()
                .mapToDouble(PostRating::getRating)
                .sum() / 5 ;
        System.out.println(total);

        return 0.0;
    }

    public List<PostRating> getRatings() {
        return ratings;
    }
}