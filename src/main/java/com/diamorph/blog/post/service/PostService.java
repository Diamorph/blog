package com.diamorph.blog.post.service;

import com.diamorph.blog.post.dto.PostBodyUpdateDto;
import com.diamorph.blog.post.dto.PostCreateDto;
import com.diamorph.blog.post.dto.PostDTO;
import com.diamorph.blog.post.dto.PostTitleUpdateDto;

import java.util.List;

public interface PostService {
    List<PostDTO> retrievePosts(int userId);

    PostDTO retrievePostDtoById(int userId, int id);

    PostDTO createPost(int userId, PostCreateDto postDTO);
//
    PostDTO updatePost(int userId, int id, PostCreateDto postDTO);

    void deletePost(int userId, int id);

    void updatePostTitle(int userId, int id, PostTitleUpdateDto title);

    void updatePostBody(int userId, int id, PostBodyUpdateDto title);

}
