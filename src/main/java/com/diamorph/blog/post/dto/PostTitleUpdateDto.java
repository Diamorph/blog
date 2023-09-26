package com.diamorph.blog.post.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostTitleUpdateDto {
    @NotBlank(message = "Title: Must not be blank")
    @Length(min = 3, message = "Title: Must have at least 3 characters")
    private String title;
}
