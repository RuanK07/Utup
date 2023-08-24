package com.ruankennedy.socialnetwork.config;

import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ruankennedy.socialnetwork.enumerated.RoleName;
import com.ruankennedy.socialnetwork.model.Comment;
import com.ruankennedy.socialnetwork.model.Friend;
import com.ruankennedy.socialnetwork.model.Post;
import com.ruankennedy.socialnetwork.model.Profile;
import com.ruankennedy.socialnetwork.model.Role;
import com.ruankennedy.socialnetwork.model.User;
import com.ruankennedy.socialnetwork.repository.CommentRepository;
import com.ruankennedy.socialnetwork.repository.FriendRepository;
import com.ruankennedy.socialnetwork.repository.PostRepository;
import com.ruankennedy.socialnetwork.repository.ProfileRepository;
import com.ruankennedy.socialnetwork.repository.RoleRepository;
import com.ruankennedy.socialnetwork.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class StartProjectConfigurations implements CommandLineRunner {

	@Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
	
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final FriendRepository friendRepository;

    
    @Override
    public void run(String... args) {
        User u1 = User.builder()
                .nickname("admin")
                .email("admin@hotmail.com")
                .password(encoder.encode("123456"))
                .build();

        User u2 = User.builder()
                .nickname("user1")
                .email("user1@hotmail.com")
                .password(encoder.encode("123456"))
                .build();

        User u3 = User.builder()
                .nickname("user2")
                .email("user2@hotmail.com")
                .password(encoder.encode("123456"))
                .build();

        List<User> users = userRepository.saveAll(Arrays.asList(u1, u2, u3));

        Role r1 = Role.builder()
                .name(RoleName.ROLE_ADMIN)
                .build();

        Role r2 = Role.builder()
                .name(RoleName.ROLE_USER)
                .build();

        roleRepository.saveAll(Arrays.asList(r1, r2));

        users.get(0).addRole(r1);
        users.get(1).addRole(r2);
        users.get(2).addRole(r2);
        
        users = userRepository.saveAll(users);

        Profile p1 = Profile.builder()
                .profilePhoto(getProfilePhoto("static/images/test.png"))
                .biography("Biography do perfil 1")
                .user(users.get(0))
                .build();

        Profile p2 = Profile.builder()
                .profilePhoto(getProfilePhoto("static/images/test.png"))
                .biography("Biography do perfil 2")
                .user(users.get(1))
                .build();

        Profile p3 = Profile.builder()
                .profilePhoto(getProfilePhoto("static/images/test.png"))
                .biography("Biography do perfil 3")
                .user(users.get(2))
                .build();

        List<Profile> profiles = profileRepository.saveAll(Arrays.asList(p1, p2, p3));

        users.get(0).addRole(r1);
        users.get(1).addRole(r2);
        users.get(2).addRole(r2);
        
        users = userRepository.saveAll(users);
        
        Post po1 = Post.builder()
                .subtitle("Subtitle do post 1")
                .postPhoto(getPostPhotos("static/images/setup.png", "static/images/setup2.png"))
                .postedMoment(LocalDateTime.now())
                .profile(profiles.get(0))
                .build();

        Post po2 = Post.builder()
                .subtitle("Subtitle do post 2")
                .postPhoto(getPostPhotos("static/images/setup2.png", "static/images/setup.png"))
                .postedMoment(LocalDateTime.now())
                .profile(profiles.get(1))
                .build();

        List<Post> posts = postRepository.saveAll(Arrays.asList(po1, po2));
        
        profiles.get(0).addPost(po1);
        profiles.get(1).addPost(po2);
        
        profiles = profileRepository.saveAll(profiles);

        Comment c1 = Comment.builder()
                .comment("comentario do usuario 1")
                .commentedMoment(LocalDateTime.now())
                .post(posts.get(0))
                .user(users.get(0))
                .build();

        Comment c2 = Comment.builder()
                .comment("comentario do usuario 2")
                .commentedMoment(LocalDateTime.now())
                .post(posts.get(1))
                .user(users.get(1))
                .build();

        Comment c3 = Comment.builder()
                .comment("comentario do usuario 3")
                .commentedMoment(LocalDateTime.now())
                .post(posts.get(1))
                .user(users.get(2))
                .build();
        
        commentRepository.saveAll(Arrays.asList(c1, c2, c3));
        
        users.get(0).addComment(c1);
        users.get(1).addComment(c2);
        users.get(2).addComment(c3);

        c1.setPost(posts.get(0));
        c2.setPost(posts.get(1));
        c3.setPost(posts.get(1));
        
        users = userRepository.saveAll(users);
        posts = postRepository.saveAll(posts);
        commentRepository.saveAll(Arrays.asList(c1, c2, c3));

        Friend f1 = Friend.builder()
                .friendStart(LocalDateTime.now())
                .profile(profiles.get(0))
                .targetProfile(profiles.get(1))
                .build();

        Friend f3 = Friend.builder()
                .friendStart(LocalDateTime.now())
                .profile(profiles.get(2))
                .targetProfile(profiles.get(1))
                .build();
        
        List<Friend> friends = friendRepository.saveAll(Arrays.asList(f1, f3));
        
        profiles.get(0).addFriend(f1);
        profiles.get(1).addFriend(f3);
        
        friends = friendRepository.saveAll(friends);
        profiles = profileRepository.saveAll(profiles);
        
    }

    private byte[] getProfilePhoto(String filePath) {
        try {
            Resource resource = new ClassPathResource(filePath);
            return Files.readAllBytes(resource.getFile().toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<byte[]> getPostPhotos(String... filePaths) {
        List<byte[]> postList = new ArrayList<>();
        try {
            for (String filePath : filePaths) {
                Resource resource = new ClassPathResource(filePath);
                byte[] bytes = Files.readAllBytes(resource.getFile().toPath());
                postList.add(bytes);
            }
            return postList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}