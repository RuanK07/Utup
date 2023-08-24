package com.ruankennedy.socialnetwork.service.serviceAction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import com.ruankennedy.socialnetwork.dto.request.ChangePostSubtitle;
import com.ruankennedy.socialnetwork.dto.request.CreatePostDTO;
import com.ruankennedy.socialnetwork.model.Post;
import com.ruankennedy.socialnetwork.model.Profile;
import com.ruankennedy.socialnetwork.model.User;
import com.ruankennedy.socialnetwork.repository.PostRepository;
import com.ruankennedy.socialnetwork.repository.ProfileRepository;
import com.ruankennedy.socialnetwork.repository.UserRepository;


@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository, ProfileRepository profileRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.profileRepository = profileRepository;
		this.userRepository = userRepository;
    }

    @Override
    public Post createPost(User userLogged, CreatePostDTO request) {
    	
    	Profile profile = profileRepository.findByUserId(userLogged.getId());
    	
        Post post = Post.builder()
                .subtitle(request.getSubtitle())
                .postPhoto(request.getPostPhotos())
                .postedMoment(LocalDateTime.now())
                .profile(profile)
                .build();
        
        postRepository.save(post);
        
        profile.getPosts().add(post);
        
        return post;
    }

    @Override
    public Post getPostById(String postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("Post not found"));
    }
    
    @Override
    public List<Post> getPostsBySubtitle(String subtitle) {
        return postRepository.findBySubtitleContainingIgnoreCase(subtitle);
    }
    
    @Override
    public List<Post> getPostsByUserNickname(String nickname) {
        Optional<User> userOptional = userRepository.findByNickname(nickname);
        User user = userOptional.get();
        Profile profile = user.getProfile();
        return postRepository.findByProfile(profile);
    }

    @Override
    public Post updatePostSubtitle(String postId, ChangePostSubtitle request) {
    	Optional<Post> postToUpdate = postRepository.findById(postId);
    	Post post = postToUpdate.get();
    	post.setSubtitle(request.getSubtitle());
        return postRepository.save(post);
    }

}