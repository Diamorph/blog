package com.diamorph.blog.post.service;

import com.diamorph.blog.post.dto.PostDTO;
import com.diamorph.blog.post.model.Post;

import java.util.List;
import java.util.Map;

public interface PostService {
    List<PostDTO> retrievePosts();

    PostDTO retrievePostDtoById(int id);

    PostDTO createPost(PostDTO postDTO);

    PostDTO updatePost(int id, PostDTO postDTO);

    void deletePost(int id);

    void updatePostPartially(int id, Map<String, Object> fields);

    private PostDTO convertToDto(Post post) {
        return new PostDTO();
    }

    private Post convertToEntity(PostDTO postDTO) {
        return new Post();
    }
}
