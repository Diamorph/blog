package com.diamorph.blog.post;

import com.diamorph.blog.post.dto.PostBodyUpdateDto;
import com.diamorph.blog.post.dto.PostCreateDto;
import com.diamorph.blog.post.dto.PostDTO;
import com.diamorph.blog.post.dto.PostTitleUpdateDto;
import com.diamorph.blog.post.service.PostServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/posts")
public class PostController {

    private PostServiceImpl postService;

    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> retrieveAllPosts(@PathVariable int userId) {
        List<PostDTO> postList = postService.retrievePosts(userId);
        return ResponseEntity.status(HttpStatus.OK).body(postList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> retrievePostById(@PathVariable int userId, @PathVariable int id) {
        PostDTO postDto = postService.retrievePostDtoById(userId, id);
        return ResponseEntity.status(HttpStatus.OK).body(postDto);
    }

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@PathVariable int userId, @Valid @RequestBody PostCreateDto postDTO) {
        PostDTO createdPost = postService.createPost(userId, postDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable int userId, @PathVariable int id, @Valid @RequestBody PostCreateDto postCreateDto) {
        PostDTO updatedPost = postService.updatePost(userId, id, postCreateDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedPost);
    }

    @PatchMapping("/{id}/title")
    public ResponseEntity<Void> updatePostTitle(@PathVariable int userId, @PathVariable int id, @Valid @RequestBody PostTitleUpdateDto postTitleUpdateDto) {
        postService.updatePostTitle(userId, id, postTitleUpdateDto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/body")
    public ResponseEntity<Void> updatePostBody(@PathVariable int userId, @PathVariable int id, @Valid @RequestBody PostBodyUpdateDto postBodyUpdateDto) {
        postService.updatePostBody(userId, id, postBodyUpdateDto);
        return ResponseEntity.noContent().build();
    }

//    @PatchMapping("/{id}")
//    public ResponseEntity<Void> updatePostPartially(@PathVariable int id,  @RequestBody Map<String, Object> fields) {
//        postService.updatePostPartially(id, fields);
//        return ResponseEntity.noContent().build();
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable int userId, @PathVariable int id) {
        postService.deletePost(userId, id);
        return ResponseEntity.noContent().build();
    }
}
