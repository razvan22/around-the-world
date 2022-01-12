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

    public Post() {
    }
    public Post(String id) {
        this.id = id;
    }


    public Post(String id, Date postDate, User author, Location location, String title, String description, List<PostImage> imageList, List<PostRating> ratings) {
        this.id = id;
        this.postDate = postDate;
        this.author = author;
        this.location = location;
        this.title = title;
        this.description = description;
        this.imageList = imageList;
        this.ratings = ratings;
    }


    public Post(String id, Date postDate, User author, List<PostComment> comments, Location location, String title, String description, List<PostImage> imageList,  List<PostRating> ratings) {
        this.id = id;
        this.postDate = postDate;
        this.author = author;
        this.comments = comments;
        this.location = location;
        this.title = title;
        this.description = description;
        this.imageList = imageList;
        this.ratings = ratings;

    }

    public Double calculateRating() {
        int number1 = 0;
        int number2 = 0;
        int number3 = 0;
        int number4 = 0;
        int number5 = 0;

        for(PostRating r : ratings){
            double rating = r.getRating();

            switch ((int) rating){
                case 1:
                    number1++;
                    break;
                case 2:
                    number2++;
                    break;
                case 3:
                    number3++;
                    break;
                case 4:
                    number4++;
                    break;
                case 5:
                    number5++;
                    break;
            }
        }

        double rating = (double) ( (1*number1 + 2*number2 + 3*number3 + 4*number4 + 5*number5) / 5);
        if (rating >= 5){
            return 5.0;
        }
        return  rating ;
    }

    public double getRating() {
        return calculateRating();
    }

    public void setRating(double rating) {
        if (rating <= 5.0){
            this.rating = rating;
        }
    }

    public List<PostRating> getRatings() {
        return ratings;
    }
}