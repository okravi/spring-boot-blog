package com.springboot.blog.payload;

import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Post model info")
@Data
public class PostDto {
    @ApiModelProperty("Blog post id")
    private long id;
    @ApiModelProperty("Blog post title")
    @NotEmpty
    @Size(min=2, message="Post title should have at least 2 characters")
    private String title;
    @ApiModelProperty("Blog post description")
    @NotEmpty
    @Size(min=10, message="Post description should have at least 10 characters")
    private String description;
    @ApiModelProperty("Blog post content")
    @NotEmpty
    @Size(min=10, message="Post content should have at least 100 characters")
    private String content;
    private Set<CommentDto> commentDtoSet;
}
