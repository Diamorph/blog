package com.diamorph.blog.post.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostBodyUpdateDto {
    @NotBlank(message = "Body: Must not be blank")
    @Length(min = 3, message = "Body: Must have at least 3 characters")
    private String body;
}
