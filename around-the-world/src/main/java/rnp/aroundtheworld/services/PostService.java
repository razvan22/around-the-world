package rnp.aroundtheworld.services;

import assets.MediaService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;
import rnp.aroundtheworld.entities.*;
import rnp.aroundtheworld.repositories.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostService {
    MediaService mediaService = new MediaService();
    @Autowired
    UserService userService;
    @Autowired
    PostRepository postRepository;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    PostImageRepository postImageRepository;
    @Autowired
    PostCommentRepository postCommentRepository;
    @Autowired
    PostRatingRepository postRatingRepository;

    public ResponseEntity savePost(MultipartFile[] imageFile, String postString ,HttpServletRequest request){

        if (userService.isUserAuthenticated(request)){
            String userName = userService.getUserNameFromJwtToken(request);
            User user =  userService.findByUsername(userName);
            Post post = jsonToPost( postString, user);
            post = postRepository.save(post);

            List<String> imagesPaths =  mediaService.getImagesPaths(imageFile);

            for(String imgPath: imagesPaths) {
                PostImage postImage = new PostImage(imgPath,post);
                postImageRepository.save(postImage);
            }

            return new ResponseEntity<Post>(post, HttpStatus.CREATED);

        } else return new ResponseEntity("Bad request", HttpStatus.BAD_REQUEST);

    }


    private Post jsonToPost ( String postString,  User author) {
        JSONObject rwaPost = new JSONObject(postString);
        JSONObject rwaLocation = rwaPost.getJSONObject("location");
        String postID =  UUID.randomUUID().toString();
        List<PostImage> imageList = new ArrayList<>();
        List<PostRating> postRatingList = new ArrayList<>();

        Post post = new Post(
                postID,
                new Date(), author,
                locationRepository.save( new Location(rwaLocation.getString("continent"), rwaLocation.getString("country"), rwaLocation.getString("address")))     ,
                rwaPost.getString("title"),
                rwaPost.getString("description"),
                imageList,
                postRatingList
                 );

        return post;
    }

    public ResponseEntity saveComment (PostComment comment, HttpServletRequest request){

        if (userService.isUserAuthenticated(request)){
            String userName = userService.getUserNameFromJwtToken(request);
            User user =  userService.findByUsername(userName);
            Post post = postRepository.getById(comment.getPost().getId());

            PostComment newComment = new PostComment(
                    user.getFirstName()+ " "+ user.getLastName(),
                    new Date(),
                    comment.getComment(),
                    post);
            postCommentRepository.save(newComment);
            List<PostComment> postComments = postRepository.getById(post.getId()).getComments();


            return new ResponseEntity<List<PostComment>>(postComments, HttpStatus.CREATED);

        } else return new ResponseEntity("Bad request", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity ratePost(PostRating rating, HttpServletRequest request){
        String postToRateId = rating.getPost().getId();
        if (saveNewRating(rating, request)){
            postRepository.findById(postToRateId);
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity("Bad request", HttpStatus.BAD_REQUEST);
    }

    private boolean saveNewRating(PostRating rating, HttpServletRequest request){
        if (userService.isUserAuthenticated(request)) {
            String userName = userService.getUserNameFromJwtToken(request);
            User user = userService.findByUsername(userName);
            Post post = postRepository.getById(rating.getPost().getId());
            PostRating newRating = new PostRating(user, post, rating.getRating());
            return postRatingRepository.save(newRating).getAuthor().getFirstName() != null;
        }
        return false;
    }



    public List<Post> getAll(){
        return postRepository.findAll();
    }


    public List<Post> findAllByContinentName (String continentName) {
        return postRepository.findAll().stream()
                .filter(post -> post.getLocation().getContinent().toLowerCase().equals(continentName.toLowerCase()) )
                .collect(Collectors.toList());
    }

    public List<Post> findAllWereTitleStartsWith (String title) {
        return postRepository.findAll()
                .stream()
                .filter(post -> post.getTitle().startsWith(title))
                .collect(Collectors.toList());
    }


    @Bean
    public javax.servlet.MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofBytes(100000000L));
        factory.setMaxRequestSize(DataSize.ofBytes(100000000L));
        return factory.createMultipartConfig();
    }
}

