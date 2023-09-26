package com.diamorph.blog.post.mapper;

import com.diamorph.blog.post.dto.PostCreateDto;
import com.diamorph.blog.post.dto.PostDTO;
import com.diamorph.blog.post.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper( PostMapper.class );

    @Mapping(target = "userId", source = "user.id")
    PostDTO toPostDTO(Post post);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Post toPost(PostCreateDto postCreateDto);
}
