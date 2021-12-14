package com.sztop.battlefield.jpa.controller.dto.mapper;

import com.sztop.battlefield.jpa.controller.dto.PostDto;
import com.sztop.battlefield.jpa.model.Post;

import java.util.List;
import java.util.stream.Collectors;

public class PostDtoMapper {

    public static List<PostDto> mapToPostDtos(List<Post> posts) {
        return posts.stream()
                .map(PostDtoMapper::mapToPostDto)
                .collect(Collectors.toList());
    }

    private static PostDto mapToPostDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .created(post.getCreated())
                .build();
    }
}
