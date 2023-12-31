package com.diamorph.blog.user.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserCreateDto {
    @Column(length = 50, nullable = false, unique = true)
    @Email(message = "User must have a valid email address")
    private String email;

    @Column(length = 20, nullable = false)
    @NotBlank(message = "Fist name could not be empty")
    @Length(min = 3, message = "First name should have at least 3 characters")
    private String name;

    @Column(length = 20, nullable = false)
    @NotBlank(message = "Last name could not be empty")
    @Length(min = 3, message = "Last name should have at least 3 characters")
    private String lastName;

    @NotNull
    @Min(value = 0, message = "Age should be greater than 0")
    @Max(value = 125, message = "Age should be less than 125")
    private short age;
}
