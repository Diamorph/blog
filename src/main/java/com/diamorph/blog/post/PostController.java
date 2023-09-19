package com.diamorph.blog.post;

import com.diamorph.blog.post.dto.PostDTO;
import com.diamorph.blog.post.service.PostServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/posts")
public class PostController {

    private PostServiceImpl postServiceImpl;

    public PostController(PostServiceImpl postServiceImpl) {
        this.postServiceImpl = postServiceImpl;
    }

    @GetMapping
    public List<PostDTO> retrieveAllPosts() {
        return postServiceImpl.retrievePosts();
    }

    @GetMapping("/{id}")
    public PostDTO retrievePostById(@PathVariable int id) {
        return postServiceImpl.retrievePostDtoById(id);
    }

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postDTO);
    }

    @PutMapping("/{id}")
    public PostDTO updatePost(@PathVariable int id, @RequestBody PostDTO postDTO) {
        return postServiceImpl.updatePost(id, postDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePostPartially(@PathVariable int id,  @RequestBody Map<String, Object> fields) {
        postServiceImpl.updatePostPartially(id, fields);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable int id) {
        postServiceImpl.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
