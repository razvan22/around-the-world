package rnp.aroundtheworld.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rnp.aroundtheworld.entities.Location;
import rnp.aroundtheworld.entities.Post;
import rnp.aroundtheworld.repositories.LocationRepository;
import rnp.aroundtheworld.repositories.PostRepository;

import java.util.List;
import java.util.UUID;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;
    @Autowired
    LocationRepository locationRepository;

    public void savePost(Post post){
        UUID postID = UUID.randomUUID();
        post.setId(postID);
        Location location = post.getLocation();
        post.setLocation(locationRepository.save(location));
        postRepository.save(post);
    }

    public List<Post> getAll(){
        return postRepository.findAll();
    }


}
