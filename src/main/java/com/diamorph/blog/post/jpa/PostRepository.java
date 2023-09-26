package com.diamorph.blog.post.jpa;

import com.diamorph.blog.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByUserId(int userId);

    Optional<Post> getFirstByUser_IdAndId(Integer userId, Integer id);

//    @Query("SELECT p from Post p WHERE p.user.id = ?1 and p.id = ?2")
//    Post retrievePostById(Integer userId, Integer id);
}
