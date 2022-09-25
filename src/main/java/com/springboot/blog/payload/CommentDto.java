package com.springboot.blog.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CommentDto {
    private long id;
    @NotEmpty(message="Name should not be null or empty")
    @Size(min=2, message="Username should have at least 2 characters")
    private String name;
    @NotEmpty(message="Email should not be null or empty")
    @Email(message="Email should be of a proper format")
    private String email;
    @NotEmpty
    @Size(min=10, message="Comment content should have at least 100 characters")
    private String body;
}
