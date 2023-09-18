package com.diamorph.blog.jpa;

import com.diamorph.blog.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
