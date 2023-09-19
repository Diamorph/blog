package com.diamorph.blog.post.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(nullable = false)
    @NotBlank(message = "Title: Must not be blank")
    @Length(min = 3, message = "Title: Must have at least 3 characters")
    private String title;
    @Column(nullable = false)
    @NotBlank(message = "Body: Must not be blank")
    @Length(min = 3, message = "Body: Must have at least 3 characters")
    private String body;
}
