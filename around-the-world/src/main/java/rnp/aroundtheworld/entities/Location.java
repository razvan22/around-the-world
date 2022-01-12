package rnp.aroundtheworld.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String continent;
    private String country;
    private String address;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "location")
    private List<Post> posts;

    public Location(String continent, String country, String address) {
        this.continent = continent;
        this.country = country;
        this.address = address;
    }

    public Location() {}

    public Integer getId() {
        return id;
    }

    public String getContinent() {
        return continent;
    }

    public String getCountry() {
        return country;
    }

    public String getAddress() {
        return address;
    }

    public List<Post> getPosts() {
        return posts;
    }
}
