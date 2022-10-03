package com.springboot.blog.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@ApiModel(description = "Comment model info")
@Data
public class CommentDto {
    @ApiModelProperty("Blog comment id")
    private long id;
    @ApiModelProperty("Blog comment name")
    @NotEmpty(message="Name should not be null or empty")
    @Size(min=2, message="Username should have at least 2 characters")
    private String name;
    @ApiModelProperty("Blog comment email")
    @NotEmpty(message="Email should not be null or empty")
    @Email(message="Email should be of a proper format")
    private String email;
    @ApiModelProperty("Blog comment body")
    @NotEmpty
    @Size(min=10, message="Comment content should have at least 100 characters")
    private String body;
}
