package com.diamorph.blog.post.mapper;

import com.diamorph.blog.post.dto.PostDTO;
import com.diamorph.blog.post.model.Post;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper( PostMapper.class );

    Post postDtoToPost(PostDTO postDto);

    PostDTO postToPostDto(Post post);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePostFromPostDto(PostDTO dto, @MappingTarget Post entity);
}
