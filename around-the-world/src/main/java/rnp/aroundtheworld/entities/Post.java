package rnp.aroundtheworld.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name= "post")
public class Post {
    @Id
    private UUID id;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date postDate;
    @ManyToOne
    private User author;
    @OneToMany(mappedBy = "post")
    private List<PostComment> comments;
    @ManyToOne
    private Location location;
    private String title;
    private String description;

}