package com.diamorph.blog.post.service;

import com.diamorph.blog.exeption.BadRequestException;
import com.diamorph.blog.jpa.PostRepository;
import com.diamorph.blog.post.dto.PostDTO;
import com.diamorph.blog.post.exception.PostNotFoundException;
import com.diamorph.blog.post.model.Post;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    private ModelMapper modelMapper;

    public List<PostDTO> retrievePosts() {
        List<Post> postList = findAll();
        return postList.stream().map(this::convertToDto).toList();
    }

    public PostDTO retrievePostDtoById(int id) {
        return convertToDto(retrievePostById(id));
    }

    public PostDTO createPost(PostDTO postDTO) {
        Post savedPost = save(convertToEntity(postDTO));
        return convertToDto(savedPost);
    }

    public PostDTO updatePost(int id, PostDTO postDTO) {
        retrievePostById(id);
        Post updatedPost = convertToEntity(postDTO);
        updatedPost.setId(id);
        return convertToDto(save(updatedPost));
    }

    public void deletePost(int id) {
        this.retrievePostById(id);
        postRepository.deleteById(id);
    }

    public void updatePostPartially(int id, Map<String, Object> fields) {
        Post post = this.retrievePostById(id);
        fields.forEach((k, v) -> {
            // use reflection to get field k on manager and set it to value v
            Field field = ReflectionUtils.findField(Post.class, k);
            if (field == null) {
                throw new BadRequestException(String.format("Bad Request:\n Can't find field %s", k));
            }
            field.setAccessible(true);
            ReflectionUtils.setField(field, post, v);
        });
        postRepository.save(post);
    }

    private Post save(Post post) {
        return postRepository.save(post);
    }

    private Post retrievePostById(int id) {
        Optional<Post> post = findById(id);
        if (post.isEmpty()) {
            throw new PostNotFoundException("Post not Found: " + id);
        }
        return post.get();
    }

    private Optional<Post> findById(Integer id) {
        return postRepository.findById(id);
    }

    private List<Post> findAll() {
        return postRepository.findAll();
    }

    private PostDTO convertToDto(Post post) {
        return modelMapper.map(post, PostDTO.class);
    }

    private Post convertToEntity(PostDTO postDTO) {
        return modelMapper.map(postDTO, Post.class);
    }
}
