package com.diamorph.blog.post.mapper;

import com.diamorph.blog.post.dto.PostCreateDto;
import com.diamorph.blog.post.dto.PostDTO;
import com.diamorph.blog.post.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper( PostMapper.class );

    PostDTO toPostDTO(Post post);

    Post toPost(PostDTO postDto);

    Post toPost(PostCreateDto postCreateDto);
}
