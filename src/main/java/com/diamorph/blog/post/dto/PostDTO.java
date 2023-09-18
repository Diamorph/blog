package com.diamorph.blog.post.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostDTO {
    private int id;
    private String title;
    private String body;
}
