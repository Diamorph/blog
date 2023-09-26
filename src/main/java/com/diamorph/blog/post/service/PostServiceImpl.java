package com.diamorph.blog.post.service;

import com.diamorph.blog.post.dto.PostBodyUpdateDto;
import com.diamorph.blog.post.dto.PostCreateDto;
import com.diamorph.blog.post.dto.PostDTO;
import com.diamorph.blog.post.dto.PostTitleUpdateDto;
import com.diamorph.blog.post.exception.PostNotFoundException;
import com.diamorph.blog.post.jpa.PostRepository;
import com.diamorph.blog.post.model.Post;
import com.diamorph.blog.user.model.User;
import com.diamorph.blog.user.service.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private ModelMapper modelMapper;
    private UserServiceImpl userService;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper, UserServiceImpl userService) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.modelMapper.addMappings(new PropertyMap<Post, PostDTO>() {
            @Override
            protected void configure() {
                map().setUserId(source.getUser().getId());
            }
        });
    }

    @Override
    public List<PostDTO> retrievePosts(int userId) {
        User user = retrieveUserById(userId);
        List<Post> postList = findAllByUserId(user.getId());
        return postList.stream().map(this::convertToDto).toList();
    }

    @Override
    public PostDTO retrievePostDtoById(int userId, int id) {
        return convertToDto(retrievePostById(userId, id));
    }

    @Override
    public PostDTO createPost(int userId, PostCreateDto postDTO) {
        User user = retrieveUserById(userId);
        Post post = convertToEntity(postDTO);
        post.setUser(user);
        Post savedPost = save(post);
        return convertToDto(savedPost);
    }

    @Override
    public PostDTO updatePost(int userId, int id, PostCreateDto postDTO) {
        Post updatedPost = convertToEntity(postDTO);
        User user = retrieveUserById(userId);
        Post post = retrievePostById(user.getId(), id);
        post.setTitle(updatedPost.getTitle());
        post.setBody(updatedPost.getBody());
        return convertToDto(save(post));
    }

    @Override
    public void deletePost(int userId, int id) {
        Post post = retrievePostById(userId, id);
        postRepository.delete(post);
    }

    @Override
    public void updatePostTitle(int userId, int id, PostTitleUpdateDto postTitleUpdateDto) {
        Post post = retrievePostById(userId, id);
        post.setTitle(postTitleUpdateDto.getTitle());
        save(post);
    }

    @Override
    public void updatePostBody(int userId, int id, PostBodyUpdateDto postBodyUpdateDto) {
        Post post = retrievePostById(userId, id);
        post.setBody(postBodyUpdateDto.getBody());
        save(post);
    }

//    @Override
//    public void updatePostPartially(int id, Map<String, Object> fields) {
//        Post post = this.retrievePostById(id);
//        fields.forEach((k, v) -> {
//            // use reflection to get field k on manager and set it to value v
//            Field field = ReflectionUtils.findField(Post.class, k);
//            if (field == null) {
//                throw new BadRequestException(String.format("Bad Request:\n Can't find field %s", k));
//            }
//            field.setAccessible(true);
//            ReflectionUtils.setField(field, post, v);
//        });
//        postRepository.save(post);
//    }

    private Post save(Post post) {
        return postRepository.save(post);
    }

    private Post retrievePostById(int userId, int id) {
        User user = retrieveUserById(userId);
        Optional<Post> post = findPostById(user.getId(), id);
        if (post.isEmpty()) {
            throw new PostNotFoundException("Post not Found: " + id);
        }
        return post.get();
    }

    private Optional<Post> findPostById(int userId, int id) {
        return postRepository.getFirstByUser_IdAndId(userId, id);
    }

    private User retrieveUserById(int userId) {
        return this.userService.retrieveUserById(userId);
    }

    private List<Post> findAllByUserId(int userId) {return postRepository.findByUserId(userId);}

    private PostDTO convertToDto(Post post) {
        return modelMapper.map(post, PostDTO.class);
    }

    private Post convertToEntity(PostCreateDto postDTO) {
        return modelMapper.map(postDTO, Post.class);
    }
}
