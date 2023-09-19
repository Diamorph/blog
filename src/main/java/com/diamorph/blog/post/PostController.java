package com.diamorph.blog.post;

import com.diamorph.blog.post.dto.PostDTO;
import com.diamorph.blog.post.service.PostServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {

    private PostServiceImpl postService;

    @GetMapping
    public ResponseEntity<List<PostDTO>> retrieveAllPosts() {
        List<PostDTO> postList = postService.retrievePosts();
        return ResponseEntity.status(HttpStatus.OK).body(postList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> retrievePostById(@PathVariable int id) {
        PostDTO postDto = postService.retrievePostDtoById(id);
        return ResponseEntity.status(HttpStatus.OK).body(postDto);
    }

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO) {
        PostDTO createdPost = postService.createPost(postDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable int id, @Valid @RequestBody PostDTO postDTO) {
        PostDTO updatedPost = postService.updatePost(id, postDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedPost);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePostPartially(@PathVariable int id,  @RequestBody Map<String, Object> fields) {
        postService.updatePostPartially(id, fields);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable int id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
